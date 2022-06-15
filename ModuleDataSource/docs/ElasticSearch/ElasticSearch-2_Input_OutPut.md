# ElastictSearch - 输入与输出

## 概念

如果将`对象`按`对象的方式`来`存储`，这样就能更加专注于 `使用` 数据，而不是在电子表格的`局限性`下对`应用建模`。 
我们可以重新利用对象的`灵活性`。

一个 `对象` 是基于`特定语言`的`内存`的`数据结构`。为了通过网络发送或者存储它，我们需要将它表示成某种标准的格式。 
`JSON` 是一种以`人可读的文本`表示`对象`的方法。 
`JSON` 已经变成 `NoSQL` 世界`交换数据`的`事实标准`。
当一个对象被`序列化`成为 `JSON`，它被称为一个 `JSON 文档` 。


`Elastcisearch` 是`分布式`的 `文档存储`。
它能​以 `实时` 的方式 `存储` 和 `检索` 复杂的数据结构—​被`序列化`的`JSON文档`。 
换句话说，一旦一个文档被存储在 Elasticsearch 中，它就是可以被`集群`中的`任意节点`检索到。

在 `Elasticsearch` 中， 每个`字段`的 `所有数据` 都是 `默认被索引` 的 ，即：每个字段都有为了快速检索设置的专用 `倒排索引`。而且，不像其他多数的数据库，它能在 `同一个` 查询中使用`所有`这些倒排索引，并以惊人的速度返回结果。

## 文档

通常情况下，我们使用的术语 `对象` 和 `文档` 是可以`互相替换`的。不过，有一个区别： 
一个`对象`仅仅是类似于 hash 、 hashmap 、字典或者关联数组的 `JSON 对象`，对象中也可以嵌套其他的对象，对象可能包含了另外一些对象。
在 `Elasticsearch` 中，术语 `文档` 有着特定的含义，它是指 `最顶层`或者`根` **对象**, 这个`根对象`被`序列化`成 JSON 并`存储`到 Elasticsearch 中，指定了`唯一 ID`。


## 文档元数据

文档构成：`实际数据` + `元数据` 。 3个必须的元数据元素如下：

**1. 实际数据**
**2. 元数据**
- `_index`：文档的 `存放位置`
- `_type`：文档表示的 `对象类别`
- `_id`：文档 `唯一标识`

### _index

**命名要求：**索引名`必须小写`，`不能以下划线开头`，`不能包含逗号`。

一个 `索引` 应该是 `因共同特性` 被`分组到一起`的`文档集合`。 
例如，你可能存储所有的产品在索引 products 中，而存储所有销售的交易到索引 sales 中。 

实际上，在 `Elasticsearch` 中，我们的数据是被`存储`和`索引`在 **分片** 中，而`一个索引`仅仅是`逻辑`上的`命名空间`， 这个命名空间由`一个`或者`多个分片`**组合**在一起。 

然而，这是一个内部细节，我们的应用程序根本不应该关心分片，对于应用程序而言，只需知道`文档`位于一个 `索引` 内， 而Elasticsearch 会处理所有的细节。

### _type

**命名要求：**可以是`大写`或者`小写`，但是`不能以下划线`或者`不能以句号开头`，`不应该包含逗号`， 并且长度限制为256个字符。

数据可能在`索引`中只是`松散`的组合在一起，但是通常`明确定义`一些数据中的`子分区`是很有用的。 例如，所有的产品都放在一个索引中，但是你有许多不同的产品类别，比如 "electronics" 、 "kitchen" 和 "lawn-care"。

这些文档共享一种相同的（或非常相似）的模式：他们有一个标题、描述、产品代码和价格。他们只是正好属于“产品”下的一些子类。

`Elasticsearch` 公开了一个称为 `types （类型）`的特性，它允许您在`索引`中对数据进行`逻辑分区`。不同 types 的文档可能有不同的字段，但最好能够非常相似。



### _id


`ID` 是一个`字符串`，`_id` + `_index` + `_type` 组合就可以 **唯一确定** `Elasticsearch` 中的`一个文档`。 
当你创建一个新的文档，要么提供自己的 _id ，要么让 Elasticsearch 帮你生成。

## 索引(创建)文档 — PUT/POST

一个文档的位置由唯一标识： `_index` + `_type` + `_id`  确定。

### 使用自定义的 ID — PUT

格式：


```shell
PUT /{index}/{type}/{id}
{
  "field": "value",
  ...
}
```

其中 **PUT** 的含义为 `使用这个 URL 存储这个文档`

例子：

```shell
PUT /website/blog/123
{
    "title": "My first blog entry",
  "text":  "Just trying this out...",
  "date":  "2014/01/01"
}
```

结果：


```shell
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 1,
  "resultBase" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
```

**一个新元素 `_version`**:
在 Elasticsearch 中每个`文档`都有一个`版本号`。当每次对文档进行`修改`时（包括删除）， `_version` 的值会`递增`。 

### 自动生成ID — POST


例子：


```shell
POST /website/blog/
{
  "title": "My second blog entry",
  "text":  "Still trying this out...",
  "date":  "2014/01/01"
}
```

其中 **POST**的含义是 `存储文档在这个 URL 命名空间下`。

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "YzE45HkBZFS3pNflwGSU",
  "_version" : 1,
  "resultBase" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}
```

`自动生成的 ID` 是` URL-safe`、 基于 `Base64 编码`且长度为`20个字符`的 `GUID 字符串`。 
这些 GUID 字符串由可修改的 FlakeID 模式生成，这种模式允许多个节点并行生成唯一 ID ，且互相之间的冲突概率几乎为零。

## 取回一个文档 — GET

检索出文档，我们仍然使用相同的 `_index` , `_type` , 和 `_id` 

### 取回全部

例如：


```shell
GET /website/blog/123?pretty
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 1,
  "_seq_no" : 0,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "title" : "My first blog entry",
    "text" : "Just trying this out...",
    "date" : "2014/01/01"
  }
}
```

`GET` 请求的响应体包括 `{"found": true}` ，这证实了文档已经被找到。 
如果我们请求一个不存在的文档，我们仍旧会得到一个 JSON 响应体，但是 `found` 将会是 `false` 。

### 取回一部分

例子：


```shell
GET /website/blog/123?_source=title,text
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 1,
  "_seq_no" : 0,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "text" : "Just trying this out...",
    "title" : "My first blog entry"
  }
}
```

### 仅取回source字段数据


```shell
GET /website/blog/123/_source
```

结果：


```
{
  "title" : "My first blog entry",
  "text" : "Just trying this out...",
  "date" : "2014/01/01"
}
```

## 检查文档是否存在 — HEAD

`HEAD` 请求`没有返回体`，只返回一个 `HTTP 请求报头`。


```shell
HEAD /website/blog/123
```

结果：


```
200 - OK
```


## 更新整个文档 — 重建索引
在 `Elasticsearch` 中`文档`是 **不可改变** 、**不可修改**，只能 **被替换**。


相反，如果想要 **更新** `现有文档`，需要 ：**重建索引** 或者进行 **替换**， 我们可以使用相同的 index API 进行实现。

例子：


```
PUT /website/blog/123
{
  "title": "My first blog entry",
  "text":  "I am starting to get the hang of this...",
  "date":  "2014/01/02",
  "tag":"niubi"
}
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 2,
  "resultBase" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 2,
  "_primary_term" : 1
}
```

在 `Elasticsearch` 内部已将`旧文档`标记为`已删除`，并`增加`一个`全新`的文档。 
尽管你不能再对`旧版本`的文档进行访问，但它并`不会立即消失`。
当继续索引更多的数据，`Elasticsearch` 会在`后台清理`这些`已删除文档`。

执行以下过程：

1. 从旧文档构建 JSON
2. 更改该 JSON
3. 删除旧文档
4. 索引一个新文档


## 创建新文档 — /_create

`_index` 、 `_type` 和 `_id` 的 **组合** 可以`唯一标识`一个文档。

###  默认创建ID
确保创建一个新文档的最简单办法是，使用索引请求的 POST 形式让 Elasticsearch `自动生成唯一 _id `:

### 自定义ID

如果已经有自己的 _id ，那么我们必须告诉 Elasticsearch ，只有在`相同`的 `_index` 、 `_type` 和 `_id 不存在`时才接受我们的索引请求。
这里有两种方式，他们做的实际是`相同`的事情。使用哪种，取决于哪种使用起来更方便。


1. **使用 `op_type` 查询-字符串参数：**


	```
	PUT /website/blog/123?op_type=create
	{ ... }
	```

2. **在 `URL` 末端使用 `/_create` :**



	```
	PUT /website/blog/123/_create
	{ ... }
	```

例子-重复：


```
PUT /website/blog/123/_create
{
  "title" : "My first blog entry",
  "text" : "I am starting to get the hang of this...",
  "date" : "2014/01/02",
  "tag" : "niubi"
}
```

结果：


```
{
  "error" : {
    "root_cause" : [
      {
        "type" : "version_conflict_engine_exception",
        "reason" : "[123]: version conflict, document already exists (current version [2])",
        "index_uuid" : "c2wJaiSuSdWO7sQSVBZfzw",
        "shard" : "0",
        "index" : "website"
      }
    ],
    "type" : "version_conflict_engine_exception",
    "reason" : "[123]: version conflict, document already exists (current version [2])",
    "index_uuid" : "c2wJaiSuSdWO7sQSVBZfzw",
    "shard" : "0",
    "index" : "website"
  },
  "status" : 409
}
```

例子-新建


```
PUT /website/blog/124/_create
{
  "title" : "My first blog entry",
  "text" : "I am starting to get the hang of this...",
  "date" : "2014/01/02",
  "tag" : "niubi"
}
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "124",
  "_version" : 1,
  "resultBase" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 3,
  "_primary_term" : 1
}
```

## 删除 — DELETE

```
DELETE /website/blog/124/
```

数据存在时，结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "124",
  "_version" : 2,
  "resultBase" : "deleted",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 4,
  "_primary_term" : 1
}
```

数据不存在时，结果：

```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "124",
  "_version" : 3,
  "resultBase" : "not_found",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 5,
  "_primary_term" : 1
}
```

即使文档不存在`"resultBase" : "not_found"`， `_version` 值`仍然会增加`。
这是 Elasticsearch 内部记录本的一部分，用来确保`这些改变`在`跨多节点`时以`正确的顺序执行`。


## 处理冲突

![](/images/deal_chongtu.png)

变更越频繁，读数据和更新数据的间隙越长，也就越可能丢失变更。

在数据库领域中，有两种方法通常被用来确保并发更新时变更不会丢失：

- **悲观并发控制**

 这种方法被关系型数据库广泛使用，它假定有变更冲突可能发生，因此`阻塞访问资源以防止冲突`。
  一个典型的例子是读取一行数据之前先将其锁住，确保只有放置锁的线程能够对这行数据进行修改。

- **乐观并发控制**

 `Elasticsearch` 中使用的这种方法`假定冲突是不可能发生的，并且不会阻塞正在尝试的操作`。
  然而，如果源数据在读写当中被修改，更新将会失败。应用程序接下来将决定该如何解决冲突。 
  例如，可以重试更新、使用新的数据、或者将相关情况报告给用户。

## 乐观并发控制 — if_seq_no和if_primary_term


`Elasticsearch` 是`分布式`的。当文档创建、更新或删除时， `新版本`的文档`必须复制`到`集群`中的`其他节点`。`Elasticsearch` 也是`异步`和`并发`的，这意味着这些`复制请求`被`并行发送`，并且到达目的地时也许 `顺序是乱的` 。 `Elasticsearch` 需要一种方法确保文档的`旧版本不会覆盖新的版本` — **_version（版本号）**，当文档被修改时版本号递增。 
`Elasticsearch` 使用这个 `if_seq_no` 和 `if_primary_term` 来确保`变更`以`正确顺序`得到执行。

例子：

创建一个新blog：


```
PUT /website/blog/1/_create
{
  "title": "My first blog entry",
  "text":  "Just trying this out..."
}
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "1",
  "_version" : 1,
  "resultBase" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 7,
  "_primary_term" : 1
}
```

指定`if_seq_no` 和 `if_primary_term` 来`重建文档索引`：


```
PUT /website/blog/1?if_seq_no=7&if_primary_term=1
{
  "title": "My first blog entry",
  "text":  "Starting to get the hang of this..."
}
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "1",
  "_version" : 2,
  "resultBase" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 8,
  "_primary_term" : 1
}
```

## 更新部分文档 — _update

`ElasticSearch`中的文档是不可变的：他们不能被修改，只能被替换。` update API` 必须遵循同样的规则。

从`外部`来看，我们在一个文档的某个位置进行`部分更新`。
在`内部`来看， `update API` 简单使用与之前整体更新 `检索-修改-删除-重建索引` 的处理过程是一样的。

**区别**在于：

- 这个过程发生在 `分片内部`，这样就避免了多次请求的网络开销。
- 通过减少`检索`和`重建索引`步骤之间的时间，我们也减少了其他进程的`变更带来冲突的可能性`。


### 最简单的方式：

接收文档的`一部分`作为 `doc 的参数`， 它只是与`现有文档`进行**合并**。
`对象`**被合并**到一起，**覆盖**`现有字段`，**增加**`新字段`。 
例如，我们增加字段 tags 和 views 到我们的博客文章，如下所示：


```
POST /website/blog/123/_update
{
   "doc" : {
      "tags" : [ "testing" ],
      "views": 0
   }
}
```

结果：



```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 3,
  "resultBase" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 9,
  "_primary_term" : 1
}
```

查询新增后的数据：

```
GET /website/blog/123
```

结果：


```
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 3,
  "_seq_no" : 9,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "title" : "My first blog entry",
    "text" : "I am starting to get the hang of this...",
    "date" : "2014/01/02",
    "tag" : "niubi",
    "views" : 0,
    "tags" : [
      "testing"
    ]
  }
}
```

## 取回多个文档 — 合并多个请求：mget

`mget API` 要求有一个 `docs 数组`作为参数，每个元素包含`需要检索文档的元数据`：`_index` 、 `_type` 和 `_id` 。
如果你想检索一个或者多个`特定字段`，那么你可以通过 `_source 参数`来指定这些字段的名字：


```
GET /_mget
{
  "docs":[
    {
         "_index" : "website",
         "_type" :  "blog",
         "_id" :    123,
         "_source": "views"
      },
      {
         "_index" : "website",
         "_type" :  "pageviews",
         "_id" :    124
      }
  ]
}
```

结果：


```
{
  "docs" : [
    {
      "_index" : "website",
      "_type" : "blog",
      "_id" : "123",
      "_version" : 3,
      "_seq_no" : 9,
      "_primary_term" : 1,
      "found" : true,
      "_source" : {
        "views" : 0
      }
    },
    {
      "_index" : "website",
      "_type" : "pageviews",
      "_id" : "124",
      "found" : false
    }
  ]
}
```

**注意：**
即使有某个文档没有找到，上述请求的 HTTP 状态码仍然是 200 。
事实上，即使请求 没有 找到任何文档，它的状态码依然是 200 --因为 `mget 请求本身已经成功执行`。 
为了确定某个文档查找是成功或者失败，你需要检查 `found` 标记。

提供公共 `_index` 和 `_type`的例子:


```
GET /website/blog/_mget
{
  "docs":[
    {
         "_id" :    123,
         "_source": "views"
      },
      {
         "_id" :    124
      }
  ]
}
```

再度简化：


```
GET /website/blog/_mget
{
  "ids":["123","124"]
}
```

## 代价较小的批量操作

`bulk API` 允许在`单个步骤`中进行多次 `create` 、 `index` 、 `update` 或 `delete` 请求。

###  bulk的请求体格式


```
{ action: { metadata }}\n
{ request body        }\n
{ action: { metadata }}\n
{ request body        }\n
...
```

这种格式类似一个有效的 `单行 JSON文档流` ，它通过 `换行符(\n)` 连接到一起。注意2个要点：

- 每行一定要以`换行符(\n)`结尾， 包括`最后一行` 。这些换行符被用作一个标记，可以有效分隔行。
- 这些行不能包含`未转义的换行符`，因为他们将会对解析造成干扰。这意味着这个 JSON 不 能使用 pretty 参数打印。


###  bulk的请求体参数说明：

 **`{ action: { metadata }}` 行指定 `哪一个文档` 做 `什么操作`** 。

 - 其中 **`action`** 必须是以下选项之一:

   	a `create`：如果文档不存在，那么就创建它。
  	 b `index`：创建一个新文档或者替换一个现有的文档。
  	 c `update`：部分更新一个文档。
  	 d `delete`：删除一个文档。

 - `**metadata**` 应该指定被索引、创建、更新或者删除的文档的 `_index` 、 `_type` 和 `_id` 。

**`{ request body        }` 行由文档的 `_source` 本身组成—​文档包含的字段和值。它是 `index` 、`update` 和 `create` 操作所必需的** ，`删除操作`不需要该行。

### 示例


```
POST /_bulk
{"delete":{"_index":"website","_type":"blog","_id":"123"}}
{"create":{"_index":"website","_type":"blog","_id":"123"}}
{"title":"My first blog post","content":"XXXXXXXXX","tag":["news"]}
{"index":{"_index":"website","_type":"blog","_id":"1234"}}
{"title":"My second blog post","conent":"yyyyyyyyyyyyyyy","tag":["book"]}
{"update":{"_index":"website","_type":"blog","_id":"123"}}
{"doc":{"title":"My updated blog post"}}
```

结果：
这个 `Elasticsearch` 响应包含 `items 数组`，这个`数组`的内容是以`请求顺序`列出来的`每个请求的结果`。
`每个子请求`都是`独立执行`，因此`某个子请求的失败`不会对其他子请求的成功与否`造成影响`，这也意味着 `bulk 请求` **不是原子**的： 不能用它来实现`事务控制`。
如果其中`任何子请求失败`，最`顶层的 error` 标志被设置为 `true` ，并且在相应的请求报告出错误明细。

```
{
  "took" : 88,
  "errors" : false,
  "items" : [
    {
      "delete" : {
        "_index" : "website",
        "_type" : "blog",
        "_id" : "123",
        "_version" : 7,
        "resultBase" : "deleted",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 14,
        "_primary_term" : 1,
        "status" : 200
      }
    },
    {
      "create" : {
        "_index" : "website",
        "_type" : "blog",
        "_id" : "123",
        "_version" : 8,
        "resultBase" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 15,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "index" : {
        "_index" : "website",
        "_type" : "blog",
        "_id" : "1234",
        "_version" : 1,
        "resultBase" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 16,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "update" : {
        "_index" : "website",
        "_type" : "blog",
        "_id" : "123",
        "_version" : 9,
        "resultBase" : "updated",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 17,
        "_primary_term" : 1,
        "status" : 200
      }
    }
  ]
}
```

### 不要重复指定Index和Type — 提取公共参数


上面的例子可以精简为以下：

```
POST /website/blog/_bulk
{"delete":{"_id":"124"}}
{"create":{"_id":"124"}}
{"title":"My first blog post","content":"XXXXXXXXX","tag":["news"]}
{"index":{"_id":"125"}}
{"title":"My second blog post","conent":"yyyyyyyyyyyyyyy","tag":["book"]}
{"update":{"_index":"website","_type":"blog","_id":"124"}}
{"doc":{"title":"My updated blog post"}}
```

