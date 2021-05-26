# 说明

## 关于工程名称 NoahArk（诺亚方舟）

`诺亚方舟` 出自 `《圣经-旧约》`中的`创世纪`。
上帝为了惩罚地上的万物而要水淹大地，于是命令`诺亚`制造一条船，并让`诺亚`从地上的万物中都选出一对雌雄进入方舟中，以躲避将要到来的大洪水，供后世繁衍。
这就是 `诺亚方舟` 的由来。

本项目取名为`诺亚方舟` 诣在将 `服务端` 的`各个方面`的`知识点`进行总结，并在各个子项目之间建立`层级依赖关系`，使其共同构成一个`功能完善`的`统一整体`。

## 工程构建方式

本工程使用 `Gradle` 方式进行构建。
由于本人主要工作是做 `Android App` 开发，使用 `Android Studio` IDE进行项目开发，并且从2015年开始接触 `Gradle` 项目构建方式。
使用`Gradle` 是一方面是由于熟悉，另一方面是因为其 `表达清晰、简洁` 的特点。


## 工程结构描述

 - `AppRun` — **工程的主App**
 - `AsmLib` — **ASM库使用demo示例**
 - `Common` — **公共基础依赖**
 - `Configuration` — **NoahArk工程配置**
 - `DataSource` — **数据库相关**
 - `Script` — **Java与其他语言间的互相调用**

每一个 `Module` 下都有一个 `docs` 目录，用以存储该类型的相关文档。

## 工程及子Module的主要文件目录


```
NoahArkA 
├── AppRun
│   ├── docs
│   └── src
├── AsmLib
│   ├── docs
│   └── src
├── Common
│   ├── docs
│   └── src
├── Configuration
│   ├── docs
│   └── src
├── DataSource
│   ├── docs
│   └── src
├── Genesis（project）
│   ├── docs
│   ├── images
│   ├── lib
│   └── src
├── ScriptLib
│   ├── docs
│   ├── out
│   └── src
├── docs
└── images

```

## 特殊 - Genesis（创世纪）工程


该工程单独于`NoahArk`工程，原因是：

该工程是以编写 `Spring Framework` 中的 `各个模块` 的demo示例为主要目的。
在编写某个 `模块` 时导入相应的jar包，以达到 `掌握` Spring框架底层模块间`构成`及`关联关系`的目的。

因此本人希望编写组成Spring框架的各个模块的示例demo，构建起对Spring框架最坚实的基础，这个过程就像`《圣经-旧约》创世纪`中上帝创造万物一样。


## 工程涉及：
 
 - Git
 - Nexus
 - Jenkins

## Time：Plans & Complete

[展示项目的实时进度](/docs/TimeLine.md)


