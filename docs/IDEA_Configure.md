# IDEA 配置

## 1. IDEA配置

### 1.1 扩展MarkDown文件编辑

#### 实现目标：
在`IDEA`中创建 `.md` 文件，当`打开`md文件时，使用在`macOS`上自己安装的 `app` 打开并编辑该`md`文件。

#### 配置

- [Idea中添加Typora第三方软件编辑md文件](https://www.cnblogs.com/cndarren/p/14415213.html)

### 1.2 为Spring配置JRebel

- 参考：https://www.cnblogs.com/c2g5201314/p/13063668.html
- [IDEA with JRebel to Spring Boot](https://www.imooc.com/article/303635)
- [idea集成JRebel](https://www.cnblogs.com/-xuzhankun/p/13331109.html)



## 2. Gradle构建

### 2.1 多Module构建

- [IntelliJ IDEA通过gradle搭建spring boot多模块项目](https://www.cnblogs.com/davidhhuan/p/12232908.html)
- [IDEA 2020.2 +Gradle 6.6.1 + Spring Boot 2.3.4 创建多模块项目](https://blog.csdn.net/zh452647457/article/details/108844078?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242)
- [Gradle org.springframework.boot](https://plugins.gradle.org/plugin/org.springframework.boot)
- [Gradle 7.0.2 Structuring Large Projects](https://docs.gradle.org/current/userguide/structuring_software_products.html#structure_large_projects)
- [Gradle 7.0.2 Create a Basic Multi-Project Build](https://docs.gradle.org/current/userguide/multi_project_builds.html#multi_project_builds)

**注意：**
当 `Project` 中创建多个 `Module` 后，在右侧边栏 `Gradle`窗口中，会出现各个 `Module` 对应的 `Task`，且修改完根目录下的`settings.gradle` 和 `build.gradle`文件后，原`Module` 对应的 `Task`名称都将会变为 `Project` 对应的名称。

此时：应当保留最下端的`Project` 对应 `Task` 名称，其之上的所有 `Task`名称均删除即可。

[](/images/gradle-task配置.png)

### 2.2 Plugin

[Using Gradle Plugins](https://docs.gradle.org/current/userguide/plugins.html)

`Plugin` **类型**： 

- [Binary Plugins](https://docs.gradle.org/current/userguide/plugins.html#sec:binary_plugins)
 1. 核心插件
 2. 社区插件
- [Script Plugins](https://docs.gradle.org/current/userguide/plugins.html#sec:script_plugins)

`Plugin` **应用方式**：

- [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block)
- [BuildScript Block](https://docs.gradle.org/current/userguide/plugins.html#sec:applying_plugins_buildscript)



### 2.3 JVM Plugin

- [Java Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html#application_plugin)
- Java Library Plugin
 - 参考：https://docs.gradle.org/current/userguide/java_library_plugin.html
 - 参考：https://docs.gradle.org/current/userguide/java_plugin.html#java_plugin

### 2.4. Grade的 gradle.properties

- [gradle.properties](https://docs.gradle.org/current/userguide/build_environment.html)


## 3. IDEA+Gradle编译Spring源码

- [IDEA+Gradle构建Spring5源码阅读环境教程(优化构建速度)](https://zhuanlan.zhihu.com/p/149641082)
- [IDEA导入Spring源码调试必看](https://blog.csdn.net/u013469325/article/details/106586497)