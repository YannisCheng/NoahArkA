#

2021-12-23 14:23:52 周四

**博客：**

- [https://blog.csdn.net/qq_44156131/article/details/104001057](https://blog.csdn.net/qq_44156131/article/details/104001057)
- [SpringBoot 过滤器&拦截器](https://snailclimb.gitee.io/springboot-guide/#/./docs/basis/springboot-interceptor)
- [Spring中的拦截器](https://blog.csdn.net/hongxingxiaonan/article/details/48090075)
- [spring-boot笔记-HandlerInterceptor和MethodInterceptor(AspectJ)（四）](https://blog.csdn.net/u013185616/article/details/72926966)
- [Spring Boot 使用 @Aspect 注解实现 AOP](https://blog.csdn.net/qmqm011/article/details/90172698)
- [SpringBoot使用AOP，PointCut表达式详解以及使用](https://blog.csdn.net/LuQiaoYa/article/details/88233846)
- [springBoot AOP @Before,@Around,@After,@AfterReturn,@AfterThrowing的理解](https://blog.csdn.net/Crystalqy/article/details/104292711)

## 拦截器分类

- **url请求：`HandlerInterceptor`、`Filter`**。

  拦截的目标是请求的地址

 - `Filter`：

  是`Servlet`规范规定的，不属于`spring框架`。 在写Filter时需要自己配置拦截的`urlPatterns`,它适合更粗粒度的拦截，在请求前后做一些编解码处理、Session验证等。

 - `HandlerInterceptor`：

  提供更精细的的控制能力：在request被响应之前、request被响应之后、视图渲染之前以及request全部结束之后。 所以针对请求地址做一些验证、预处理等操作比较合适。
也可以用作计算一个请求的相应时间等。【必须过DispatcherServlet的请求才会被拦截】

- **method：`MethodInterceptor`**

 利用的是`AOP实现机制`，它拦截的目标是`方法`，即使不是controller中的方法。 实现MethodInterceptor拦截器大致也分为2种：

  - 实现`MethodInterceptor接口`；
  - 利用`AspectJ`的`注解`或`配置`。

    利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
    在Spring AOP中业务逻辑仅仅只关注业务本身，将日志记录、性能统计、安全控制、事务处理、异常处理等代码从业务逻辑代码中划分出来，从而在改变这些行为的时候不影响业务逻辑的代码。

