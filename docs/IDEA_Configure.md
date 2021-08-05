# IDEA 配置

- [idea集成JRebel](file:////Users/yannischeng/Documents/All_Collection/Other/Server端概念相关.graffle)

## 内存

### 大小修改
 路径：`/Users/yannischeng/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/211.7142.45/IntelliJ IDEA.app.vmoptions`

### 内存过大、大小限制

[intellij内存占用很大（16G以上），而且时不时卡顿。](https://segmentfault.com/q/1010000012306606)

## 1. 创建工作Flow

已经实现的本地app的flow配置：

- MWeb（Markdown编辑工具）：文件后缀 `.md`
- XMind（思维导图工具）：文件后缀 `.xmind`

 注意：仅能打开已经存在的xmind文件，如果在IDEA中创建该文件，将无法打开。
 
- OmniGraffle（绘图工具）：文件后缀 `.graffle`

  注意：仅能打开已经存在的graffle文件，如果在IDEA中创建该文件，将无法打开。


### 实现目标：
在`IDEA`中创建 `.md` 文件，当`打开`md文件时，使用在`macOS`上自己安装的 `app` 打开并编辑该`md`文件。

### 配置

- [Idea中添加Typora第三方软件编辑md文件](https://www.cnblogs.com/cndarren/p/14415213.html)

## 2. 为Spring配置JRebel

- 参考：https://www.cnblogs.com/c2g5201314/p/13063668.html
- [IDEA with JRebel to Spring Boot](https://www.imooc.com/article/303635)
- [idea集成JRebel](https://www.cnblogs.com/-xuzhankun/p/13331109.html)



## 3. Gradle构建

### 依赖源

 - [maven库地址](https://mvnrepository.com/)


### 3.1 多Module构建

- [IntelliJ IDEA通过gradle搭建spring boot多模块项目](https://www.cnblogs.com/davidhhuan/p/12232908.html)
- [IDEA 2020.2 +Gradle 6.6.1 + Spring Boot 2.3.4 创建多模块项目](https://blog.csdn.net/zh452647457/article/details/108844078?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242)
- [Gradle org.springframework.boot](https://plugins.gradle.org/plugin/org.springframework.boot)
- [Gradle 7.0.2 Structuring Large Projects](https://docs.gradle.org/current/userguide/structuring_software_products.html#structure_large_projects)
- [Gradle 7.0.2 Create a Basic Multi-Project Build](https://docs.gradle.org/current/userguide/multi_project_builds.html#multi_project_builds)

**注意：**
当 `Project` 中创建多个 `Module` 后，在右侧边栏 `Gradle`窗口中，会出现各个 `Module` 对应的 `Task`，且修改完根目录下的`settings.gradle` 和 `build.gradle`文件后，原`Module` 对应的 `Task`名称都将会变为 `Project` 对应的名称。

此时：应当保留最下端的`Project` 对应 `Task` 名称，其之上的所有 `Task`名称均删除即可。

![](/images/gradle-task配置.png)

### 3.2 Plugin

[Using Gradle Plugins](https://docs.gradle.org/current/userguide/plugins.html)

`Plugin` **类型**： 

- [Binary Plugins](https://docs.gradle.org/current/userguide/plugins.html#sec:binary_plugins)
 1. 核心插件
 2. 社区插件
- [Script Plugins](https://docs.gradle.org/current/userguide/plugins.html#sec:script_plugins)

`Plugin` **应用方式**：

- [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block)
- [BuildScript Block](https://docs.gradle.org/current/userguide/plugins.html#sec:applying_plugins_buildscript)



### 3.3 JVM Plugin

- [Java Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html#application_plugin)
- Java Library Plugin
 - 参考：https://docs.gradle.org/current/userguide/java_library_plugin.html
 - 参考：https://docs.gradle.org/current/userguide/java_plugin.html#java_plugin

### 3.4. Grade的 gradle.properties

- [gradle.properties](https://docs.gradle.org/current/userguide/build_environment.html)


## 4. IDEA+Gradle编译Spring源码

- [IDEA+Gradle构建Spring5源码阅读环境教程(优化构建速度)](https://zhuanlan.zhihu.com/p/149641082)
- [IDEA导入Spring源码调试必看](https://blog.csdn.net/u013469325/article/details/106586497)

## SpringBoot2.5版本差异

- [InvalidConfigDataPropertyException: Property ‘spring.profiles.active‘ imported from...SpringBoot版本所致](https://blog.csdn.net/Be_insighted/article/details/119062722)
- [SpringBoot中使用Spring profile进行配置【谨慎使用，参见SpringBoot 2.4.0发布文档】](https://www.cnblogs.com/fanqisoft/p/14048773.html)
- [抢先目睹：SpringBoot2.4配置文件加载机制大变化](https://zhuanlan.zhihu.com/p/192589458)
