# Genesis

`Genesis` 意为 `创世纪`，是 `《圣经-旧约》`中的第一章。

## 索引

架构组合：
Spring Boot+ SpringWeb Flux + Spring Cloud

### 1. Spring & Spring Framework概念

- [Spring官网](https://spring.io/)
- [spring framework3体系结构及内部各模块jar之间的maven依赖关系](https://www.cnblogs.com/ywlaker/p/6136625.html)

**Spring Framework参考文档**

- [Spring Framework Documentation 4.3.9 - 有架构图](https://docs.spring.io/spring-framework/docs/4.3.9.RELEASE/spring-framework-reference/html/overview.html)
- [Spring Framework Documentation 5.3.x](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/)
- [Spring Framework Documentation 5.3.6](https://docs.spring.io/spring-framework/docs/5.3.6/reference/html/)
- [Spring 入门指南](https://spring.io/guides#topical-guides)

**Spring Framework源码**

- [Github:spring-framework](https://github.com/spring-projects/spring-framework)
- [spring repo home](https://repo.spring.io/webapp/#/home)
- [spring 5.3.7 repo](https://repo.spring.io/webapp/#/artifacts/browse/tree/General/release/org/springframework/spring/5.3.7)
- [download page](https://repo.spring.io/release/org/springframework/spring/5.3.7/)
  
### 2. Spring Boot 2.5.3

- [Spring Boot 参考文档](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Boot 特性](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features)  
- [Spring Boot “操作方法”指南](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.application)
- [Spring Boot Gradle 插件参考指南](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/htmlsingle/)
- Spring Boot的Gradle构建[Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/htmlsingle/)

 附录

- [应用程序属性:](https://docs.spring.io/spring-boot/docs/2.5.3/reference/html/application-properties.html) :可用于配置应用程序的通用应用程序属性
  
### 2. 课程：

- [Spring5框架最新版教程（idea版）](https://www.bilibili.com/video/BV1Vf4y127N5?t=382&p=40)
- [Spring5源代码和相关jar包](https://github.com/mxg133/learnforSpring5)
- [SpringBoot2核心技术与响应式编程](https://www.yuque.com/atguigu/springboot)
- [2021版SpringBoot2](https://www.bilibili.com/video/BV19K4y1L7MT)
- [参考](https://www.bilibili.com/read/cv5216534?spm_id_from=333.788.b_636f6d6d656e74.5)


### 3. 延伸

- [资深程序员建议放弃 JSP 吧，否则你可能“无路可走”！](https://blog.csdn.net/afreon/article/details/111399465)
- [从Java基础、JavaWeb基础到常用的框架再到面试题都有完整的教程，几乎涵盖了Java后端必备的知识点](https://github.com/ZhongFuCheng3y/3y)

Spring Boot不推荐使用jsp，强行使用jsp脱离了Spring Boot易配置、快速开发的初衷。 
Spring推荐 前后分离 的开发方式，不推荐使用任何服务器端模板引擎。
  
## Spring、Spring Framework与Spring Boot的关系
**Spring**
是一个生态的统称。

**Spring Framework** 
是Spring的基础，其定位是为 Tomcat 之类的容器提供web 框架，其核心是ioc控制反转和aop面向切面编程。

**Spring Boot**：

- Spring要配置大量的配置文件, Spring Boot 有很多默认的配置。 
- 使用“约定优于配置”的理念开发，自动装配，使各个功能模块的引入和配置变得非常简单，几乎可以做到0配置。
- 简化我们使用Spring（或者其他常用框架）时的大量配置 ，方便我们快速搭建一个web工程。
- 一句话总结：Spring Boot是Spring的配置封装。


## 热插拔插件
JRebel

## 引入Swagger组件

 - [Spring Boot整合swagger使用教程](https://www.cnblogs.com/progor/p/13297904.html)
 - [swagger2 @ApiImplicitParams](https://www.jianshu.com/p/3299877c5179?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation)
 - [Swagger UI简介-API分组](https://blog.csdn.net/zhanshixiang/article/details/104605292)
 - [](https://github.com/swagger-api/swagger-ui/issues/5969)

## Maven库在线搜索

[Maven库地址](https://mvnrepository.com/)