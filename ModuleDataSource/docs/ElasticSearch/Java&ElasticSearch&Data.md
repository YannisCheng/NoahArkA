# Java&ElasticSearch&Data

2021-07-16 16:31:53 周五

官方：

 - **Elastic**：[ElasticSearch的Java API](https://www.elastic.co/guide/en/elasticsearch/reference/current/api-java.html)
 - **SpringBoot**：
   
    - [Spring Data Elasticsearch - 参考文档](https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#preface)
    - [Spring Data Repositories - 重点基础知识](https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#repositories)
    - [Spring Data JPA 在方法名中支持的关键字](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)

参考实例：

 - [开发学院-ElasticSearch 7 教程](https://www.kaifaxueyuan.com/server/elasticsearch7/elasticsearch-index.html)
 - [高途课堂-elasticsearch系列](https://blog.csdn.net/m0_37135421/article/details/104119720)
 - [ElasticSearch7.6.2 JavaAPI创建索引并设置IK分词](https://blog.csdn.net/GhostGuest/article/details/109760660)
 - [Elastic Stack系列】第一章：概述与目录](https://twocups.cn/index.php/2021/02/20/24/)

博客参考：

 - *评论区的内容值得借鉴* [完整教程：spring-boot-starter-data-elasticsearch整合elasticsearch 6.x](https://blog.csdn.net/chengyuqiang/article/details/86135795)
 - *评论区的内容值得借鉴* [完整教程：Springboot 2.2整合elasticsearch 7.x (spring-boot-starter-data-elasticsearch)](https://blog.csdn.net/chengyuqiang/article/details/102938266)
 - [Elasticsearch搜索引擎一些参数含义和用法](https://blog.csdn.net/qq_44695727/article/details/107164037)
 - [Elasticsearch实战篇——Spring Boot整合ElasticSearch](https://segmentfault.com/a/1190000018625101)
 - [RestHighLevelClient使用](https://www.cnblogs.com/cicada-smile/p/14322789.html)
 - [RestHighLevelClient操作7.4.2（一）之java中的增删改查](https://blog.csdn.net/m0_37635053/article/details/108438105)

## SpringBoot结合ELasticSearch启动成功log

### 服务连接成功log

```text
2021-12-15 16:31:33.863 TRACE org.elasticsearch.client.RequestLogger 83 logResponse - curl -iX GET 'http://es.yannises.cn/'
# HTTP/1.1 200 OK
# Content-Type: application/json; charset=UTF-8
# Date: Wed, 15 Dec 2021 08:31:33 GMT
# Content-Length: 532
#
# {
#   "name" : "node-1",
#   "cluster_name" : "ES-Cluster",
#   "cluster_uuid" : "knZojVKySVWxuOndm01Q2w",
#   "version" : {
#     "number" : "7.12.1",
#     "build_flavor" : "default",
#     "build_type" : "tar",
#     "build_hash" : "3186837139b9c6b6d23c3200870651f10d3343b7",
#     "build_date" : "2021-04-20T20:56:39.040728659Z",
#     "build_snapshot" : false,
#     "lucene_version" : "8.8.0",
#     "minimum_wire_compatibility_version" : "6.8.0",
#     "minimum_index_compatibility_version" : "6.0.0-beta1"
#   },
#   "tagline" : "You Know, for Search"
# }
```

### SpringBoot健康检测log

```text
2021-12-15 16:31:35.355 TRACE org.elasticsearch.client.RequestLogger 83 logResponse - curl -iX GET 'http://es.yannises.cn/_cluster/health/'
# HTTP/1.1 200 OK
# Content-Type: application/json; charset=UTF-8
# Date: Wed, 15 Dec 2021 08:31:35 GMT
# Content-Length: 390
#
# {"cluster_name":"ES-Cluster","status":"green","timed_out":false,"number_of_nodes":2,"number_of_data_nodes":2,"active_primary_shards":217,"active_shards":434,"relocating_shards":0,"initializing_shards":0,"unassigned_shards":0,"delayed_unassigned_shards":0,"number_of_pending_tasks":0,"number_of_in_flight_fetch":0,"task_max_waiting_in_queue_millis":0,"active_shards_percent_as_number":100.0}
```

## ElasticSearch 连接异常

- 本地网络异常
- 本地Spring连接配置异常（端口自动追加）
- 远程服务器异常
- 转发公网异常

## ElasticSearch Repository 与 RestHighLevelClient 的关系

![RestHighLevelClientt](/images/ElasticSearch/RestHighLevelClientt.png)

## 操作类

- `IndexOperations`：定义索引级别的操作，例如创建或删除索引
- `DocumentOperations`：定义基于 id 存储、更新和检索实体的操作。结果只会返回找到的实体。
- `SearchOperations`：定义使用查询搜索多个实体的操作。每个实体都可以获得附加信息。
- `ElasticsearchOperations`：结合了DocumentOperations和SearchOperations接口。

![操作类](/images/ElasticSearch/ElasticsearchOperations.png)

## 构造查询条件

![构造查询条件1](/images/ElasticSearch/QueryBuilder.png)

![构造查询条件2](/images/ElasticSearch/ElasticSearch-Query.png)

## 查询结果类型

![查询结果类型](/images/ElasticSearch/Search-Result.png)

每个`实体`都被包装在一个包含此实体特定附加信息的`SearchHit`对象中。这些`SearchHit对象`本身在一个`SearchHits对象`中返回，该对象还包含有关整个搜索的信息，如maxScore或请求的聚合。

- `SearchHit<T>`：包含以下信息：

  ID
  分数
  排序值
  突出显示字段
  检索到的 <T> 类型的实体
- `SearchHits<T>`：包含以下信息：

  总命中数
  总点击数关系
  最高分
  SearchHit<T>对象列表
  返回的聚合

- `SearchPage<T>`：定义一个包含 SearchHits<T> 元素的 Spring Data Page，可用于使用存储库方法进行分页访问。
- `SearchScrollHits<T>`：由 ElasticsearchRestTemplate 中的低级scroll API 函数返回，它用 Elasticsearch 的 scroll ID 丰富了 SearchHits<T>。
- `SearchHitsIterator<T>`：SearchOperations 接口的streaming函数返回的迭代器。

