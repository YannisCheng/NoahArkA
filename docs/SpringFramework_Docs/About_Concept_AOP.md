# 关于Spring Framework的概念理解 — AOP

## 1. 基本概念

### 1.1 AOP（Aspect Oriented Programming）— 面向切面编程

 - 面向切面编程
 - 不通过修改原逻辑的方式添加或修改新功能
 - 业务逻辑隔离，降低耦合度。
 
### 1.2 AOP底层原理 — 动态代理
 
#### 1.2.1 有2种情况需要使用动态代理
 
1. 有接口：使用JDK的动态代理

   创建 `接口实现类` 的 `代理对象`，增强类的方法。
   
     
2. 没有接口：使用CGLIB动态代理
 
    创建`子类`的`代理对象`，增强类的方法。
 
#### 1.2.2 知识点
   
1. **动态代理**

 
- [Java动态代理分析](https://blog.csdn.net/danchu/article/details/70146985)

   
2. **CGLIB**

 `CGLib (Code Generation Library) ` 是一个强大的、高性能、高质量的 `Code 生成类库`。
 其被广泛应用于 `AOP框架（Spring、dynaop）`中，用以提供`方法拦截`操作。
 它可以在 `运行期` 扩展 `Java 类` 与实现 `Java 接口`。
 `CGLib` 比 Java 的 `java.lang.reflect.Proxy` 类更强的在于它不仅可以接管`接口类`的方法，还可以接管`普通类`的方法。
 CGLib 的底层是 `Java字节码操作框架 —— ASM`。
    
 - [CGLIB(Code Generation Library)详解](https://www.oschina.net/p/cglib?hmsr=aladdin1e1)
 - [cglib GitHab](https://github.com/cglib/cglib)

## 2. 代理实现原理 — Java Proxy方式

### 2.1 创建接口，声明方法


```Java
public interface UserDao {
    public int add(int a, int b);
    public String update(String data);
}
```

### 2.2 实现接口


```Java
public class UserDaoImpl implements UserDao{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public String update(String data) {
        return data;
    }
}
```

### 2.3 创建Proxy


```Java
// 创建被代理的对象
UserDaoImpl userDao = new UserDaoImpl();
// 声明被代理的接口
Class[] interfaces = {UserDao.class};
// 理解关键点： 声明:接口实现类的代理对象，在这个代理对象中对被代理接口实现类的每一个方法进行增强。
UserDao proxyInstance = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 此处如果对 method 进行进一步处理就是用到了反射。
        System.out.println("方法之前执行。。。" + method.getName() + "args : " + Arrays.toString(args));
        // 当proxyInstance对象调用哪个方法时，此处就执行哪个方法。
        // 所谓的“方法增强”就是在“被调用方法上下”进行代码处理
        Object invoke = method.invoke(userDao, args);
        System.out.println("方法之后执行。。。" + invoke);

        return invoke;
    }
});
```

### 2.4 使用Proxy


```Java
// 接口实现类的代理对象 调用接口中的 add()
int add = proxyInstance.add(1, 2);
System.out.println("result: " + add);
/*
 * 运行结果：
 * 方法之前执行。。。addargs : [1, 2]
 * 方法之后执行。。。3
 * result: 3
 */
// 接口实现类的代理对象 调用接口中 的update()
String newUpdate = proxyInstance.update("new update");
System.out.println("update new method: " + newUpdate);
/*
 * 运行结果：
 * 方法之前执行。。。updateargs : [new update]
 * 方法之后执行。。。new update
 * update new method: new update
 */
```

## 3. AOP操作术语

- **连接点**：类中`被增强的方法`
- **切入点**：`实际真正被增强的方法`
- **通知（增强）**：原逻辑中`新增的实际增强的逻辑`
 - 前置
 - 后置
 - 环绕
 - 异常
 - 最终：`finally{}`
- **切面**：将`通知`应用到`切入点`的 **过程**

## 4. AspectJ操作

### 4.1 准备

- `Spring`中使用`AspectJ`实现`AOP操作`
- `AspectJ`是一个`独立`的`AOP框架`。
- 2种方式实现注解：
 - xml
 - 注解
- Spring中AspectJ的 **切入点表达式**：

 `execution([类的权限修饰符][返回类型][类的全路径][方法名称][参数列表])`
 
 例1. 仅对包中的类中的1个方法增强
 `execution(* com.cwj.genesis.Book.showName(,,))`
 
  例2. 对包中的类中的所有方法增强
 `execution(* com.cwj.genesis.Book.*(,,))`

 例3. 对包中的所有类中所有方法增强
 `execution(* com.cwj.genesis.*.*(,,))`

  

### 4.2 AspectJ基础注解方式实现AOP


#### 4.2.1 创建类，并定义方法


```Java
public class UserDao {
    public void add(){
        System.out.println("add ......");
    }
}
```

#### 4.2.2 创建增强类（编写增强逻辑）
	
	
**增强类中编写方法：不同的方法代表不同的通知类型**
	
	
```Java
public class UserDaoProxy {
    public void before(){
        System.out.println("before 。。。。。");
    }

    public void after(){
        System.out.println("after 。。。。。");
    }
}
```

#### 4.2.3 注解配置

或者使用：**xml开启注解扫描和Aspect注解配置**
 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--开启注解扫描路径-->
    <context:component-scan base-package="com.cwj.genesis.aop"/>
    
    <!--开启Aspect生成代理对象-->
    <aop:aspectj-autoproxy/>
	
</beans>
```

或者使用： **自定义扫描类**


```Java
//将此类声明为一个配置文件
@Configuration
// 开启AspectJ自动生成代理对象
@EnableAspectJAutoProxy
// 配置注解扫描路径范围
@ComponentScan(basePackages = {"com.cwj.genesis.aop"})
public class SpringScan {
}
```

#### 4.2.4 添加注解以创建对象


```Java
@Component
public class UserDao {
    public void add(){
        System.out.println("add ......");
    }
}
```

 
```Java
@Aspect
@Component
public class UserDaoProxy {
    public void before(){
        System.out.println("before 。。。。。");
    }

    public void after(){
        System.out.println("after 。。。。。");
    }
}
```

#### 4.2.5 配置不同类型的通知（增强）

在增强类中，在通知方法上添加通知类型注解（使用切入点表达式进行配置）


```Java
@Aspect
@Component
public class UserDaoProxy {

    //前置通知：value值可忽略
    @Before(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void before(){
        System.out.println("before 。。。。。");
    }

    /**
     * 最终通知
     */
    @After(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void after(){
        System.out.println("after 。。。。。");
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void afterReturning(){
        System.out.println("afterReturning 。。。。。");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void afterThrowing(){
        System.out.println("afterThrowing 。。。。。");
    }

    @Around(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void Around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Around before。。。。。");
        // 被增强的方法
        point.proceed();
        System.out.println("Around after。。。。。");
    }
}
```

#### 4.2.6 测试


```Java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringScan.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.add();
/*
 * 运行结果：
 * Around before。。。。。
 * before 。。。。。
 * add ......
 * afterReturning 。。。。。
 * after 。。。。。
 * Around after。。。。。
 */
```

#### 4.2.7 相同的切入点抽取
```Java
// 相同的切入点抽取
@Pointcut(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
public void point(){

}

// "相同的切入点抽取" 的使用
@Before(value = "point()")
public void before(){
    System.out.println("before 。。。。。");
}
```

#### 4.2.8 一个方法有多个增强类时，设置增强类的优先级

**注解代理类上添加@Order，数字越小，优先级越高**
