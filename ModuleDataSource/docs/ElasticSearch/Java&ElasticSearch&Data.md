# Java&ElasticSearch&Data

2021-07-16 16:31:53 周五

官方：

 - **Elastic**：[ElasticSearch的Java API](https://www.elastic.co/guide/en/elasticsearch/reference/current/api-java.html)
 - **SpringBoot**：[Spring Data Elasticsearch - 参考文档](https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#preface)

参考实例：

 - [开发学院-ElasticSearch 7 教程](https://www.kaifaxueyuan.com/server/elasticsearch7/elasticsearch-index.html)
 - [高途课堂-elasticsearch系列](https://blog.csdn.net/m0_37135421/article/details/104119720)
 - [ElasticSearch7.6.2 JavaAPI创建索引并设置IK分词](https://blog.csdn.net/GhostGuest/article/details/109760660)
 - [Elastic Stack系列】第一章：概述与目录](https://twocups.cn/index.php/2021/02/20/24/)

博客参考（ *评论区的内容值得借鉴* ）：
 - [完整教程：spring-boot-starter-data-elasticsearch整合elasticsearch 6.x](https://blog.csdn.net/chengyuqiang/article/details/86135795)
 - [完整教程：Springboot 2.2整合elasticsearch 7.x (spring-boot-starter-data-elasticsearch)](https://blog.csdn.net/chengyuqiang/article/details/102938266)

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


