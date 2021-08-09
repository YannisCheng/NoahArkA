# Spring中各个子组件的注解

2021-07-30 10:20:36 周五

- [Spring SpringMVC SpringBoot SpringCloud 注解整理](https://www.cnblogs.com/wddhahaha/archive/2019/10/26/11744071.html)
- [Spring和SpringMVC，MyBatis以及SpringBoot的注解分别有哪些？SpringMVC，SpringBoot，MyBatis框架的优点](https://blog.csdn.net/tzydzj/article/details/113241709)
- [spring注解,springmvc注解,springboot注解,springcloud注解大全](https://blog.csdn.net/kxj19980524/article/details/85124785)



## 1 Spring Framework

## 2 Spring boot

- [Spring Boot的相关注解@RestController和@Controller、@RequestMapping、@RequestParam和@RequestBody等](https://blog.csdn.net/u010986518/article/details/82187793)

#### @SpringBootApplication

![SpringBootApplication注解功能图解](/images/spring_framework/191601240_9_20200529102144347.jpg)

用于主类。其包含以下3个注解(Spring Boot 核心注解)功能：

- **@EnableAutoConfiguration**：启用 Spring Boot 的自动配置机制
- **@SpringBootConfiguration**：启用上下文中额外bean的注册或额外配置类的导入（自动import操作）。 Spring 标准 **@Configuration** 的替代方案，可帮助您在集成测试中检测配置。
- **@ComponentScan**：在应用程序所在的包上启用@Component扫描

#### @Repository - 声明Bean
标注数据访问组件，DAO组件。

#### @Service - 声明Bean
标注业务层组件。

#### @RestController - 声明Bean

Which marks the class as a controller where every method returns a domain object instead of a view. 
It is shorthand for including both @Controller and @ResponseBody.

标注控制层组件。包含：

- **@Controller**：
- **@ResponseBody**：表示该方法返回的结果直接写入`Http Response body`中，不会被解析为跳转路径。

#### @Component - 声明Bean
泛指组件，当组件不好归类时使用。

#### @ComponentScan - 配置类
组件扫描。如果扫描到有@Component、@Controller、@Service等这些注解的类，则会被Spring自动扫描并放入bean容器中。

#### @Configuration - 配置类
指出该类是Bean配置的信息源，相当于XML中的<beans></beans>，一般加在主类上。
读取：spring.factories 文件

#### @Bean - 配置类
相当于XML中的<bean></bean>，放在方法上，意思是生产一个Bean,并交由spring管理。

#### @AutoWired - 注入bean
byType方法，返回值会通过视图解析器解析为实际的物理视图，然后做转发操作。
Spring提供的注解。
把配置好的Bean拿来用，完成属性、方法的组装，自动装配，当加上（required=false）时，即便找不到这个Bean也不报错。

如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。如下：


```Java
public class HelloWorld{
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
}
```

#### @Qualifier - 注入bea
当有多个同一类型的Bean时，可以用@Qualifier("name")来指定，与@Autowired配合使用。

#### @Resource(name="name",type="type") - 注入bea
没有括号的话，默认ByName,与@Autowired类似。
由J2EE提供，需要导入包javax.annotation.Resource。
Spring将@Resource注解的name属性解析为bean的名字，type属性则解析为　　　　 　　　　bean的类型。
如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略。


## 3 Spring Web

#### @Controller - 声明Bean
标记在一个类上，使用它标记的类就是一个springmcv Controller对象，分发处理器将会扫描使用了该注解
的类的方法，并检测该方法是否使用了**@RequestMapping** 注解。
@Controller只是定义了一个控制器类，而使用了**@RequestMapping** 注解的方法才是真正处理请求的处理器。

#### @RequestMapping
标记在一个方法或类上，用来处理请求地址映射的注解，用于类上，表示类中所有响应请求处理的方法都是以该地址。

作为父路径，返回值会通过视图解析器解析为实际的物理视图，然后做转发操作。

属性：

- value：指定请求的实际地址。
- method: 指定请求的method类型。
-  consumes: 指定处理请求的内容提交类型（Content-Type）,例如application/josn，text/html。
- produces: 指定返回的内容类型，仅当request请求头中的（Accept）类型中包含该指定类型才返回。
- param: 指定request中必须包含的参数值。
- headers：指定request中必须包含某些指定的header值，才能让改方法处理请求。

#### @PathVariable
用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出uri模板中的变量作为参数。

```Java
@RequestMapping(value="/user/{userId}/roles/{roleId}",method = RequestMethod.GET) 
public String getLogin(@PathVariable("userId") String userId, 
  @PathVariable("roleId") String roleId){ 
  System.out.println("User Id : " + userId); 
  System.out.println("Role Id : " + roleId); 
  return "hello"; 
} 
```

####  @CookieValue
作用：用来获取Cookie中的值；
参数：

- value：参数名称
- required：是否必须
- defaultValue：默认值

```Java
@RequestMapping("/testCookieValue")
public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
    System.out.println("JSESSIONID = " + sessionId);
    return "success";
} 
```

#### @RequestParam
用于将请求参数区数据映射到功能处理方法的参数上，用例：

``` Java
@RequestMapping("/testRequestParam")
public String testRequestParam(@RequestParam("id") int id) {
	System.out.println("testRequestParam  " + id);
	return "success";
}
```

#### @SessionAttributes
将值放到session作用域中，写在class上面。
通过属性名指定需要放到会话中的属性（value 属性值），
通过模型属性的对象类型指定哪些模型属性需要放到会话中（types 属性值）,
用例：
`@SessionAttributes(value = {"user"}, types = {String.class})`

#### @ResponseBody　
将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。

使用时机：返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）

## 4 Spring SpringWeb Flux

## 5. Swagger
