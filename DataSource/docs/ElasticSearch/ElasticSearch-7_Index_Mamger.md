# 索引管理 — 配置

[索引管理](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index-management.html#index-management)

**优化** `索引` 和 `搜索` 过程，几乎围绕着处理 `索引` 和 `类型` 的 `方方面面` 的过程。

## 手动创建index格式模板


```
PUT /my_index
{
    "settings": { ... any settings ... },
    "mappings": {
        "type_one": { ... any mappings ... },
        "type_two": { ... any mappings ... },
        ...
    }
}
```

## 删除index

### 删除1个索引:


```
DELETE /my_index
```

### 删除多个索引：


```
DELETE /index_one,index_two
DELETE /index_*
```

### 删除全部索引：


```
DELETE /_all
DELETE /*
```



## Elasticsearch配置

详细配置参照 [索引模块](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/index-modules.html)
在 `/home/adminc/下载/elasticsearch-7.12.1/config/elasticsearch.yml` 中的每个节点下 **添加** 配置。
**注意：**


```
Elasticsearch 提供了优化好的默认配置。 
除非你理解这些配置的作用并且知道为什么要去修改，否则不要随意修改。
```

### 分片配置 — 2个最重要的设置

- **number_of_shards**： **每个 `索引` 的 `主分片数`**，默认值是 `5` 。这个配置在**索引`创建后`不能修改**，修改后将找不到已存储在各个分片中的数据。
- **number_of_replicas**： **每个 `主分片` 的 `副本数`**，默认值是 `1` 。对于`活动`的索引库，这个配置可以`随时修改`。

	创建只有 一个主分片，没有副本的小索引：
	
	
	```
	PUT /my_temp_index
	{
	    "settings": {
	        "number_of_shards" :   1,
	        "number_of_replicas" : 0
	    }
	}
	```
	
	
	 
	然后，我们可以用 update-index-settings API 动态修改副本数：
	
	
	```
	PUT /my_temp_index/_settings
	{
	    "number_of_replicas": 1
	}
	```

### 创建Index配置 — 禁止自动创建index

```
action.auto_create_index: false
```

### 删除Index配置 — 禁止通过1个命令来删除所有index

这个设置使删除只限于 `特定名称指向` 的数据, 而不允许通过 `指定_all` 或 `通配符` 来删除指定索引库。

```
action.destructive_requires_name: true
```

## 分析器
### 配置

为 `spanish_docs` index 配置自定义的 `es_std` 分析器，该自定义分析器仅仅用于  `spanish_docs` index ：

```
PUT /spanish_docs
{
    "settings": {
        "analysis": {
            "analyzer": {
                "es_std": {
                    "type":      "standard",
                    "stopwords": "_spanish_"
                }
            }
        }
    }
}
```

使用方式：



```
GET /spanish_docs/_analyze?analyzer=es_std
El veloz zorro marrón
```

### 组成

虽然 `Elasticsearch` 带有一些 `现成` 的 `分析器`，然而在 **分析器** 上`Elasticsearch`真正的强大之处在于，你可以通过在一个适合你的特定数据的设置之中组合：`字符过滤器`、`分词器`、`词单元过滤器` 来创建自 `定义的分析器`。

一个 `分析器` 就是包含了：`字符过滤器`、`分词器`、`词单元过滤器` 的包装器，这3个函数按顺序执行。各个函数的作用为：

- **字符过滤器**

 用来 `整理` 一个`尚未被分词`的字符串。
 例如，如果我们的文本是HTML格式的，它会包含像 <p> 或者 <div> 这样的HTML标签，这些标签是我们不想索引的。我们可以使用 html清除 字符过滤器 来移除掉所有的HTML标签，并且像把 &Aacute; 转换为相对应的Unicode字符 Á 这样，转换HTML实体。

 **一个`分析器`可能有`0个`或者`多个`的`字符过滤器`**。
 
- **分词器**

 一个 `分析器` **必须** 有 `1个唯一` 的 `分词器`。
 `分词器`把`字符串`分解成`单个词条`或者`词汇单元`。
 
 - `标准 分词器` 把一个字符串根据单词边界分解成单个词条，并且移除掉大部分的标点符号；
 - `关键词 分词器` 完整地输出 接收到的同样的字符串，并不做任何分词；
 - `空格 分词器` 只根据空格分割文本；
 - `正则 分词器` 根据匹配正则表达式来分割文本 。
 
 
- **词过滤器**

 经过`分词器`后，作为结果的 `词单元流` 会按照 `指定的顺序` 通过 `指定` 的` 词单元过滤器` 。
 `词单元过滤器`可以 **修改**、**添加** 或者 **移除** `词单元`。
 
### 自定义


```
PUT /my_index
{
  "settings": {
    "analysis": {
      "char_filter": {
        "&_to_end":{
          "type":"mapping",
          "mappings":["&=> and "]
        }
      },
      "tokenizer": {},
      "filter": {
        "my_stopwords":{
          "type":"stop",
          "stopwords":["the","a"]
       }
      }, 
      "analyzer": {
        "my_analyzer":{
          "type":"custom",
          "char_filter":["html_strip","&_to_end"],
          "tokenizer":"standard",
          "filter":["lowercase","my_stopwords"]
        }
      }
    }
  },
  
  "mappings": {
    "properties": {
        "title":{
          "type": "text",
          "analyzer": "my_analyzer"
        }
      }
  }
}
```

## 类型和映射
`类型` 在 `Elasticsearch` 中表示`一类相似的文档`。 
`类型` 由 `名称` 和 `映射` 组成。
`Elasticsearch` 的 `类型` 是以 Lucene 处理文档的这个方式为基础来实现的。
一个`索引`可以有`多个类型`，这些类型的文档可以存储在相同的索引中。

### Luence处理文档

在 `Lucene` 中，一个文档由一组简单的`键值对`组成。
每个`字段`都可以有`多个值`，但至少要有一个值。 
类似的，一个字符串可以通过`分析`过程转化为多个值。
Lucene 不关心这些值是字符串、数字或日期，—​所有的值都被当做 `不透明字节` 。

当在 Lucene 中索引一个文档时，每个字段的值都被添加到相关字段的`倒排索引`中。

### ElasticSearch的类型实现

[实现](https://www.elastic.co/guide/cn/elasticsearch/guide/current/mapping.html#mapping)


Elasticsearch `类型` 是以 Lucene `处理文档` 的这个`方式` 为基础来实现的。
一个索引可以有多个类型，这些类型的文档可以存储在相同的索引中。

Lucene 没有 `文档类型` 的概念，每个文档的 `类型名` 被存储在一个叫 `_type` 的 `元数据字段` 上。 
当我们要检索某个类型的文档时，Elasticsearch 通过在 `_type` 字段上使用 `过滤器` 限制只返回这个类型的文档。

Lucene 没有 `映射` 的概念。 映射是 Elasticsearch 将 `复杂JSON文档` **映射** 成 Lucene 需要的 `扁平化数据` 的方式。

### 避免类型陷阱


**如果有两个不同的类型，每个类型都有同名的字段，但映射不同（例如：一个是字符串一个是数字），将会出现什么情况？**

- 简单回答是：

 
```
Elasticsearch 不会允许定义这个映射。当配置这个映射时，将会出现异常。
```

- 详细回答是：


```
每个 Lucene 索引中的所有字段都包含一个单一、扁平的模式，一个特定字段可以映射成 string 类型也可以是 number 类型，但是不能两者兼具。
因为类型是 Elasticsearch 添加的，优于 Lucene 的额外机制（以元数据 _type 字段的形式），在 Elasticsearch 中的所有类型最终都共享相同的映射。
```

### 类型结论

技术上讲，多个类型可以在相同的索引中存在，只要它们的 **字段不冲突**（要么因为字段是互为独占模式，要么因为它们共享相同的字段）。

重要的一点是: 类型可以很好的区分同一个集合中的不同细分。在不同的细分中数据的整体模式是相同的（或相似的）。

类型不适合 完全不同类型的数据 。如果两个类型的字段集是互不相同的，这就意味着索引中将有一半的数据是空的（字段将是 稀疏的 ），最终将导致性能问题。在这种情况下，最好是使用两个单独的索引。

## 根对象

[根对象](https://www.elastic.co/guide/cn/elasticsearch/guide/current/root-object.html)


`映射`的`最高一层`被称为 `根对象` ，它可能包含下面几项：

- 1个 `properties` 节点，列出了文档中可能包含的`每个字段`的`映射`
- 各种`元数据`字段，它们都以一个下划线开头，例如 _type 、 _id 和 _source
- `设置项` 控制如何动态处理新的字段，例如 analyzer 、 dynamic_date_formats 和 dynamic_templates
- `其他设置` 可以同时应用在根对象和其他 object 类型的字段上，例如 enabled 、 dynamic 和 include_in_all

### 元数据: _source 字段

默认地，`Elasticsearch` 在 `_source` 字段存储代表 `文档体` 的 `JSON字符串` 。
和所有被存储的字段一样， **_source 字段在被 `写入磁盘` 之前先会`被压缩`** 。

这个字段的存储几乎总是我们想要的，因为它意味着下面的这些：

- `搜索结果`包括了`整个可用的文档`——不需要额外的从另一个的数据仓库来取文档。
- 如果没有 _source 字段，部分 `update` 请求不会生效。
- 当你的 `映射改变`时，你需要 `重新索引` 你的数据，有了_source字段你可以直接从Elasticsearch这样做，而不必从另一个（通常是速度更慢的）数据仓库取回你的所有文档。
- 当你不需要看到整个文档时，`单个字段` 可以从 _source 字段 `提取` 和通过 get 或者 search 请求返回。
- 调试查询语句更加简单，因为你可以直接看到每个文档包括什么，而不是从一列id猜测它们的内容。




**映射禁用 `_source` 字段**：

```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "_source": {
                "enabled":  false
            }
        }
    }
}
```


在一个搜索请求里，你可以通过在请求体中 **指定 _source 参数**，来达到只获取特定的字段的效果：


```
GET book_san_guo_zhi/_search
{
  "query": {"match_all": {}}, 
  "_source": ["book_item_name_title"]
}
```


### 元数据: _all 字段

在 `轻量` 搜索 中，我们介绍了 `_all` 字段：**一个把 `其它字段的值` 当作一个 `大字符串` 来索引的 `特殊字段`**。 `query_string` 查询子句(搜索 ?q=john )在没有指定字段时默认使用 `_all` 字段。

_all 字段在新应用的探索阶段，当你还不清楚文档的最终结构时是比较有用的。
你可以使用这个字段来做任何查询，并且有很大可能找到需要的文档：


```
GET /_search
{
    "match": {
        "_all": "john smith marketing"
    }
}
```

通过 `include_in_all` 设置来`逐个控制`字段是否要包含在 `_all` 字段中，默认值是 true。
在一个对象(或根对象)上设置 `include_in_all` 可以`修改`这个对象中的所有字段的默认行为。


## 动态映射

当 Elasticsearch 遇到文档中以前 `未遇到的字段`，它用 **动态映射** 来确定字段的`数据类型`并 自动把新的字段添加到类型映射。

可以用 `dynamic` 配置来控制这种行为 ，可接受的选项如下：

- `true`：动态添加新的字段—​缺省
- `false`：忽略新的字段
- `strict`：如果遇到新字段抛出异常

配置参数 dynamic 可以用在 `根object` 或 `任何object类型` 的字段上。
你可以将 dynamic 的默认值设置为 strict , 而只在指定的内部对象中开启它, 例如：


```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "dynamic":      "strict", 
            "properties": {
                "title":  { "type": "string"},
                "stash":  {
                    "type":     "object",
                    "dynamic":  true 
                }
            }
        }
    }
}
```

- 如果遇到新字段，对象 my_type 就会抛出异常。
- 而内部对象 stash 遇到新字段就会动态创建新字段。


把 dynamic 设置为 false 一点儿也不会改变 `_source` 的`字段内容`。 
`_source` 仍然`包含被索引`的`整个JSON文档`。
只是新的字段`不会`被加到`映射中`也`不可搜索`。

## 自定义动态映射

### 日期检测

当 Elasticsearch 遇到一个新的字符串字段时，它会检测这个字段是否包含一个可识别的日期，比如 2014-01-01 。
如果它像日期，这个字段就会被作为 date 类型添加。
否则，它会被作为 string 类型添加。

日期检测可以通过在根对象上设置 date_detection 为 false 来关闭：


```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "date_detection": false
        }
    }
}
```

使用这个映射，字符串将始终作为 string 类型。如果你需要一个 date 字段，你必须手动添加。

### 动态模板

使用 `dynamic_templates` ，你可以完全控制`新检测生成字段`的`映射`。你甚至可以通过字段名称或数据类型来应用不同的映射。

每个模板都有：

1. 一个`名称`，你可以用来描述这个`模板的用途`；
2. 一个 `mapping` 来`指定映射`应该`怎样使用`；
3. 至少一个`参数 (如 match) `来定义`该模板`适用于`哪个字段`。

模板按照顺序来检测；第一个匹配的模板会被启用。例如，我们给 string 类型字段定义两个模板：

es ：以 _es 结尾的字段名需要使用 spanish 分词器。
en ：所有其他字段使用 english 分词器。
我们将 es 模板放在第一位，因为它比匹配所有字符串字段的 en 模板更特殊：



```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "dynamic_templates": [
                { "es": {
                       // 匹配字段名以 _es 结尾的字段。
                      "match":              "*_es", 
                      "match_mapping_type": "string",
                      "mapping": {
                          "type":           "string",
                          "analyzer":       "spanish"
                      }
                }},
                { "en": {
                      // 匹配其他所有字符串类型字段。
                      "match":              "*", 
                      "match_mapping_type": "string",
                      "mapping": {
                          "type":           "string",
                          "analyzer":       "english"
                      }
                }}
            ]
}}}
```

`match_mapping_type` 允许你应用模板到`特定类型`的`字段`上，就像有`标准动态映射规则检测`的一样， (例如 string 或 long)。
`match` 参数只匹配字段名称。


