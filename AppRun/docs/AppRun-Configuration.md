# AppRun主工程配置

2021-08-03 20:38:19 周二

## 配置集中

### yml文件配置集中

![工程依赖结构](/images/AppRun主工程依赖2个子Module中的配置文件.png)
 
在 AppRun 工程的 application.yml 文件中添加了如下配置：

```yaml
spring:
  profiles:
    active: dev,common-dev,data-dev
```

这段配置内容表示：
激活 Common 项目中的 application-common-dev.yml 与 ModuleSpringData项目中的 application-data=dev.yml 文件中的内容，将这2个文件中的配置一起集中到application.yml文件中。
由此实现了 主工程 和 多个子依赖项目 的配置统一。

### Java类中功能（容器）的配置集中
在 AppRun 主工程的 Application.java 文件中，添加了：

```java
@Import(value = {CommonApplication.class, DataSourceApplication.class})
public class AppRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppRunApplication.class, args);
    }

}
```

通过将 CommonApplication.class 与 DataSourceApplication.class 这2个类的class导入到 AppRun主工程 中的 AppRunApplication.java 文件中，实现了将2个子依赖项目中的java代码功能进行集中。
