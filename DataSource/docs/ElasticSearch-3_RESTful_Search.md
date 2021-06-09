# ElasticSearch — RESTful与搜索

- [官网](https://www.elastic.co/cn/)
- [Elasticsearch Guide](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Elastic Stack](https://www.elastic.co/cn/elastic-stack)


## ElasticSearch RESTful请求

### 格式

**一个 `Elasticsearch 请求` 和任何 `HTTP 请求` 一样由若干相同的部件组成**：


```shell
curl -X<VERB> '<PROTOCOL>://<HOST>:<PORT>/<PATH>?<QUERY_STRING>' -d '<BODY>'
```

### 参数

**被 `< >` 标记的部件**：

- **VERB**

 适当的 `HTTP` 方法 或 谓词 : `GET`、 `POST`、 `PUT`、 `HEAD` 或者 `DELETE`。

- **PROTOCOL**

 `http` 或者 `https`（如果你在 Elasticsearch 前面有一个 https 代理）

- **HOST**

 Elasticsearch 集群中`任意节点`的`主机名`，或者用 `localhost` 代表`本地机器`上的`节点`。

- **PORT**

 运行 Elasticsearch HTTP `服务的端口号`，默认是 9200 。

- **PATH**

 API 的终端`路径`（例如 _count 将返回集群中文档数量）。Path 可能包含多个组件，例如：`_cluster/stats` 和 `_nodes/stats/jvm` 。

- **QUERY_STRING**

 `任意` `可选` 的 `查询字符串参数` (例如 `?pretty` 将格式化地输出 JSON 返回值，使其更容易阅读)

- **BODY**

 一个 `JSON格式` 的`请求体` (如果请求需要的话)
 
## ElasticSearch 返回值格式说明

[搜索结果数据格式](https://www.elastic.co/guide/cn/elasticsearch/guide/current/empty-search.html)

使用如下命令即可搜索：`所有索引下`的`所有文档`

```shell
curl -X GET "localhost:9200/_search?pretty"
```

结果：

```
{
  // 执行整个搜索请求耗费了多少毫秒
  "took" : 28,
  // 查询是否超时。可以指定 timeout 为 10ms : GET /_search?timeout=10ms
  // 应当注意的是 timeout 不是停止执行查询，它仅仅是告知正在协调的节点返回到目前为止收集的结果并且关闭连接。
  // 在后台，其他的分片可能仍在执行查询即使是结果已经被发送了。
  // 使用超时是因为 SLA(服务等级协议)对你是很重要的，而不是因为想去中止长时间运行的查询。
  "timed_out" : false,
  // 在查询中参与分片的总数，以及这些分片成功了多少个失败了多少个。
  "_shards" : {
    "total" : 9,
    "successful" : 9,
    "skipped" : 0,
    "failed" : 0
  },
  
  // 返回结果中最重要的部分。
  "hits" : {
  	// 匹配到的文档总数
    "total" : {
      "value" : 402,
      "relation" : "eq"
    },
    // 该值是与查询所匹配文档的 _score 的最大值
    "max_score" : 1.0,
    // 显示所查询结果的前十个文档
    "hits" : [
      {
        // 类比于传统数据库中的：数据库
        "_index" : ".kibana-event-log-7.12.1-000001",
        // 类比于传统数据库中的：表
        "_type" : "_doc",
        // 类比于传统数据库中的：表中一条record的id
        "_id" : "_QaAfnkBFYGw_2OtF6aU",
        // 衡量了文档与查询的匹配程度，文档是按照 _score 降序排列的
        "_score" : 1.0,
        // 类比于传统数据库中的：表中的一条具体record
        "_source" : {
          "@timestamp" : "2021-05-18T08:03:03.471Z",
          "event" : {
            "provider" : "eventLog",
            "action" : "starting"
          },
          "message" : "eventLog starting",
          "ecs" : {
            "version" : "1.6.0"
          },
          "kibana" : {
            "server_uuid" : "5d9edfaf-f6cd-4b8c-bc32-5dbf6584ea89"
          }
        }
      },
    ]
  }
}
```
 
 
## 搜索(_search)示例


**搜索`一个索引有五个主分片`和搜索`五个索引各有一个分片`准确来所说是`等价`的。**

[多索引，多类型](https://www.elastic.co/guide/cn/elasticsearch/guide/current/multi-index-multi-type.html)

### _search的路径

**1. 格式：**


```shell
/索引(Index)/类型(Type)/_search
```

**2. 具体的使用方式：**

- **/_search**：在`所有的索引`中搜索`所有的类型`

- **/gb/_search**：在 `gb 索引`中搜索`所有的类型`

- **/gb,us/_search**：在 `gb索引` 和 `us索引`中搜索 `所有的文档`

- **/g*,u*/_search**：在`任何以 g 或 u 开头的索引`中搜索 `所有的类型`

- **/gb/user/_search**：在 `gb 索引`中`搜索 user 类型`
 
- **/gb,us/user,tweet/_search**：在 gb 和 us 索引中搜索 user 和 tweet 类型
- **/_all/user,tweet/_search**：在`所有的索引`中搜索 user 和 tweet 类型 

###  向 index(数据库)为：megacrop的插入一条Type(表)为：employee的数据：


	```shell
	curl -H "Content-Type: application/json" -XPUT 'http://localhost:9200/megacrop/employee/1' -d '{
	    "first_name" : "John",
	    "last_name" :  "Smith",
	    "age" :        25,
	    "about" :      "I love to go rock climbing",
	    "interests": [ "sports", "music" ]
	}'
	```


### 通过文档ID搜索


	```shell
	GET /megacrop/employee/1
	```

### 搜索全部


	```shell
	GET /megacrop/employee/_search
	```


### 轻量搜索

 
	```shell
	GET /megacrop/employee/_search?q=last_name:Smith
	```
	
### 查询表达式搜索


	```shell
	GET /megacrop/employee/_search
	{
	  "query":{
	    "match":{
	      "last_name":"Smith"
	    }
	  }
	}
	```
	
	**注意：** 此处的 `轻量搜索` 和 `查询表达式搜索` 虽然接口不同，但是达到的效果是一样的。
	
### 更复杂的表达式搜索

	```shell
	GET /megacrop/employee/_search 
	{
	  "query":{
	    "bool": {
	      "must":{
	        "match":{
	         "last_name":"smith" 
	        }
	      },
	      "filter": {
	        "range":{
	         "age":{"gt":30} 
	        }
	      }
	    }
	  }
	}
	``` 
	
### 全文搜索


	```shell
	GET /megacrop/employee/_search 
	{
	  "query":{
	    "match":{
	      "about":"rock climbing" 
	    }
	  }
	}
	```
	
	结果：
	

	```
	{
	  "took" : 13,
	  "timed_out" : false,
	  "_shards" : {
	    "total" : 1,
	    "successful" : 1,
	    "skipped" : 0,
	    "failed" : 0
	  },
	  "hits" : {
	    "total" : {
	      "value" : 2,
	      "relation" : "eq"
	    },
	    "max_score" : 1.4167401,
	    "hits" : [
	      {
	        "_index" : "megacrop",
	        "_type" : "employee",
	        "_id" : "1",
	        "_score" : 1.4167401,
	        "_source" : {
	          "first_name" : "John",
	          "last_name" : "Smith",
	          "age" : 25,
	          "about" : "I love to go rock climbing",
	          "interests" : [
	            "sports",
	            "music"
	          ]
	        }
	      },
	      {
	        "_index" : "megacrop",
	        "_type" : "employee",
	        "_id" : "2",
	        "_score" : 0.4589591,
	        "_source" : {
	          "first_name" : "Jane",
	          "last_name" : "Smith",
	          "age" : 32,
	          "about" : "I like to collect rock albums",
	          "interests" : [
	            "music"
	          ]
	        }
	      }
	    ]
	  }
	}
	```
	
	搜索结果说明：
	
	**搜索结果排序**：`Elasticsearch` 默认按照 **`相关性``得分`** 排序，即每个文档跟查询的`匹配程度`。
	 
	 第一个最高得分的结果很明显：John Smith 的 about 属性清楚地写着 “rock climbing” 。
	 但为什么 Jane Smith 也作为结果返回了呢？原因是她的 about 属性里提到了 “rock” 。因为只有 “rock” 而没有 “climbing” ，所以她的相关性得分低于 John 的。
	 `Elasticsearch`中的 `相关性` 概念非常重要，也是完全区别于`传统关系型数据库`的一个概念。
	 
### 短语搜索


	```shell
	GET /megacrop/employee/_search 
	{
	  "query":{
	    "match_phrase":{
	      "about":"rock climbing" 
	    }
	  }
	}
	```
	
	结果：
	
	
	```
	{
	  "took" : 16,
	  "timed_out" : false,
	  "_shards" : {
	    "total" : 1,
	    "successful" : 1,
	    "skipped" : 0,
	    "failed" : 0
	  },
	  "hits" : {
	    "total" : {
	      "value" : 1,
	      "relation" : "eq"
	    },
	    "max_score" : 1.4167401,
	    "hits" : [
	      {
	        "_index" : "megacrop",
	        "_type" : "employee",
	        "_id" : "1",
	        "_score" : 1.4167401,
	        "_source" : {
	          "first_name" : "John",
	          "last_name" : "Smith",
	          "age" : 25,
	          "about" : "I love to go rock climbing",
	          "interests" : [
	            "sports",
	            "music"
	          ]
	        }
	      }
	    ]
	  }
	}
	```

### 查询结果高亮


	```shell
	GET /megacrop/employee/_search 
	{
	  "query":{
	    "match_phrase":{
	      "about":"rock climbing" 
	    }
	  },
	  "highlight":{
	    "fields": {
	      "about": {}
	    }
	  }
	}
	```
	
	结果：
	
	
	```
	{
	  "took" : 331,
	  "timed_out" : false,
	  "_shards" : {
	    "total" : 1,
	    "successful" : 1,
	    "skipped" : 0,
	    "failed" : 0
	  },
	  "hits" : {
	    "total" : {
	      "value" : 1,
	      "relation" : "eq"
	    },
	    "max_score" : 1.4167401,
	    "hits" : [
	      {
	        "_index" : "megacrop",
	        "_type" : "employee",
	        "_id" : "1",
	        "_score" : 1.4167401,
	        "_source" : {
	          "first_name" : "John",
	          "last_name" : "Smith",
	          "age" : 25,
	          "about" : "I love to go rock climbing",
	          "interests" : [
	            "sports",
	            "music"
	          ]
	        },
	        "highlight" : {
	          "about" : [
	            "I love to go <em>rock</em> <em>climbing</em>"
	          ]
	        }
	      }
	    ]
	  }
	}
	```



### 分页


```
GET /_search?size=5&from=5
```

- `size`：显示应该 `返回` 的结果数量，默认是 10
- `from`：显示应该 `跳过` 的初始结果数量，默认是 0


