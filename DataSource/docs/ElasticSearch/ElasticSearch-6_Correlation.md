# ElasticSearch — 排序与相关性

[排序与相关性](https://www.elastic.co/guide/cn/elasticsearch/guide/current/sorting.html#sorting)

## 排序

- `相关性-评分`

 为了按照 `相关性` 来排序，需要将相关性表示为一个 `数值`。
 在 `Elasticsearch` 中， 相关性得分 由一个`浮点数`进行表示，并在搜索结果中通过 `_score` 参数返回， 默认排序是 `_score 降序`。

- `过滤-0分`

 使用的是 `filter （过滤）`，这表明我们只希望获取 `匹配` 的文档，并没有试图确定这些文档的相关性。 实际上文档将按照 `随机顺序` 返回，并且每个文档都会评为`0分`。


### 按照字段的值排序

例如：通过时间来对 tweets 进行排序是有意义的，最新的 tweets 排在最前。 我们可以使用 sort 参数进行实现：


```
GET /_search
{
    "query" : {
        "bool" : {
            "filter" : { "term" : { "user_id" : 1 }}
        }
    },
    "sort": { "date": { "order": "desc" }}
}
```

### 多级排序

假定我们想要结合使用 `date` 和 `_score` 进行查询，并且匹配的结果首先按照日期排序，然后按照相关性排序：


```
GET /_search
{
    "query" : {
        "bool" : {
            "must":   { "match": { "tweet": "manage text search" }},
            "filter" : { "term" : { "user_id" : 2 }}
        }
    },
    "sort": [
        { "date":   { "order": "desc" }},
        { "_score": { "order": "desc" }}
    ]
}
```

## 字符串排序与多字段
具体例子：


```
"tweet": { 
    "type":     "string",
    "analyzer": "english",
    "fields": {
        "raw": { 
            "type":  "string",
            "index": "not_analyzed"
        }
    }
}
```

- `tweet` 主字段与之前的一样: 是一个 `analyzed` 全文字段。
- 新的 `tweet.raw` 子字段是 `not_analyzed`.

## 什么是相关性? — _score

### 什么是相关性？ 

- `相关性算法` 考虑的一个最重要的原则是** 字段长度**：`字段越短越重要`。 在较短的 title 字段中出现的短语可能比在较长的 content 字段中出现的短语更加重要。字段长度的区别在 _all 字段中不会出现。
- 每个文档都有`相关性评分`，用一个 `正浮点数` 字段 `_score` 来表示 。
 - `_score` 的评分越高，相关性越高。


### 相关性如何计算？

查询语句会为每个文档生成一个 `_score` 字段。
评分的 **计算方式** 取决于 **查询类型**，不同的查询语句用于不同的目的：

- `fuzzy(模糊)` 查询会计算与关键词的 `拼写相似程度`；
- `terms(条款)` 查询会计算找到的内容与关键词组成部分 `匹配的百分比`；
- 但是通常我们说的 `relevance(关联)` 是我们用来计算全文本 `字段的值` 相对于全文本 `检索词` **相似程度** 的算法。

### relevance(关联) — 相似度算法：TF/IDF/字段长度准则

例子：


```
GET /_search?explain 
{
   "query"   : { "match" : { "tweet" : "honeymoon" }}
}
```


- **检索词频率(TF)：** 

 `检索词` 在`该字段`出现的`频率`。出现频率越高，相关性也越高。
例子说明： 


	```
	检索词 `honeymoon` 在这个文档的 `tweet` 字段中的出现次数。
	```

- **反向文档频率(IDF)：** 

 每个 `检索词` 在 `索引` 中出现的频率。频率越高，相关性越低。检索词出现在多数文档中会比出现在少数文档中的权重更低。
 
 例子说明：
 
 
	```
	检索词 `honeymoon` 在索引中所有文档的 `tweet` 字段中出现的次数。
	```
 
 
 
- **字段长度准则：**字段的长度是多少。长度越长，相关性越低。 检索词出现在一个短的 title 要比同样的词出现在一个长的 content 字段权重更大。

 例子说明：
 
 
	```
	在这个文档中， `tweet` 字段所在内容的长度 -- 内容越长，值越小。
	```
 


`单个查询` 可以联合使用 `TF/IDF` 和 `其他方式`，比如短语查询中检索词的距离或模糊查询里的检索词相似度。

相关性并不只是全文本检索的专利。也适用于 yes|no 的子句，匹配的子句越多，相关性评分越高。

如果多条查询子句被合并为一条复合查询语句，比如 bool 查询，则每个查询子句计算得出的评分会被合并到总的相关性评分中。


### 理解评分标准 — explain

`explain` 仅适用于在 `开发` 时使用，因为其使用是非常昂贵的。
`Elasticsearch` 在 每个查询语句中都有一个 `explain` 参数，将其设为 `true` 就可以得到更详细的信息：

示例：

```
GET /book_zhou_yi/_search
{
  "explain": true, 
  "query": {
    "match": {
      "content": "蒙"
    }
  }
}
```

结果：


```
{
  "took" : 4,
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
    "max_score" : 7.288142,
    "hits" : [
      {
        // 该文档来自于哪个节点哪个分片
        // 这对我们是比较有帮助的，因为词频率和 文档频率是在每个分片中计算出来的，而不是每个索引中
        "_shard" : "[book_zhou_yi][0]",
        "_node" : "ppsjdagoQyOdjRxjPjc2iQ",
        "_index" : "book_zhou_yi",
        "_type" : "_doc",
        "_id" : "17cG23kB-zvu9h2xEHGJ",
        "_score" : 7.288142,
        "_source" : {
          "book_item" : "蒙",
          "content" : """

            （山水蒙）艮上坎下《蒙》：亨。匪我求童蒙，童蒙求我。初筮告，再三渎，渎则不告。利贞。初六，发蒙，利用刑人，用说桎梏，以往吝。九二，包蒙，吉。纳妇，吉。子克家。六三，勿用取女，见金夫，不有躬。无攸利。六四，困蒙，吝。六五，童蒙，吉。上九，击蒙，不利为寇，利御寇。

"""
        },
        // 相关性评分计算的总结
        // 返回值中提供了 _explanation（解释）
        // 每个入口都包含一个 description（计算的类型） 、 value（计算结果）、 details（计算细节）字段。
        "_explanation" : {
          "value" : 7.288142,
          "description" : "weight(content:蒙 in 16) [PerFieldSimilarity], result of:",
          "details" : [
            {
              "value" : 7.288142,
              "description" : "score(freq=9.0), computed as boost * idf * tf from:",
              "details" : [
                {
                  "value" : 2.2,
                  "description" : "boost",
                  "details" : [ ]
                },
                {
                  "value" : 3.768922,
                  "description" : "idf, computed as log(1 + (N - n + 0.5) / (n + 0.5)) from:",
                  "details" : [
                    {
                      "value" : 1,
                      "description" : "n, number of documents containing term",
                      "details" : [ ]
                    },
                    {
                      "value" : 64,
                      "description" : "N, total number of documents with field",
                      "details" : [ ]
                    }
                  ]
                },
                {
                  "value" : 0.8789759,
                  "description" : "tf, computed as freq / (freq + k1 * (1 - b + b * dl / avgdl)) from:",
                  "details" : [
                    {
                      "value" : 9.0,
                      "description" : "freq, occurrences of term within document",
                      "details" : [ ]
                    },
                    {
                      "value" : 1.2,
                      "description" : "k1, term saturation parameter",
                      "details" : [ ]
                    },
                    {
                      "value" : 0.75,
                      "description" : "b, length normalization parameter",
                      "details" : [ ]
                    },
                    {
                      "value" : 88.0,
                      "description" : "dl, length of field (approximate)",
                      "details" : [ ]
                    },
                    {
                      "value" : 84.328125,
                      "description" : "avgdl, average length of field",
                      "details" : [ ]
                    }
                  ]
                }
              ]
            }
          ]
        }
      }
    ]
  }
}
```


## Doc Value — 列式存储结构

### 由来

当对一个`字段`进行`排序`时，`Elasticsearch` 需要访问每个匹配到的文档得到相关的`值`。
`倒排索引`的`检索`性能是`非常快`的，但是在`字段值排序`时却是`不理想`的结构。

- 在 **搜索** 的时候，我们能通过搜索关键词 —— `倒排索引` 以快速得到`结果集`。
- 当 **排序** 的时候，我们需要`倒排索引`里面某个 `字段值` 的 **集合** —— 需要 **转置** `倒排索引`。

`转置` 结构在其他系统中经常被称作 `列存存储结构` —— 在 `Elasticsearch` 中，就是 `Doc Values`。实质上，它将 `所有单字段的值` 存储在 `单数据列` 中，这使得对其进行`操作`是十分高效的，例如排序。


### Doc Values创建时机

`Doc Values` 是在 `索引时创建` 的：
**当字段 `索引` 时，`Elasticsearch` 为了能够快速检索，会把 `字段的值` 加入 `倒排索引` 中，`同时` 它也会存储该字段的 `Doc Values`**。

**排序** 发生在 `索引` 时建立的 `平行数据结构` 中。

