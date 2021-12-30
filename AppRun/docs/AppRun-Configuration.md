# AppRun主工程配置

2021-08-03 20:38:19 周二

## 项目配置

参考：

- [springboot中多Module模块使用对应的application.properties配置文件](https://blog.csdn.net/u012988901/article/details/83024406?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-1.base&spm=1001.2101.3001.4242)
- [springboot多个配置文件的配置 多个application.properties的配置 版本 sprigboot2.1](https://blog.csdn.net/hanpenghu/article/details/84071979)
- [SpringBoot多环境配置详解(application-dev.yml、application-test.yml、application-prod.yml)](https://blog.csdn.net/Wing_kin666/article/details/111992800)

### yml文件配置集中：方式1

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

### yml文件配置集中：方式2-import

![Import的使用](/images/Import的使用.png)

实现方式： spring.config.import.classpath:

示例：
application-dev.yml文件配置：
通过import将其他Module中的配置文件一起导入到该文件中，这样分散在各个文件中的配置内容就被集中到了该文件中。

```yaml
spring:
  config:
    # 使用Import将多个配置文件导入到同一个文件中
    # Import 可以被视为在声明它们的文档下方插入的其他文档。
    import:
      - classpath:application-student.yml
      - classpath:application-common-dev-actuator.yml
      - classpath:application-common-dev-server.yml
      - classpath:application-common-dev-security.yml
      - classpath:application-data-dev.yml
```

application.yml文件配置：
激活 application-dev.yml 文件配置。

```yaml
spring:
  profiles:
    active: dev
```


### Java类中功能（容器）的配置集中-@Import
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

### 自定义.yml配置文件映射为Java的Bean类

项目文件结构：

#### 参考

- [如何引用 application.yml 或者 application.properties 之外的其它配置文件？](https://blog.csdn.net/weixin_41231928/article/details/118056736)
- [（Spring Boot教程三 ）关于配置文件application.properties和application.yml理解](https://blog.csdn.net/zhanggonglalala/article/details/89231025?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.base&spm=1001.2101.3001.4242)

#### 实现步骤

![自定义.yml配置文件映射为Java的Bean类](/images/自定义.yml配置文件映射为Java的Bean类.png)


1. 创建 .yml 文件
2. 在主工程的 .yml 文件中通过include引入自定义的yml配置文件
3. 创建Java Bean类，添加注解实现字段映射

#### 涉及到的注解

- 导入配置文件。注意增加编码方式，否则可能中文乱码：
  @PropertySource(value = {"classpath:application-student.yml"}, encoding = "UTF-8")
  
- 进行属性映射：
  @ConfigurationProperties(prefix = "student")
  
## 多个Module（@SpringBootApplication）间的依赖配置

### 说明

在本工程中的各个 `module` 均使保留 `SpringBoot启动类：XXXApplication`，且在最终添加依赖后仍然保留，目的是希望在尽可能简单的代码环境下测试最主要的功能。

### 实现效果

通过运行 `AppRun` 中的 `AppRunApplication` 启动类，能够加载 `ModuleCommon` 与 `ModuleDataSource` 中的配置，正常运行既定功能。

### 配置步骤

1. 通过在 `Java类` 中使用 `@Import` 导入`被依赖module`的 `启动容器类`；
2. 通过在 `build.gradle` 文件中添加 `project(':xxx')` 实现代码依赖。

### 配置示例

1. **入口类：`AppRun` 依赖 `ModuleDataSource`**

	java文件注解导入Application：
	
	```java
	@Import(value = {DataSourceApplication.class})
	public class AppRunApplication { }
	```
	
	build.gradle 文件导入依赖：
	
	```gradle
	dependencies {
	    // 引用 ModuleDataSource 组件
	    implementation project(':ModuleDataSource')
	}
	```


2. **`ModuleDataSource` 依赖 `ModuleCommon`**


	java文件注解导入Application：
	
	```java
	@Import(value = {CommonApplication.class})
	public class DataSourceApplication { }
	```
	
	build.gradle 文件导入依赖：
	
	```gradle
	dependencies {
		implementation project(':ModuleCommon')
	}
	```

