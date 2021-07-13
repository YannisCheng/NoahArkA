# ElasticSearch — 映射和分析

- [官网](https://www.elastic.co/cn/)
- [Elasticsearch Guide](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Elastic Stack](https://www.elastic.co/cn/elastic-stack)


[映射与分析](https://www.elastic.co/guide/cn/elasticsearch/guide/current/mapping-analysis.html)

## 精确值 VS 全文

`Elasticsearch` 中的数据可以概括的分为两类：`精确值`和`全文`。

- `精确值`：
 
 如它们听起来那样精确。例如`日期`或者`用户 ID`，对于精确值来讲，`Foo` 和 `foo` 是不同的，`2014` 和 `2014-09-15` 也是不同的。
 
 精确值很容易查询，结果是`二进制`的：要么匹配查询，要么不匹配。这种查询很容易用 `SQL` 表示
 
- `全文` 

 是指`文本数据（通常以人类容易识别的语言书写）`，通常是指`非结构化`的数据。例如一个推文的内容或一封邮件的内容。
 查询全文数据要微妙的多。我们问的不只是 `这个文档匹配查询吗`，而是 **该文档匹配查询的`程度`有多大**？换句话说，**该文档与给定查询的`相关性`如何**？
 
 
为了促进这类`在全文域`中的查询，`Elasticsearch` 首先 **分析** 文档，之后`根据结果`创建 **倒排索引** 。
 
## 倒排索引

`Elasticsearch` 使用一种称为 `倒排索引` 的`结构`，它适用于`快速`的`全文搜索`。一个倒排索引由`文档`中`所有不重复词`的`列表`构成，对于其中`每个词`，有一个`包含它的文档列表`。

**步骤**：

- 内容`分词`
- 创建一个包含所有不重复词条的`排序列表`

`分词`和`标准化`的过程称为 **分析**

## 分析与分析器

**`分析器` 作用：将 `全文字符串` 转换为 `适合搜索` 的 `倒排索引`**

### 概念
**分析** 包含下面的2个过程：

1. 将一块文本分成适合于`倒排索引`的 `独立` 的 `词条` ，
2. 将这些`词条``统一化为标准格式`以提高它们的 `可搜索性`，

**分析器** 包含3个功能：

1. `字符过滤器`
 
 `字符串`按顺序通过每个 `字符过滤器` 。
 他们的任务是在`分词前``整理字符串`。一个字符过滤器可以用来去掉HTML，或者将 & 转化成 and。

2. `分词器`

 `字符串` 被 `分词器` 分为`单个的词条`。
 一个简单的分词器遇到`空格`和`标点`的时候，可能会将文本`拆分`成词条。

3. `Token 过滤器`

 `词条` 按顺序通过每个 `token 过滤器` 。
 这个过程`可能会`改变词条（例如，小写化 Quick ），删除词条（例如， 像 a， and， the 等无用词），或者增加词条（例如，像 jump 和 leap 这种同义词）。

### 分析器种类

- [内置分析器](https://www.elastic.co/guide/cn/elasticsearch/guide/current/analysis-intro.html#_%E5%86%85%E7%BD%AE%E5%88%86%E6%9E%90%E5%99%A8)：`Elasticsearch`附带了可以直接使用的 `预包装分析器`。
- [自定义分析器](https://www.elastic.co/guide/cn/elasticsearch/guide/current/custom-analyzers.html)：`Elasticsearch`提供了开箱即用的 `字符过滤器`、`分词器` 和 `token 过滤器`，这些可以组合起来形成`自定义分析器`以用于不同的目的。

### 内置分析器

**种类：4中**

1. `标准分词器`
2. `简单分词器`
3. `空格分词器`
4. `语言分词器`

示例：


```
"Set the shape to semi-transparent by calling set_trans(5)"
```


1. **标准分词器**

 是`Elasticsearch`的`默认分析器`。它是分析`各种`语言文本`最常用`的选择。
 它根据 `Unicode 联盟` 定义的 `单词边界` 划分文本，删除绝大部分`标点`，最后，将`词条小写`。它会产生：
 
 
	```
	set, the, shape, to, semi, transparent, by, calling, set_trans, 5
	```

2. **简单分词器**

 在`任何不是字母`的地方`分隔文本`，将`词条小写`。它会产生：
 
 
	```
	set, the, shape, to, semi, transparent, by, calling, set, trans
	```

3. **空格分词器**

 在`空格`的地方`划分文本`。它会产生：

	```
	Set, the, shape, to, semi-transparent, by, calling, set_trans(5)
	```

4. **语言分词器**

 特定语言分析器可用于 [很多语言](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/analysis-lang-analyzer.html)。它们可以考虑指定语言的特点。
 `英语` 分词器会产生下面的词条：
 
 
	```
	set, shape, semi, transpar, call, set_tran, 5
	```
	
	
### 什么时候使用分析器


1. 当 `索引文档` 时，它的`全文域`被`分析成词条`以用来创建`倒排索引`。 
2. 当在全文域 `搜索` 的时候，我们需要将 `查询字符串` 通过 `相同的分析过程` ，以保证我们`搜索词条`与`索引词条`中的`格式一致`。

全文查询时理解 `每个域是如何定义` 的后，它们可以做正确的事：

- 当你查询一个 `全文域` 时， 会对`查询字符串`应用`相同的分析器`，以产生正确的搜索词条列表。
- 当你查询一个 `精确值域` 时，`不会`分析查询字符串，而是搜索你`指定的精确值`。

## 映射

[映射](https://www.elastic.co/guide/cn/elasticsearch/guide/current/mapping-intro.html#mapping-intro)

`索引`中每个`文档`都有 `类型` 。
每种`类型`都有它自己的 `映射` ，或者 `模式定义` 。
`映射`定义了`类型`中的`域`，每个域的`数据类型`，以及Elasticsearch如何处理这些域。
`映射`也用于配置与`类型`有关的`元数据`。


### 核心简单域类型
`Elasticsearch` 支持如下简单域类型：

- **字符串**: `string`
- **整数** : `byte`, `short`, `integer`, `long`
- **浮点数**: `float`, `double`
- **布尔型**: `boolean`
- **日期**: `date`

`Elasticsearch` 会使用 `动态映射` ，通过`JSON`中`基本数据类型`，尝试猜测域类型，默认为占用内存空间最大的类型。


**查看映射**


```shell
GET /book_lun_yu/_mapping
```

返回值：


```
{
  "book_lun_yu" : {
    "mappings" : {
      "properties" : {
        "album_name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "author" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "content" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        }
      }
    }
  }
}
```


### 自定义域映射
自定义映射允许你执行下面的操作：

- `全文`字符串域和`精确值`字符串域的区别
- 使用`特定语言分析器`
- `优化域`以适应部分匹配
- 指定`自定义数据格式`
- 还有更多

#### 域最重要的属性是 - type
对于不是 `string` 的域，你一般只需要设置 `type` ：


```
{
    "number_of_clicks": {
        "type": "integer"
    }
}
```

#### string域映射重要属性：index，analyzer

`string` 类型域会被认为`包含全文`。
就是说，它们的值在索引前，会通过一个分析器，针对于这个域的查询在搜索前也会经过一个分析器。

`string 域`**映射**的两个最重要属性是 `index` 和 `analyzer` 。

**1. index** - 索引控制
`index` 属性控制`怎样索引`字符串。它可以是下面三个值：

- `analyzed`

 index的`默认值`。先分析字符串，然后索引它。换句话说，以`全文`索引这个域。

- `not_analyzed`

  索引这个域，所以它能够被搜索，但索引的是`精确值`。不会对它进行分析。
  
- `no`

 `不索引`这个域。这个域不会被搜索到。


**2. analyzer** - 分析方式

对于 `analyzed` 字符串域，用 `analyzer` 属性指定在`搜索`和`索引时`使用的 **分析器**。
默认， `Elasticsearch` 使用 `standard` 分析器。
但你可以`指定`一个内置的分析器替代它，例如 whitespace 、 simple 和 english：


```
{
    "tweet": {
        "type":     "string",
        "analyzer": "english"
    }
}
```

### 更新映射-新增
`已存在`的域，因为无论如何我们都`无法改变`它们。
`新域`已经`被合并`到存在的映射中。
例如：新增 `tagger` 域映射：

```
PUT /book_lun_yu/_mapping
{
    "properties": {
      "tagger":{
        "index": true
        , "type": "text"
        , "analyzer": "ik_max_word"
      }
    }
}
```

**1. 使用 `ik_max_word` 分词器**分析 `tagger` 域中的`有朋自远方来，不亦说乎`文本：

```
GET /book_lun_yu/_analyze
{
  "field": "tagger"
  , "text": ["有朋自远方来，不亦说乎"]
}
```

结果：



```
{
  "tokens" : [
    {
      "token" : "有朋自远方来",
      "start_offset" : 0,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "远方来",
      "start_offset" : 3,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "远方",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "来",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "CN_CHAR",
      "position" : 3
    },
    {
      "token" : "不亦",
      "start_offset" : 7,
      "end_offset" : 9,
      "type" : "CN_WORD",
      "position" : 4
    },
    {
      "token" : "说",
      "start_offset" : 9,
      "end_offset" : 10,
      "type" : "CN_CHAR",
      "position" : 5
    },
    {
      "token" : "乎",
      "start_offset" : 10,
      "end_offset" : 11,
      "type" : "CN_CHAR",
      "position" : 6
    }
  ]
}
```


**2. 使用 `standard` 分词器**分析 `content` 域中的`有朋自远方来，不亦说乎`文本：


```
GET /book_lun_yu/_analyze
{
  "field": "content"
  , "text": ["有朋自远方来，不亦说乎"]
}
```

结果：


```
{
  "tokens" : [
    {
      "token" : "有",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<IDEOGRAPHIC>",
      "position" : 0
    },
    {
      "token" : "朋",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "<IDEOGRAPHIC>",
      "position" : 1
    },
    {
      "token" : "自",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<IDEOGRAPHIC>",
      "position" : 2
    },
    {
      "token" : "远",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "<IDEOGRAPHIC>",
      "position" : 3
    },
    {
      "token" : "方",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "<IDEOGRAPHIC>",
      "position" : 4
    },
    {
      "token" : "来",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "<IDEOGRAPHIC>",
      "position" : 5
    },
    {
      "token" : "不",
      "start_offset" : 7,
      "end_offset" : 8,
      "type" : "<IDEOGRAPHIC>",
      "position" : 6
    },
    {
      "token" : "亦",
      "start_offset" : 8,
      "end_offset" : 9,
      "type" : "<IDEOGRAPHIC>",
      "position" : 7
    },
    {
      "token" : "说",
      "start_offset" : 9,
      "end_offset" : 10,
      "type" : "<IDEOGRAPHIC>",
      "position" : 8
    },
    {
      "token" : "乎",
      "start_offset" : 10,
      "end_offset" : 11,
      "type" : "<IDEOGRAPHIC>",
      "position" : 9
    }
  ]
}
```

## 复杂核心域类型

除了`简单标量`数据类型， JSON 还有 `null值`，`数组`，和`对象`，这些 `Elasticsearch` 都是支持的。

### 多值域-数组

对于数组，没有特殊的映射需求。任何域都可以包含0、1或者多个值，就像全文域分析得到多个词条。这暗示 数组中所有的值必须是`相同数据类型`的 。


当从 Elasticsearch 得到一个`文档`，每个数组的`顺序`和你当初索引文档时一样。你得到的 `_source` 域，包含与你索引的一模一样的 JSON 文档。

但是，`数组`是以 `多值域` 索引的—**可以搜索，但是无序的**。 
在搜索的时候，你不能指定 “第一个” 或者 “最后一个”。 更确切的说，把数组想象成 `装在袋子里的值` 。

### 空域
当然，数组可以为空，这相当于存在零值。 
事实上，在 Lucene 中是不能存储 null 值的，所以我们认为存在 `null值` 的域为`空域`。

下面三种域被认为是空的，它们将`不会被索引`：


```
"null_value":               null,
"empty_array":              [],
"array_with_null_value":    [ null ]
```

### 多层级对象
 `JSON` 原生数据类 `对象` -- 在其他语言中称为哈希，哈希 map，字典或者关联数组。`内部对象` 经常用于嵌入一个实体或对象到其它对象中。
 
### 内部对象的映射


```
{
  "gb": {
    "tweet": { 
      "properties": {
        "tweet":            { "type": "string" },
        "user": { 
          "type":             "object",
          "properties": {
            "id":           { "type": "string" },
            "gender":       { "type": "string" },
            "age":          { "type": "long"   },
            "name":   { 
              "type":         "object",
              "properties": {
                "full":     { "type": "string" },
                "first":    { "type": "string" },
                "last":     { "type": "string" }
              }
            }
          }
        }
      }
    }
  }
}
```

### 内部对象是如何索引的

`Lucene` 不理解 `内部对象`。 
`Lucene` 文档是由 `一组键值对列表` 组成的。
为了能让 `Elasticsearch` 有效地索引内部类，它把我们的文档转化成这样：


```
{
    "tweet":            [elasticsearch, flexible, very],
    "user.id":          [@johnsmith],
    "user.gender":      [male],
    "user.age":         [26],
    "user.name.full":   [john, smith],
    "user.name.first":  [john],
    "user.name.last":   [smith]
}
```


在该简单扁平的文档中，没有 `user` 和 `user.nam`e 域。
因为 `Lucene 索引` 只有`标量`和`简单值`，没有复杂数据结构。


### 内部对象数组

考虑包含内部对象的数组是如何被索引的。 假设我们有个 followers 数组：


```
{
    "followers": [
        { "age": 35, "name": "Mary White"},
        { "age": 26, "name": "Alex Jones"},
        { "age": 19, "name": "Lisa Smith"}
    ]
}
```
这个文档会像我们之前描述的那样被扁平化处理，结果如下所示：


```
{
    "followers.age":    [19, 26, 35],
    "followers.name":   [alex, jones, lisa, smith, mary, white]
}
```

`{age: 35}` 和 `{name: Mary White}` 之间的`相关性`已经`丢失了`，因为每个`多值域`只是`一包无序的值`，而不是有序数组。


