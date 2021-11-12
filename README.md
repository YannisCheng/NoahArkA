# 入口说明文档

## 关于工程名称 NoahArk（诺亚方舟）

`诺亚方舟` 出自 `《圣经-旧约》`中的`创世纪`。
上帝为了惩罚地上的万物而要水淹大地，于是命令`诺亚`制造一条船，并让`诺亚`从地上的万物中都选出一对雌雄进入方舟中，以躲避将要到来的大洪水，供后世繁衍。
这就是 `诺亚方舟` 的由来。

本项目取名为`诺亚方舟` 诣在将 `服务端` 的`各个方面`的`知识点`进行总结，并在各个子项目之间建立`层级依赖关系`，使其共同构成一个`功能完善`的`统一整体`。

该工程涉及的方面较为广泛，文档分布众多，需要留意各个子工程下的 `docs` 文件夹。

## 工程构建方式

本工程使用 `Gradle` 方式进行构建。
由于本人主要工作是做 `Android App` 开发，使用 `Android Studio` IDE进行项目开发，并且从2015年开始接触 `Gradle` 项目构建方式。
使用`Gradle` 是一方面是由于熟悉，另一方面是因为其 `表达清晰、简洁` 的特点。


## 子工程描述

 - **`AppRun`** — **综合项目的入口工程**
 - **`ModuleCommon`** — **公共基础配置**
 - **`ModuleDataSource`** — **数据库操作相关**
 - **`ModuleGenerator`** — **代码生成相关**
 - **`ModuleHadoop`** — **Hadoop相关**
 - **`ModuleShiro`** — **安全框架相关**
 - **`SampleLibAsm`** — **ASM库使用demo样例**
 - **`SampleLibSpring`** — **SpringFramework使用demo样例**
 - **`SampleLibSpringBoot`** — **SpringBoot使用demo样例**

每个 `Module` 下的 `docs` 目录，用以存储涉及该工程的同类文档。

## Time：Plans & Complete

[展示项目的实时进度](/docs/TimeLine.md)
该工程任何最新的进度、规划都将在该文档中进行说明，该文件将作为该工程后续开发的指导性文件。

## 综合项目运行基本说明
[AppRun-README.md](/AppRun/docs/AppRun-README.md)

