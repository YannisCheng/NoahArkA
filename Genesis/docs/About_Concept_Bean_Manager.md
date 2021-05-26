# 关于Spring Framework的概念理解 — Bean管理

## 1. 基本概念


- **`IoC`（Inversion of Control）控制反转：**

 使用 `IoC` 的目的：`降低耦合度`
 `IoC(控制反转)` 理解：将`对象`的 **控制** **反转** 给`Spring`
 
- **AOP（Aspect Oriented Programming）面向切面编程**

- **底层原理：**

 1. xml解析
 2. 工厂模式
 3.  反射

- **思想：**

 `IoC思想`基于`IoC容器`，IoC容器 **底层** 就是 `工厂模式`
 
 [](/images/spring_ioc.png)


- **Spring中IoC容器实现的2种方式(接口)：**

 1.  **BeanFactory**：最基本（内置）的实现，不提供给Dev人员使用。
 2.  `加载配置文件`时使用`不创建对象`，仅在 `获取(使用)对象` 时才创建对象。
 3.  **ApplicationContext**：是 `BeanFactory` 的子实现接口，更强大，供dev人员使用。
    - 加载配置文件时就在配置文件中创建对象（Tomcat启动过程中就创建对象）。

## 2. Spring的Bean管理

Spring的Bean有2种类型：

 - **FactoryBean**：配置文件中，配置Bean类型`不一定`是返回类型。
 - **普通Bean**：配置文件中，配置Bean类型`一定是`返回类型。

Bean管理有2个操作：

- 用Spring **创建对象**
- 用Spring **注入属性**

这2个操作均有2种管理方式实现：

- **xml方式**
- **反射方式**




## 3. 实现方式1：基于xml方式的Bean管理



### 3.1 创建对象

1. 使用`bean`标签

   ```xml
   <bean id="user" class="com.cwj.genesis.User"></bean>
   ```

2. `bean` 标签的重要属性

   属性：

    - id：不是对象的名字，是唯一标识
    - class：类全路径

3. 创建对象时默认执行的是`无参构造函数`

### 3.2 注入属性



**思想：DI(依赖注入)，注入属性（基于已经创建的对象）**

#### 3.2.1 属性注入 — 内部注入

- `setter`方式注入

  ```xml
  <bean id="book" class="com.cwj.genesis.declare.Book">
      <property name="name" value="shengjing"/>
      <property name="price" value="333"/>
  </bean>
  ```

- `有参构造函数`注入


	```xml
	 <bean id="book" class="com.cwj.genesis.declare.Book">
        <!--有参构造注入-->
        <constructor-arg name="address" value="jinan"/>
        <!--属性注入-->
        <property name="name" value="shengjing"/>
        <property name="price" value="333"/>
	 </bean>
	```

- 其他类型的数据注入-字面量

    1. null


	```xml
	<bean id="book" class="com.cwj.genesis.declare.Book">
        <!--为属性注入null值-->
        <property name="out">
            <null/>
        </property>
	</bean>
	```

2. 特殊符号

	```xml
	<bean id="book" class="com.cwj.genesis.declare.Book">
	    <!--为属性注入 特殊符号 ：
	    1：转义字符
	    2：CDATA 结构
	    -->
	    <property name="out2">
	        <value><![CDATA[<<jinan-out>>]]></value>
	    </property>
	</bean>
	```

#### 3.2.2 属性注入 — Bean注入

- 内部Bean注入

	```xml
	<bean id="emp" class="com.cwj.genesis.bean.Emp">
	    <property name="name" value="lucy"></property>
	    <property name="genter" value="male"></property>
	    <property name="dept">
	        <!--内部Bean的写法-->
	        <bean id="dept" class="com.cwj.genesis.bean.Dept">
	            <property name="name" value="baoan"></property>
	        </bean>
	    </property>
	</bean>
	```

- 外部Bean注入

  ```xml
  <bean name="userService" class="com.cwj.genesis.service.UserService
      <!--注意此处的name值为：类声明中的属性名称-->
      <!--注意此处的 userDaoImpl要和：<bean name="userDaoImpl"中的name值 对应-->
      <!--这条属性配置语句实际表达的是：
      将 '<bean name="userDaoImpl"' 中创建的对象
      通过 'ref' 引入到：
      '<property name="userDao"' 的属性中。-->
      <property name="userDao" ref="userDaoImpl"/>
  </bean>
  <bean name="userDaoImpl" class="com.cwj.genesis.dao.UserDaoImpl"/>
  ```

  文件路径结构：

  [](/images/file_route.png)


#### 3.2.3 属性注入 — 集合注入

array、 List、Map、Set


```xml
<bean id="stu" class="com.cwj.genesis.bean.Stu">
    <property name="courses" >
        <array>
            <value>Java</value>
            <value>Database</value>
        </array>
    </property>
    <property name="list">
        <list>
            <value>jone</value>
            <value>bob</value>
        </list>
    </property>
    <property name="maps">
        <map>
            <entry key="JAVA" value="java"></entry>
            <entry key="DATA" value="data"></entry>
        </map>
    </property>
    <property name="sets">
        <set>
            <value>MySQL</value>
            <value>MyBaits</value>
        </set>
    </property>
</bean>
```

集合中使用 `自定义对象` 时的写法：

```xml
<bean id="stu" class="com.cwj.genesis.bean.Stu">
    <!--集合中使用：对象-->
    <property name="coursesList">
        <list>
            <ref bean="course1"/>
            <ref bean="course2"/>
        </list>
    </property>
</bean>
<!--在外部声明 list集合 中使用到的各个对象-->
<bean id="course1" class="com.cwj.genesis.bean.Course">
    <property name="name" value="spring"/>
</bean>
<bean id="course2" class="com.cwj.genesis.bean.Course">
    <property name="name" value="Java"/>
</bean>
```

公共部分提取：


```xml
<!--1. 设置自定义标签-->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">

    <!--2. 提取公共集合数据-->
    <util:list id="bookStu">
        <value>Book1</value>
        <value>Book2</value>
        <value>Book3</value>
    </util:list>

    <!--3. 设置属性-->
    <bean id="bookstu" class="com.cwj.genesis.bean.BookStu">
        <property name="list" ref="bookStu"/>
    </bean>
</beans>
```

### 3.3. FactoryBean

#### 3.3.1 创建一个实现FactoryBean接口的Bean

```Java
public class MyFactoryBean implements FactoryBean<Book> {
}
```

#### 3.3.2 实现FactoryBean中的方法，在方法中返回Bean类型

**设置返回类型：**

```Java
public class MyFactoryBean implements FactoryBean<Book> {

    /**
     * 返回指定的Bean类型
     */
    @Override
    public Book getObject() throws Exception {
        return new Book("Jinan");
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
    
}
```

**Bean配置文件：**

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="mybean" class="com.cwj.genesis.factory_bean.MyFactoryBean">
    </bean>
</beans>
```

**调用：**


```Java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mybean.xml");
Book bookstu = context.getBean("mybean", Book.class);
System.out.println(bookstu);
```

### 3.4 Bean的作用域

#### 3.4.1 说明

- Bean的`单实例`和`多实例`对象是可以修改的。
- Bean默认情况下为 `单例对象`。

#### 3.4.2 Bean实例方式设置


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--默认的实例方式：singleton-->
    <!--<bean id="mybean" class="com.cwj.genesis.factory_bean.MyFactoryBean" scope="singleton">-->
    <!--修改后的实例方式：prototype-->
    <bean id="mybean" class="com.cwj.genesis.factory_bean.MyFactoryBean" scope="prototype">
    </bean>
</beans>
```

#### 3.4.3 区别

|区别|singleton|prototype|
|---| --- | --- |
|单、多|单实例|多实例  |
|创建时机| Spring加载配置文件时创建单实例对象| 在调用getBean()时创建多实例对象 |

### 3.5 Bean的生命周期

**`对象创建` -> `对象销毁`**

#### 3.5.1 生命周期过程（5步）

**1. 描述**

1. 通过`无参构造函数` 创建Bean实例
2. 调用`setter`
3. 调用Bean的`初始化方法` — **需要配置**
4. 使用对象
5. `容器关闭`时，调用Bean对象的`销毁方法` — **需要配置**

**2. 代码实现验证**

1. **Java代码声明对象：**

```Java
public class LifeCycleBean {

    public LifeCycleBean() {
        System.out.println("1 step -> construct");
    }

    private String name;

    public void setName(String name) {
        this.name = name;
        System.out.println("2 step -> setter");
    }

    /**
     * Bean对象的初始化方法
     */
    public void initMethod() {
        System.out.println("3 step -> init method");
    }

    /**
     * Bean对象的销毁方法
     */
    public void destroyMethod() {
        System.out.println("5 step -> destroy method");
    }

    @Override
    public String toString() {
        return "LifeCycleBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

2. **配置文件声明：**


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--设置： Bean的"初始化方法"、"销毁方法"-->
    <bean id="lifecycle" class="com.cwj.genesis.lifecycle.LifeCycleBean" init-method="initMethod" destroy-method="destroyMethod">
        <property name="name" value="生命周期"/>
    </bean>
</beans>
```


3. **运行：**

```Java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifecycle.xml");
LifeCycleBean lifecycle = context.getBean("lifecycle", LifeCycleBean.class);
System.out.println("4 step -> use lifecycle obj" + lifecycle.toString());

// 手动调用Bean的销毁，调用：LifeCycleBean#destroyMethod()
context.close();
```

4. **运行结果：**

```
1 step -> construct
2 step -> setter
3 step -> init method
4 step -> use lifecycle objLifeCycleBean{name='生命周期'}
5 step -> destroy method
```

#### 3.5.2 生命周期过程（7步）

**1. 描述**

1. 通过`无参构造函数` 创建Bean实例
2. 调用`setter`
3. **把 `bean实例` 传递给 Bean前置处理器: `BeanPostProcessor#postProcessBeforeInitialization()`**
4. 调用Bean的`初始化方法` — **需要配置**
5. **把 `bean实例` 传递给 Bean后置处理器: `BeanPostProcessor#postProcessAfterInitialization()`**
6. 使用对象
7. `容器关闭`时，调用Bean对象的`销毁方法` — **需要配置**

**2. 代码实现验证**

1. **Java代码：**


```Java
public class LifeCycleBean7 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization");
        return bean;
    }
}
```

2. **配置文件：**


```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--设置： Bean的"初始化方法"、"销毁方法"-->
    <bean id="lifecycle" class="com.cwj.genesis.lifecycle.LifeCycleBean5" init-method="initMethod" destroy-method="destroyMethod">
        <property name="name" value="生命周期"/>
    </bean>

    <bean id="life7" class="com.cwj.genesis.lifecycle.LifeCycleBean7" init-method="initMethod"
          destroy-method="destroyMethod">
        <property name="name" value="nanan"/>
    </bean>
</beans>
```

**运行结果：**


```
1 step -> construct
2 step -> setter
BeforeInitialization
3 step -> init method
AfterInitialization
4 step -> use lifecycle objLifeCycleBean5{name='生命周期'}
5 step -> destroy method
```

#### 3.5.3 应用场景

### 3.6 自动装配
### 3.7 外部属性配置文件

## 4. 实现方式2：基于注解的Bean管理



### 4.1 非纯注解-创建对象

**注解管理需要一个配置属性：`component-scan` 设置扫描范围。**

```xml

<context:component-scan base-package="com.cwj.genesis.bean_manager.annotation"/>
```

#### 4.1.1. 创建用到的注解：

- `@Component`：普通注解
- `@Service`：Service层
- `@Controller`：Web层
- `@Repository`：DAO层

以上4个注解功能是一样的：都是用用于：`创建Bean实例`。

#### 4.1.2 使用步骤

1. **添加依赖**：spring-aop.jar
2. **开启组件扫描**：

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                               http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
   
       <!--开启组件扫描-->
       <context:component-scan base-package="com.cwj.genesis.bean_manager.annotation"/>
       <!--多个路径扫描：方式1：-->
       <!--<context:component-scan base-package="com.cwj.genesis.annotation,com.cwj.genesis.bean_manager.xml"/>-->
       <!--多个路径扫描：方式2：-->
       <!--<context:component-scan base-package="com.cwj.genesis"/>-->
   
   </beans>
   ```
	
	获取扫描规则：
	
	```Java
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Annotation1.xml");
	com.cwj.genesis.bean_manager.annotation.UserAnno bean = context.getBean("userAnno", UserAnno.class);
	bean.showLog();
	```
	
3. **创建类，添加注解**


	```Java
	@Component
	public class UserAnno {
	    public void showLog() {
	        System.out.println("user .........");
	    }
	}
	```

4. **扫描细节**


	```xml
	<!--扫描过滤：实例1：-->
	<!--语句含义：仅扫描"com.cwj.genesis"路径下的"Controller"类型的"annotation"注解-->
	<context:component-scan base-package="com.cwj.genesis" use-default-filter="false">
	    <context:include-filter type="annotation"
	                            expression="org.springframework.stereotype.Controller"/>
	</context:component-scan>
	
	<!--扫描过滤：实例2：-->
	<!--语句含义：除了"com.cwj.genesis"路径下的"Controller"类型的"annotation"注解外都扫描-->
	<context:component-scan base-package="com.cwj.genesis">
	    <context:exclude-filter type="annotation"
	                            expression="org.springframework.stereotype.Controller"/>
	</context:component-scan>
	```

### 4.2 非纯注解-注入属性

#### 4.2.1 注入属性用到的注解：

- `@AutoWired`
- `@Qualifier`
- `@Resource`
- `@Value`

#### 4.2.2 各个注入对象类型使用步骤

- `@AutoWired`：根据 `属性类型(byType)` 自动注入

 注入步骤：

 - 添加创建对象的注解：
 - 添加注入注解：

- `@Qualifier`：根据 `属性名称(byName)` 注入

 与 `@AutoWired` 和 `4个创建注解(value="")` 一起使用。
 
 
	```Java
	@Repository(value = "userDaoImpl1")
	public class UserDaoImpl implements UserDao {
	    @Override
	    public void update() {
	        System.out.println("user dao ......");
	    }
	}
	
	@Component
	public class UserAnno {
	
	    @Autowired
	    // 指定类型的某个名称
	    @Qualifier(value = "userDaoImpl1")
	    private UserDaoImpl userDao;
	
	    public void showLog() {
	        System.out.println("user .........");
	        userDao.update();
	    }
	}
	```
 
 
- `@Resource`：根据 `属性类型(byType)` 或 `属性名称(byName)` 注入
 
  是Java包中的注解，非Spring。


	```Java
	@Resource
	private UserDaoImpl userDao;
	// huo
	@Resource(name = "userDaoImpl1")
	private UserDaoImpl userDao;
	```

#### 4.2.3 注入普通类型属性

- `@Value`

	```Java
	@Value(value = "abc")
	private String name;
	```
	
### 4.3 纯注解开发




#### 4.3.1 创建配置类-spring boot



```Java
@Configuration
@ComponentScan(basePackages = {"com.cwj.genesis.bean_manager.annotation"})
public class SpringConfig {
}
```

#### 4.3.2 使用配置类


```Java
// 加载配置类
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
com.cwj.genesis.bean_manager.annotation.UserAnno bean = context.getBean("userAnno", UserAnno.class);
bean.showLog();
```

