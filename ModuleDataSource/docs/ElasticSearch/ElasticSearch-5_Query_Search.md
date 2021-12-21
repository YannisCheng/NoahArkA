# ElasticSearch — 请求体Search（构造查询）

[请求体查询](https://www.elastic.co/guide/cn/elasticsearch/guide/current/full-body-search.html#full-body-search)

之所以称之为 `请求体查询(Full-Body Search)`，因为`大部分参数`是通过 `Http请求体`而非查询字符串来传递的。 不仅可以处理`自身的查询请求`，还允许你对结果进行 `片段强调（高亮）`
、对所有或部分结果进行` 聚合分析`，同时还可以给出 `你是不是想找` 的建议。

## Search种类：

- `scoring`
- `filtering`

## 空查询

返回所有index中的所有文档。

```
GET /_search
{}
```

### ElasticSearch中“带请求体”的Get请求 == POST请求

对于一个`查询请求`，`Elasticsearch` 的工程师偏向于使用 `GET` 方式，因为他们觉得它比 `POST` 能更好的描述 `信息检索（retrieving information）` 的行为。 然而，因为带请求体的 GET
请求并不被广泛支持，所以 `search API`同时支持 `POST` 请求：

```
POST /_search
{
  "from": 30,
  "size": 10
}
```

类似的规则可以`应用于任何`需要`带请求体`的 `GET API`。

## 查询表达式(Query DSL)

`空查询` —— `{}` 等价于 `match_all查询`，即：

```
GET /book_zhou_yi/_search
{}
```

等价于：

```
GET /book_zhou_yi/_search
{
  "query": {
    "match_all": {}
  }
}
```

### 查询语句结构

一个查询语句的典型结构：

```
{
    QUERY_NAME: {
        ARGUMENT: VALUE,
        ARGUMENT: VALUE,...
    }
}
```

如果是针对某个`字段`，那么它的结构如下：

```
{
    QUERY_NAME: {
        FIELD_NAME: {
            ARGUMENT: VALUE,
            ARGUMENT: VALUE,...
        }
    }
}
```

### 一个简单的基本查询体示例

```text
{
    "query": {
        "bool": {
            "must": [ { "match": { "content": "有朋自远方来"}}],
            "must_not": [ ],
            "should": [ ]
        }
    },
    "from": 0,
    "size": 10,
    "sort": [ ],
    "aggs": { }
}
```

## 查询(query)与过滤(filtering)

`Elasticsearch` 使用的查询语言（DSL）拥有一套`查询组件`，这些组件可以`以无限组合`的方式进行搭配，可以在以下两种情况下使用：

- `过滤情况（filtering context）`
- `查询情况（query context）`

1. **过滤情况**

查询被设置成一个 `不评分` 或者 `过滤` 查询。 即这个查询只是简单的问一个问题：“这篇文档是否匹配？”。 回答也是非常的简单，`yes` 或者 `no` ，二者必居其一。

2. **查询情况**

查询就变成了一个 `评分` 的查询。 a. 要去`判断`这个文档`是否匹配`， b. 还需要判断这个文档匹配的有 `多好（匹配程度）`。

**注意：**

- 为了明确和简单，我们用 `filter` 这个词表示`不评分`、只过滤情况下的查询。你可以把 "filter" 、 "filtering query" 和 "non-scoring query" 这几个词视为相同的——过滤查询。

- 如果单独地不加任何修饰词地使用 `query` 这个词，我们指的是 "scoring query"——评分查询 。

### 性能差异

在一般情况下，一个`filter` 会比一个评分的`query`性能更优异，并且每次都表现的很稳定。 过滤（filtering）的目标是：`减少`那些需要通过评分查询（scoring queries）进行检查的文档。

### 如何选择查询与过滤

通常的规则是，使用`查询（query）`语句来进行 `全文` 搜索或者其它任何需要影响 `相关性得分` 的搜索。 除此以外的情况都使用`过滤（filters)`。

## 最重要的查询关键字

- [Es学习第七课， term、terms、match等基本查询语法](https://www.cnblogs.com/kakatadage/p/9958932.html)

部分关键字查询示例：

```text
{
    "query": {
        "bool": {
            "must": [
                {
                    "match": {
                        "content": "有朋自远方来"
                        }
                    }
                ],
            "must_not": [
                {
                    "fuzzy": {
                        "content": {
                            "value": "来",
                            "max_expansions": "1"
                        }
                    }
                }
            ],
            "should": [
                {
                    "term": {
                        "content": "曰"
                    }
                }
            ]
        }
    },
    "from": 0,
    "size": 10,
    "sort": [ ],
    "aggs": { }
}
```

### match — 通用查询

无论你在`任何字段`上进行的是`全文`搜索还是`精确`查询，match 查询是你可用的`标准查询`。

如果你在一个`全文`字段上使用 `match` 查询，在执行查询前，它将用正确的`分析器`去分析查询字符串（其知道分词器的存在，会对`field`进行`分词`操作，然后再查询）； 如果在一个`精确值`
的字段上使用它，例如数字、日期、布尔或者一个 not_analyzed 字符串字段，那么它将会精确匹配给定的值。

```
对于精确值的查询，你可能需要使用 filter 语句来取代 query，因为 filter 将会被缓存。
```

### match_all — 匹配所有文档

查询简单的匹配所有文档。

```
GET /book_zhou_yi/_search
{
  "query": {
    "match_all": {}
  }
}
```

等价于：

```
GET /book_zhou_yi/_search
{}
```

### match_phrase - 短语匹配查询

ES引擎首先分析查询字符串，从分析后的文本中构建`短语查询`，这意味着`必须匹配`短语中的`所有分词`，并且保证各个分词的`相对位置不变`。

### multi_match — 多字段同一个查询条件

可以在`多个字段`上执行`相同`的 `match` 查询：

```
GET /book_zhou_yi/_search
{
  "query": {
    "multi_match": {
      "query": "乾",
      "fields": ["book_item","content"]
    }
  }
}
```

### _source - 部分返回结果

当我们希望返回结果只是一部分字段时，可以加上 `_source`

### range — 范围

查询找出那些落在`指定区间内`的`数字`或者`时间`：

```
GET /book_zhou_yi/_search
{
  "query": {
    "range": {
      "age": {
        "gte": 10,
        "lte": 20
      }
    }
  }
}
```

被允许的操作符如下：

- `gt`：大于
- `gte`：大于等于
- `lt`：小于
- `lte`：小于等于

### terms — 精确查询

会去倒排索引中寻找 `确切` 的term。
`terms` 查询和 `term` 查询一样，但它允许你`指定多值`进行`匹配`。 如果这个字段包含了指定值中的任何一个值，那么这个文档满足条件。 和 `term` 查询一样，`terms` 查询对于`输入的文本` **不分析**
，因此它查询那些 `精确匹配的值`（包括在大小写、重音、空格等方面的差异）。

### exists和 missing — 字段是否存在值

`exists` 查询和 `missing` 查询被用于查找那些`指定字段` 中 `有值 (exists)` 或`无值 (missing)` 的文档。 因此，这些查询经常用于`某个字段`有值和缺值的情况。 这与`SQL`
中的 `IS_NULL (missing)` 和 `NOT IS_NULL (exists)` 在本质上具有共性。

### sort - 排序

使用sort实现排序（类似sql）：desc 降序，asc升序

### fuzzy - 实现模糊查询

- value：查询的关键字
- boost：查询的权值，默认值是1.0
- min_similarity：设置匹配的最小相似度，默认值0.5，对于字符串，取值0-1(包括0和1)；对于数值，取值可能大于1；对于日期取值为1d,1m等，1d等于1天
- prefix_length：指明区分词项的共同前缀长度，默认是0

## 组合查询

[组合查询](https://www.elastic.co/guide/cn/elasticsearch/guide/current/combining-queries-together.html#combining-queries-together)

### bool组合查询

**bool组合查询示例：**

```text
{
    "query": {
        "bool": {
            "must": [
                {
                    "match": {
                        "content": "有朋自远方来"
                    }
                }
            ],
            "must_not": [
                {
                    "match": {
                        "content": "人无远虑"
                    }
                }
            ],
            "should": [
                {
                    "match": {
                        "content": "学而时习之"
                    }
                }
            ]
        }
    },
    "from": 0,
    "size": 10,
    "sort": [ ],
    "aggs": { }
}
```

用 `bool` 查询来实现`组合查询`。它接收以下参数：

- `must`：文档 `必须` 匹配这些条件才能被包含进来（相当于and）。
- `must_not`：文档 `必须不` 匹配这些条件才能被包含进来（相当于not）。
- `should`：如果`满足`这些语句中的`任意`语句，将增加 `_score` ，否则，无任何影响。它们主要用于`修正`每个文档的相关性得分（相当于or）。
- `filter`：`必须` 匹配，但它以不评分、过滤模式来进行。这些语句对评分没有贡献，只是根据`过滤标准`来排除或包含文档。

**注意：**

如果没有 `must` 语句，那么至少需要能够匹配其中的一条 `should` 语句。 但，如果存在`至少`一条 `must` 语句，则对 `should` 语句的匹配没有要求。

**`相关性`**得分**的组合方式：**
每一个子查询都 `独自` 地计算文档的相关性得分。 一旦他们的得分被计算出来， bool 查询就将这些得分进行`合并`并且返回一个代表整个布尔操作的得分。

### 范围过滤

- gt: >
- lt: <
- gte: >=
- lte: <=

### 增加带过滤器（filtering）的查询

嵌套`bool`查询。

## 验证查询 — validate-query





