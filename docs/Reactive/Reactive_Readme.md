# Spring WebFlux

2021-08-06 21:50:32 周五

参考：

Reactive共识：

 - [Reactive Streams](https://www.reactive-streams.org/)
  

Spring Reactive：

 - [Spring Reactive](https://spring.io/reactive)
 - [Spring官网：Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux)

Project Reactor：

 - [Project Reactor官网](https://projectreactor.io/)
 - [reactor响应流式库](https://github.com/reactor/reactor)
 - [Reactor3英文文档](https://projectreactor.io/docs/core/release/reference/)   
 - [Reacotr3中文文档](https://htmlpreview.github.io/?https://github.com/get-set/reactor-core/blob/master-zh/src/docs/index.html)
 - [Reactor：响应式编程描述](https://htmlpreview.github.io/?https://github.com/get-set/reactor-core/blob/master-zh/src/docs/index.html#intro-reactive)

参考：

 - [Stream, RxJava, Reactor区别](https://blog.csdn.net/mmlz00/article/details/86249161)
 - [外行人都能看懂的WebFlux，错过了血亏！](https://www.cnblogs.com/Java3y/p/11880377.html)
 - [响应式Spring的道法术器（Spring WebFlux 教程）](https://blog.csdn.net/get_set/article/details/79466657)
 - [10大主流压力测试工具](https://blog.csdn.net/langzitianya/article/details/81479422)
 - [Spring Boot ： Webflux 和 MVC 性能对比](https://blog.csdn.net/m0_46413295/article/details/106634819)

## 名词：


响应（Reactive）
响应式流（Reactive Stream）
响应式编程（Reactive Programming）
命令式编程

---

Propagation of Change（变化传递）
Data-Stream（数据流）
Declarative（声明式）
Lambda表达式



## Reactive 规范

`Reactive规范`是 `JVM Reactive 扩展规范` 中的 `Reactive Streams JVM`，而 `Reactive 框架` 最典型的实现是：

- **Java 9 ：Flow API**
- **ReactiveX：RxJava**
- **Spring 5：Reactor**

### Reactive Streams（规范）

#### 是什么

`Reactive Streams` 是 `JVM` 面向`Stream库`的`标准`和`规范`，包括：

- 处理潜在无限数量的元素；
- 按顺序；
- 在组件之间异步传递元素；
- 具有强制性非阻塞背压。

#### 目的是：

为 `非阻塞` `背压`  的 `异步` `流处理` 提供标准。包括针对运行时环境（JVM和JavaScript）以及网络协议的工作。

#### 目标是：

管理 `跨异步边界` 的 `数据流交换` ——  将元素传递至另一个线程或者线程池。

#### API的组成

- **Publisher：发布者（生产者）**
- **Subscriber：订阅者（消费者）**
- **Subscription：订阅**
- **Processor：处理器**

## "响应（Reactive）"是谁响应谁？
 
服务端推送（push）数据到客户端后，服务端再响应客户端的反应的需求，服务端以作出调整。

## 响应式编程（Reactive Programming）

 ```
Reactive Programming：一种技术，各自表达。
 ```

与 命令式编程 对应。

响应式编程式是 异步非阻塞 式的。
响应式编程是一种基于 数据流 和 变化传递(异步) 的 声明式 的编程范式。
作为面向对象编程中"观察者模式"的一种扩展。

其中：

数据流：由 Lambda表达式+Stream 配合实现
变化传递：指后续子值（自变量）的变化仍能继续影响整体（因变量）值。
声明式：开发人员通过 描述控制流程 来定义对 被推送数据 的处理逻辑（API名称）。

### 概念解释

- **维基百科**：

 是一种声明式的编程规范，其核心要素是数据流（data streams ）与其传播变化（ propagation of change），前者是关于数据结构的描述，包括静态的数组（arrays）和动态的事件发射器（event emitters）。像是Java中的：

 - 数据流：Java 8 Stream
 - 传播变化：Java Observable/Observer
 - 事件/监听：Java EventObject/EventListener

- **ReactiveX、Reactor**

 对`观察者模式`进行了扩展：通过 **操作符（Operators）** 对 **数据/事件序列（Sequence of data or events）** 进行操作，并且 **屏蔽了并发细节**，如线程 API（Exectuor 、Future、Runnable）、同步、线程安全、并发数据结构以及非阻塞 I/O。
 
- **Java API**

 Java8中的 `Stream API` 也包含操作符，也屏蔽了并发细节，在数据结构方面Stream基本上就是数据流。与 `ReactiveX` 不同的是在 `设计模式` 上。

  - **Stream** 使用的是 `迭代器模式（Iterator）`；
  - **ReactiveX** 使用的是 `观察者模式（Observer）`

 两者的差异为：
 
  - 迭代器模式（Iterator）属于：`拉（pull）`模式
  - 观察者模式（Observer）属于：`推（push）`模式

- **来自 André Staltz**
  
  `In a way, this isn't anything new. Event buses or your typical click events are really an asynchronous event stream, on which you can observe and do some side effects. Reactive is that idea on steroids. You are able to create data streams of anything, not just from click and hover events. Streams are cheap and ubiquitous, anything can be a stream: variables, user inputs, properties, caches, data structures, etc.`
 
  
### Reactive Programming 使用场景

- **Reactive Streams JVM**

 `Reactive Streams` 用于在 **异步边界**（asynchronous boundary） 管理 **流式数据交换**（ govern the exchange of stream data）。异步说明其并发模型，流式数据则体现数据结构，管理则强调它们的它们之间的协调。
 
- **Spring 5**

 Spring 认为 `Reactive` 和 `非阻塞` 通常并非让应用运行更快速（generally do not make applications run faster），甚至会增加少量的处理时间，因此，它的使用场景则是 **性能：利用较少的资源，提升应用的伸缩性**（scale with a small, fixed number of threads and less memory）。
 
- **ReactiveX**

 `ReactiveX` 所描述的使用场景与 `Spring` 的不同，它没有从性能入手，而是 **代码可读性** + **减少Bugs** 的角度出发，解释了 `Reactive Programming 的价值`。
 
  同时，强调其框架的核心特性：**异步**（asynchronous）、**同顺序**（same sort）和 **组合操**作（composable operations）。它也间接地说明了：`Java 8 Stream` 在 `组合操作` 的限制，以及` 操作符` 的不足。
  
- **Reactor**

  同样强调 **结构性** 、**可读性**（Composability and readability）和 **高层次并发抽象**（High level abstraction），并明确地表示它提供 `丰富的数据操作符`（ rich vocabulary of operators）弥补 `Stream API` 的短板，还支持` 背压`（Backpressure）操作，提供数据生产者和消费者的消息机制，协调它们之间的产销失衡的情况。同时，Reactor 采用 **订阅式数据消费（Nothing happens until you subscribe）** 的机制，实现 Stream 所不具备的数据推送机制。

### 总结

- **Reactive Programming** 作为 **观察者模式（Observer）的延伸**，不同于传统的命令编程方式（ Imperative programming）同步拉取数据的方式，如迭代器模式（Iterator） 。而是采用数据发布者同步或异步地推送到数据流（Data Streams）的方案。当该数据流（Data Steams）订阅者监听到传播变化时，立即作出响应动作。

- 在实现层面上：**Reactive Programming** 可结合 **函数式编程** 简化面向对象语言语法的臃肿性，屏蔽并发实现的复杂细节，提供数据流的有序操作，从而达到提升代码的可读性，以及减少 Bugs 出现的目的。

- 同时，**Reactive Programming** 结合 **背压**（Backpressure）的技术解决发布端生成数据的速率高于订阅端消费的问题。

**简单归纳为：**

 - **设计模式** - `观察者模式`
 - **数据/事件序列** -`数据结构` 或者 `数据流`
 - **操作符** - `数据操作`
 - **并发模型** - `线程`
 - **支持背压** - `解决数据的生产与消费速度问题`

## 响应式流（Reactive Stream）

是异步非阻塞+流量控制。
通过定义一组实体，接口和互操作方法，给出了实现 异步非阻塞背压 的标准。
第三方遵循这个标准来实现具体的解决方案，常见的有Reactor，RxJava，Akka Streams，Ratpack等。

### 背压（back pressure）

消费者能够反向告知生产者生产内容的速度的能力。

## Spring WebFlux
是一个 响应式编程 技术栈。
是Spring推出的 响应式编程 的一部分（Web端：WebFlux）
是基于SpringMVC库的。
使用的 响应式流 不是JDK9，而是 Reactor响应流式库。

## Reactor响应流式库

