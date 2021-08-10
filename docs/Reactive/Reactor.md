# Reactor

2021-08-09 21:00:58 周一


参考：

Project Reactor：

- [Project Reactor 官网](https://projectreactor.io/)
- [Project Reactor 参考文档和 Javadoc](https://projectreactor.io/docs)
- [Reactor3 参考指南 官网](https://projectreactor.io/docs/core/release/reference/)
- [Reacotr3 中文文档](https://htmlpreview.github.io/?https://github.com/get-set/reactor-core/blob/master-zh/src/docs/index.html)

Github

- [Github Reactor响应流式库](https://github.com/reactor/reactor)

## Spring WebFlux

是一个 响应式编程 技术栈。
是Spring推出的 响应式编程 的一部分（Web端：WebFlux）
是基于SpringMVC库的。
使用的响应式流不是 JDK9，而是 Reactor响应流式库。

## Reactive

### 基本概念

反应式系统具有某些特性，使其成为低延迟、高吞吐量工作负载的理想选择。
Project Reactor 和 Spring 产品组合协同工作，使开发人员能够构建具有响应性、弹性、弹性和消息驱动的企业级反应式系统。

什么是反应式处理？
响应式处理是一种范例，它使开发人员能够构建可以处理背压（流控制）的非阻塞、异步应用程序。

为什么要使用反应式处理？
反应式系统更好地利用现代处理器。此外，在反应式编程中包含背压可确保解耦组件之间具有更好的弹性。

### Project Reactor


Project Reactor 是一个完全无阻塞的基础，包括背压支持。
它是 Spring 生态系统中反应式堆栈的基础，并在 Spring WebFlux、Spring Data 和 Spring Cloud Gateway 等项目中具有特色。

### Reactive Microservices

开发人员从阻塞代码转向非阻塞代码的主要原因之一是效率。
反应式代码用更少的资源做更多的工作。
Project Reactor 和 Spring WebFlux 使开发人员能够利用多核下一代处理器——处理潜在的大量并发连接。
通过反应式处理，您可以用更少的微服务实例满足更多的并发用户。

### 使用 Spring Boot 的 Reactive Microservices

Spring 产品组合提供两个并行堆栈。
一种是基于带有 Spring MVC 和 Spring Data 构造的 Servlet API。
另一个是利用 Spring WebFlux 的完全反应式堆栈 和 Spring Data 反应式存储库。
在这两种情况下，Spring Security 都为您提供了对这两个堆栈的本机支持。

![Spring MVC与Spring WebFlux](/images/Reactive/SpringMVC与SpringWebFlux.png)

### 与常用技术的集成

以反应方式访问和处理数据很重要。
MongoDB、Redis 和 Cassandra 在Spring Data，许多关系数据库（Postgres、Microsoft SQL Server、MySQL、H2 和 Google Spanner）通过R2DBC，
在消息传递的世界里，Spring Cloud Stream  还支持对 RabbitMQ 和 Kafka 等平台的反应式访问。

## Reactor-core库

```Java
// 订阅并触发序列。
subscribe();

// 对每个产生的价值做一些事情。
subscribe(Consumer<? super T> consumer);

// 处理值的同时也要对错误做出反应。
subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer);

// 处理值和错误，但也会在序列成功完成时运行一些代码。
subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer);

// 处理值和错误并成功完成，但也要处理 Subscription此subscribe调用产生的内容。
subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer,
          Consumer<? super Subscription> subscriptionConsumer);
```

取消Subscriber：

Reactor 中由通用Disposable接口表示在不需要更多数据时取消订阅。取消后，源应停止生成值并清理它创建的任何资源。