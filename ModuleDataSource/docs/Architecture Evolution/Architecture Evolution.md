# 演变过程

## 演变过程


### 1. 访问量小+单数据库

![](/images/architecture_evolution/ae01.png)

其中：`DAL` 是数据访问层（`Data Access Layer`）

当遇到一下问题时，便会导致数据存储的瓶颈：

1. 数据量的总大小一个机器放不下时
2. 数据的索引(B+Tree)一个机器的内存放不下时
3. 访问量(读写混合)一个实例不能承受

### 2. Memcached(缓存)+MySQL+垂直拆分

随着访问量的上升，人们开始追求性能，大量的使用缓存技术来缓解数据库的压力，优化数据库的结构和索引。
开始比较流行的是通过文件缓存来缓解数据库压力，但是当访问量继续增大的时候，`多台web机器` 通过文件缓存不能共享，大量的小文件缓存也带了了比较高的IO压力。
在这个时候，`Memcached` 就自然的成为一个非常时尚的技术产品。

![](/images/architecture_evolution/ae02.png)

### Mysql主从读写分离


![](/images/architecture_evolution/ae03.png)



![](/images/architecture_evolution/ae04.png)



![](/images/architecture_evolution/ae05.png)

