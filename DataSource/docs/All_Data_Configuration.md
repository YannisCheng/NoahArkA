# Data Configuration

2021-05-24 14:01:05 周一

## 概述

- MySQL

- Postgresql

- MongoDB

- Elasticsearch

- Redis



## MySQL

### 安装位置

- macOS
- Ubuntu

### 配置

- 数据库名：area_database、49_class_database
- 端口号：3306
- 服务器地址：macOS/Ubuntu
- 用户名：root
- 密码(macOS/Ubuntu)：Admin_1126

### 终端启动

- **macOS**:

	```shell
	mysqlu
	```

- **Ubuntu**:


	```shell
	mysqlstart
	```

## Postgresql

### 安装位置

- ubuntu

### 配置

- 启动密码：admin
- 数据库：area_database
- 用户名：postgres


### 终端

**启动：**

```shell
poststart
```

**登录：**

```shell
psqllogin
```

## MongoDB

### 安装位置

- Ubuntu

### 配置

- **账号**：

	| 数据库 | 用户名 | 密码 |
	| --- | :-- | --- |
	| ~ | root | root|
	|admin | admin | admin|
	|mongoTest| mongoUser | mongo|

### 终端启动

- **admin数据库启动**：

	```shell
	mongostart
	```

- **mongoTest数据库启动**：

	```shell
	mongostartdb
	```

## Elasticsearch

### 安装位置

- Ubuntu

### 配置


### 启动Elasticsearch（安装于Ubuntu）

```shell
estart
```

### 启动Kibana（安装于macOS）

- **终端启动Kibana服务：**

	```shell
	kbrun
	```

- **在浏览器中打开Kibana的Web界面：**

	```shell
	kbshow
	```

### 浏览器中运行 ElasticSearch-Head

在 `Chrome` 中安装 `Head`。

## Redis

### 安装位置

- Ubuntu

### 配置

- 密码：admin

### 终端启动

- **实例1**：

	```shell
	redistart79
	```

- **实例2**：

	```shell
	redistart80
	```





