# Netty

2021-08-07 16:03:40 周六

Linux：

- [本地IO视频课程讲解](file:///Users/yannischeng/Movies/课程/马士兵-IO与存储技术串讲/2.Redis，zookeeper，kafka，Nginx技.mp4)
- [Linux IO模式及 select、poll、epoll详解](https://segmentfault.com/a/1190000003063859)

---

Netty官网：

- [Netty](https://netty.io/)
- [Netty 采用者](https://netty.io/wiki/adopters.html)

---

Netty参考：

- [Netty框架学习之(一）：Netty框架简介](https://blog.csdn.net/eric_sunah/article/details/80424344)
- [Netty 系列之 Netty 高性能之道](https://www.infoq.cn/article/netty-high-performance/#anch111813)
- [彻底理解Netty](https://www.cnblogs.com/shineman-zhang/articles/13884407.html)

---

路线：

- [Netty 学习和进阶策略](https://www.infoq.cn/article/iRfKjNuxYGSVgm5CtIpr)


![netty](/images/IO/netty.png)

## 概念

- Linux network I/O模型
  - Synchronous I/O
    - BIO（Blocking IO），阻塞I/O
    - NIO（Non-Bloacking IO），非阻塞I/O
    - I/O多路复用
  - 异步I/O（Asynchronous IO）
  - epoll
  - poll

- JDK net I/O模型（NIO编程）
 - BIO
 - NIO（多路复用I/O）

## 特点：

 - 高并发
 - 传输快
 - 封装好

 ## JDK I/O模型

 - （JDK）BIO线程模型

 ![BIO图](/images/IO/BIO-socket.png)

要管理多个并发的客户端就需要为每个客户端Socket创建一个新的Thread。

特点：

1. 仅仅因为等待输入输出的数据就绪，则在任何时候都有可能有大量的线程处于休眠状态，导致资源浪费；
2. 需要为每个线程的调用栈分配内存；
3. 即时JVM在物理机上支持大量的线程，但是上下文切换会导致大量开销。

 - （JDK 1.4） - NIO线程模型

 ![NIO图](/images/IO/NIO-Socket.png)

NIO特点：

1. 使用较少的线程处理较多的连接，因此减少了 内存管理 和 上下文切换 带来的开销。
2. 当未有I/O操作的时候，线程可以被用作其他。

## Netty-NIO与JDK-NIO编程关系 

Netty封装了JDK中NIO的API。同时以此为基础：

1. 使用 多路复用技术 ，提高 处理连接的并发性；
2. 零拷贝；
3. 存拷贝



