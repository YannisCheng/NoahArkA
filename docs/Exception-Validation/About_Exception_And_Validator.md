#

2021-12-24 14:56:04 周五

**博客**


## ControllerAdvice的用法

- [异常处理解决方案](https://snailclimb.gitee.io/springboot-guide/#/./docs/advanced/springboot-handle-exception-plus)
- [@ControllerAdvice 的介绍及三种用法](https://blog.csdn.net/qq_36829919/article/details/101210250)

首先，`ControllerAdvice` 本质上是一个 `Component`，因此也会被当成组建扫描。
这个类是为那些声明了（`@ExceptionHandler`、`@InitBinder` 或 `@ModelAttribute` 注解修饰的）`方法`的 **类** 而提供的专业化的 `@Component` , 以供多个或所有 `Controller类` 所共享。

这是 `aop思想` 的一种实现，你告诉SpringBoot需要拦截规则，它帮你把他们拦下来，具体你想做更细致的 `拦截筛选` 和 `拦截之后` 的处理，
你自己通过 `@ExceptionHandler`、`@InitBinder` 或 `@ModelAttribute` 这三个注解以及被其注解的方法来自定义。

### 全局异常

`@ControllerAdvice` 配合 `@ExceptionHandler` 实现 **全局异常处理**。
如果这2个注解的 `value` 中不填写任何参数，则表示处理没有任何限制的异常处理（方法抛出什么异常都接得住）。

### 预设全局数据
`@ControllerAdvice` 配合 `@ModelAttribute` **预设全局数据**。

### 请求参数预处理
`@ControllerAdvice` 配合 `@InitBinder` 实现对请求参数的预处理


## Validated验证

- [spring boot validation校验](https://snailclimb.gitee.io/springboot-guide/#/./docs/spring-bean-validation)

### 基本使用方式

可以验证任何 `Spring Bean` 的输入：`@Validated`（类） 与 `@Valid`（方法参数） 组合使用

### 验证请求体

### 验证请求参数

### 自定义Validated