# IDEA 配置

## 1. 扩展MarkDown文件编辑

### 实现目标：
在`IDEA`中创建 `.md` 文件，当`打开`md文件时，使用在`macOS`上自己安装的 `app` 打开并编辑该`md`文件。

### 配置

- [Idea中添加Typora第三方软件编辑md文件](https://www.cnblogs.com/cndarren/p/14415213.html)

## 2. IDEA + Gradle： 一个Project配置多个Module

- [IntelliJ IDEA通过gradle搭建spring boot多模块项目](https://www.cnblogs.com/davidhhuan/p/12232908.html)
- [IDEA 2020.2 +Gradle 6.6.1 + Spring Boot 2.3.4 创建多模块项目](https://blog.csdn.net/zh452647457/article/details/108844078?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242)

**注意：**
当 `Project` 中创建多个 `Module` 后，在右侧边栏 `Gradle`窗口中，会出现各个 `Module` 对应的 `Task`，且修改完根目录下的`settings.gradle` 和 `build.gradle`文件后，原`Module` 对应的 `Task`名称都将会变为 `Project` 对应的名称。

此时：应当保留最下端的`Project` 对应 `Task` 名称，其之上的所有 `Task`名称均删除即可。

[](images/gradle-task配置.png)


