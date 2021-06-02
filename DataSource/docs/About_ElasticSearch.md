# ElasticSearch

- [官网](https://www.elastic.co/cn/)
- [Elasticsearch Guide](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Elastic Stack](https://www.elastic.co/cn/elastic-stack)

## 安装

### ElasticSearch

- [Ubuntu 18.04 安装ElasticSearch](https://blog.csdn.net/k_young1997/article/details/104199232)
- 下载：

	```shell
	wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.12.1-linux-x86_64.tar.gz
	```
- 解压：

	```shell
	tar -zxvf elasticsearch-7.12.1-linux-x86_64.tar.gz
	```	
	
- [chrome ElasticSearch Head插件](https://chrome.google.com/webstore/detail/elasticsearch-head/ffmkiejjmecolpfloofpjologoblkegm/related)
- ES安装包所在位置：`/home/adminc/下载/elasticsearch-7.12.1`

**验证安装是否成功：**

- 方式1：终端运行 

	```shell
	curl localhost:9200
	```
		
- 方式2：浏览器

	```
	http://localhost:9200
	```
	
- 返回值：
	
	```shell
	{
	  "name" : "node-1",
	  "cluster_name" : "elasticsearch",
	  "cluster_uuid" : "AGzH42zWTFifDpyr1aCA8Q",
	  "version" : {
	    "number" : "7.12.1",
	    "build_flavor" : "default",
	    "build_type" : "tar",
	    "build_hash" : "3186837139b9c6b6d23c3200870651f10d3343b7",
	    "build_date" : "2021-04-20T20:56:39.040728659Z",
	    "build_snapshot" : false,
	    "lucene_version" : "8.8.0",
	    "minimum_wire_compatibility_version" : "6.8.0",
	    "minimum_index_compatibility_version" : "6.0.0-beta1"
	  },
	  "tagline" : "You Know, for Search"
	}
	```

### Kibana

- [Download Kibana](https://www.elastic.co/cn/downloads/kibana)
	
### elasticdump

- [GitHub elasticsearch-dump](https://github.com/taskrabbit/elasticsearch-dump)
- [elasticDump的安装使用（包含Node、npm安装）](https://www.cnblogs.com/larry-luo/p/11133276.html)
- [Elasticsearchdump 数据导入/导出](https://www.cnblogs.com/mojita/p/12011800.html)
- [ElasticSearch索引数据迁移ElasticSearch-Dump方式](https://blog.csdn.net/qq_39680564/article/details/83177266)


## 配置

- `ES配置` 文件所在目录：`/home/adminc/下载/elasticsearch-7.12.1/config`
- `Kibana` 安装文件路径：`'/Users/yannischeng/YC_USR/kibana-7.12.1-darwin-x86_64/`

## 命令

### ElasticSearch

- **进入ES安装目录**

	```shell
	alias cdes='cd /home/adminc/下载/elasticsearch-7.12.1'
	```

- **进入ES配置目录**

	```shell
	alias cdesconf='cd /home/adminc/下载/elasticsearch-7.12.1/config'
	```

- **ES开启服务（后台守护）**

	```shell
	alias estart='/home/adminc/下载/elasticsearch-7.12.1/bin/elasticsearch -d'
	```

### Kibana

- **终端启动Kibana服务（安装于macOS）：**

	```shell
	alias kbrun='/Users/yannischeng/YC_USR/kibana-7.12.1-darwin-x86_64/bin/kibana'
	```

- **在浏览器中打开Kibana的Web界面：**

	```shell
	alias kbshow='open -a "Google Chrome" http://localhost:5601'	```

### elasticdump

- **备份：mapping和数据**


	```shell
	elasticdump --input http://had-nn:9200/book_song --output /home/adminc/文档/syncFile/bos_mapping.json -- type=mapping
	```
	
- **备份：分词**


	```shell
	lasticdump --input http://192.168.8.182:9200/book_song --output /home/adminc/文档/syncFile/bos_analyzer.json -- type=analyzer
	```
	
- **备份：数据**


	```shell
	elasticdump --input http://192.168.8.182:9200/book_song --output /home/adminc/文档/syncFile/bos_data.json -- type=data
	```

