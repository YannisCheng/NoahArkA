# Redis Use

2021-08-05 11:25:11 周四

参考：

 - [springboot redis 项目实战 完整篇](https://www.jianshu.com/p/5596c3a4978d)
 - [springboot集成redis (Lettuce)](https://blog.csdn.net/u014082714/article/details/105294268/)
 - [基于Spring Boot2.x使用Redis(Lettuce)详细教程---Redis使用环境配置 (一)](https://blog.csdn.net/jetty_welcome/article/details/104866487?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-1.control&spm=1001.2101.3001.4242)
 - [Spring Boot 2.X(六)：Spring Boot 集成 Redis](https://www.cnblogs.com/zwqh/p/11664782.html)
 - [Springboot2.2 Redis多个实例简单的手动配置](https://blog.csdn.net/chenypgg/article/details/85698209)

API操作：

 - [SpringBoot集成Redis 开始使用操作String、List、hash、Zset数据类型](https://blog.csdn.net/weixin_44806772/article/details/106083152)
 - [Redis中opsForValue()方法的使用介绍](https://blog.csdn.net/m0_55208404/article/details/113728643)

数据持久化：


数据恢复：



问题总结：

问题1：
2021-08-05 19:39:21.146 ERROR 13654 --- [nio-8089-exec-4] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/NoahArk] threw exception [Request processing failed; nested exception is org.springframework.data.redis.serializer.SerializationException: Could not read JSON: Invalid UTF-8 start byte 0x9b
at [Source: (byte[])"曹操"; line: 1, column: 3]; nested exception is com.fasterxml.jackson.core.JsonParseException: Invalid UTF-8 start byte 0x9b
at [Source: (byte[])"曹操"; line: 1, column: 3]] with root cause


问题2：
No primary or single public constructor found for interface java.util.List - and no default constructor found either] with root cause