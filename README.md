# 入口说明文档

## 项目性质

本项目仅为个人在工作之余的爱好，非商业项目，因此项目的开发进度是根据个人休息时间而定的。当在工作中接手新的项目或者工作紧张时，本项目的开发进度将会十分缓慢。
本项目的最终目的：以大数据为后端基础，前端展示，通过 `ESS&古诗文` 项目进行实际应用。

## 关于工程名称 NoahArk（诺亚方舟）

`诺亚方舟` 出自 `《圣经-旧约》`中的`创世纪`。 上帝为了惩罚地上的万物而要水淹大地，于是命令`诺亚`制造一条船，并让`诺亚`从地上的万物中都选出一对雌雄进入方舟中，以躲避将要到来的大洪水，供后世繁衍。 这就是 `诺亚方舟` 的由来。

本项目取名为`诺亚方舟` 诣在将 `服务端` 的`各个方面`的`知识点`进行总结，并在各个子项目之间建立`层级依赖关系`，使其共同构成一个`功能完善`的`统一整体`。

该工程涉及的方面较为广泛，文档分布众多，需要留意各个子工程下的 `docs` 文件夹。

## 工程构建方式

本工程使用 `Gradle` 方式进行构建。 由于本人主要工作是做 `Android App` 开发，使用 `Android Studio` IDE进行项目开发，并且从2015年开始接触 `Gradle` 项目构建方式。
使用`Gradle` 是一方面是由于熟悉，另一方面是因为其 `表达清晰、简洁` 的特点。


## 子项目描述

**与 `ESS&古诗文` 项目相关：**

- **`app_ui`** — **Web前端项目**
- **`AppRun`** — **项目启动入口**
- **`ModuleCommon`** — **公共基础配置**
- **`ModuleDataSource`** — **各种数据源操作相关**
- **`ModuleShiro`** — **安全框架相关**
- **`ModuleAuth`** - **认证、授权相关**
- **`ModuleCron`** - **定时任务相关**
- **`ModuleFile`** - **文件处理**
  
**`Hadoop` 相关 (相关服务、集群搭建、生态配置已经结束，代码项目暂未启动)：**

- **`ModuleHadoop`** — **Hadoop相关**
  
**各个`Lib`库的`Sample`：**

- **`SampleGenerator`** — **代码生成相关**
- **`SampleLibAsm`** — **ASM库使用demo样例**
- **`SampleLibSpring`** — **SpringFramework使用demo样例**
- **`SampleLibSpringBoot`** — **SpringBoot使用demo样例**
- **`SampleAnnoSource`** - **Annotation中的SOURCE类型的使用**

每个 `Module` 下的 `docs` 目录，用以存储涉及该工程的同类文档。

## 子项目中的各种文档说明

### /docs

 整个项目最基础的文档集合入口。

 涉及：
  
  - Spring基础知识
  - 编程思想
  - 其他基础知识点
  - IDEA_Configure.md: IDEA使用配置相关
  - README.md: 该Spring项目涉及的知识点
  - [TimeLine.md](/docs/TimeLine.md): 时间点对应的工作

### /AppRun/docs

  该工程实际产出项目 `ESS&古诗文` 的整体描述文件集合。

  - AppRun-Configuration.md: `ESS&古诗文` 项目配置相关
  - [AppRun-README.md](/AppRun/docs/AppRun-README.md): `ESS&古诗文` 项目部署文件

### ~~/ModuleCommon/docs~~

### /ModuleDataSource/docs

各种数据源相关文档

- ElasticSearch
- MySQL
- Redis
- MongoDB

### /ModuleGenerator/docs

代码自动生成

### /SampleLibAsm/docs

ASM字节码操作框架

## 服务器硬件配置

### 1. VM虚拟机

服务器配置文档：[【腾讯文档】配置明细说明](https://docs.qq.com/sheet/DY2hQV2hsekNrVWRw)

宿主机器：**`Windows10 内存32G`**
虚拟机软件：`VMWare`
虚拟机： **3台 `Ubuntu Server 20.04TLS`**无界面终端服务器


| 编号 | 虚拟机(服务器)名称 | 核数 | 内存(G) | 硬盘(G) |
| --- | --- | --- | --- | --- |
| 1 | had-nn | 2 | 8 | 30 |
| 2 | had-dn1 | 2 | 8 | 30 |
| 3 | had-dn2 | 2 | 8 | 30 |

注：
`had-nn`全称：hadoop name node
`had-dn1`全称：hadoop date node1
`had-dn2`全称：hadoop date node2


### 2. macOS主机

#### 私有 Nexus Repository 

配置描述：

|title|desc|
|---|---|
|服务版本|OSS 3.17.0-01|
|装机位置|macOS|
|服务地址|http://localhost:8082/|
|用户名|admin|
|密码|admin123|
|启动命令|stanex|
|停止命令|stonex|


