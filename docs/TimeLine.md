# Time：Plans & Complete

## 课程

### 在线

* [ ] 宋红康java基础29天第5集：反射、动态代理
* [ ] 
* [ ] 
* [ ] 
* [ ] 
  
### 本地

* [ ] 马士兵课程：/Users/yannischeng/Movies/课程/马士兵-IO与存储技术串讲
* [ ] JVM：/Users/yannischeng/BigData/尚硅谷课程/JVM/

## 门闩

### 知识点

- **2021-08-02 18:31:28 周一**

* [x] @resource与@autowired区别/Spring自动装配bean：byType与byName区别

	- [Spring 中的byName与 byType](https://blog.csdn.net/youlingqishi11/article/details/6458821)
	- [Spring源码学习--@Autowired注解和启动自动扫描的三种方式](https://blog.csdn.net/u013412772/article/details/73741710/)
	- [Spring的byType、byName的自动装配逻辑以及@Autowired和@Resource的使用示例](https://blog.csdn.net/Alexshi5/article/details/84000678)
	- [Spring MVC 解读——@Autowired](https://my.oschina.net/HeliosFly/blog/203902)
	- [@Resource注解用法作用](https://blog.csdn.net/panting30/article/details/95333766)
	- [Spring中@Resource和@Autowire的区别](https://blog.csdn.net/kevinxxw/article/details/105145397)
  
### IDE

* [x] **IDEA + Gradle + Lombok注解不起作用** 
IDEA2021.1旗舰版默认已经安装了Lombok Plugin插件，但是在项目的build.gradle文件中添加依赖时，需要添加2个依赖，否则报错。

 解决方式：
 [IDEA + Gradle + Lombok 错误：找不到符号](https://my.oschina.net/u/3251146/blog/4938478)
 [projectlombok-gradle处理方式](https://projectlombok.org/setup/gradle)

 ```gradle
repositories {
		mavenCentral()
}
dependencies {
		compileOnly 'org.projectlombok:lombok:1.18.20'
		annotationProcessor 'org.projectlombok:lombok:1.18.20'
		
		testCompileOnly 'org.projectlombok:lombok:1.18.20'
		testAnnotationProcessor 'org.projectlombok:lombok:1.18.20'
}  
 ```


## 进度

- **2021-05-23 周日**

* [x] 初始化`NoahArk` 工程
* [x] 填充 `AsmLib` Module 中的文件内容。

- **2021-05-24 周一**

* [x] 完善 `README.md` 文档
* [x] 为 `根项目` 及 `子Module` 创建 `docs` 目录。
* [x] 完善 `IDEA_Configure.md`
* [x] 完善 `DataSource/docs/` 目录下各个说明文件的编写

- **2021-05-25 周二**

* [x] 添加 `Genesis` Module，该Module为学习Spring的最基本知识点。
* [x] 在 `Genesis` Module中包含了 `Bean管理` 知识。

- **2021-05-26 周三**

* [x] 添加 `AOP` 中 `JDK-Proxy` 的代理实现原理测试代码。
* [x] 添加 `AOP` 中 `AspectJ`的`注解`方式进行方法增强。
* [x] 修改 `Genesis` Module中的文档命名及内容。

- **2021-07-31 16:34:39 周六**

* [x] 更改NoahArkA中的子Module名称
* [x] 更新SampleLibSpring/docs目录下的README.md文档
* [x] Module：SampleLibSpring的主要功能是在未使用"注解"的情况下，了解Spring Framework的基本使用
* [x] Module：SampleLibSpringBoot的主要功能是了解SpringBoot的基本使用，众多的例子将会一起集中在这个Module中。
* [x] SampleLibSpring与SampleLibSpringBoot中的文档统一集中在SampleLibSpring/docs目录下
* [x] 在SampleLibSpringBoot中集成了Swagger组件
* [ ] 熟练掌握、总结Swagger注解的使用
* [ ] 数量掌握、总结各种HTTP请求方式的使用方式、方法
* [ ] 掌握、总结SpringBoot中MySQL的基本使用
* [ ] 掌握、总结SpringBoot中Postgresql的基本使用
* [ ] 掌握、总结SpringBoot中Redis的基本使用
* [ ] 掌握、总结SpringBoot中ElasticSearch的基本使用

- **2021-08-02 18:31:28 周一**

* [x] 添加mybatis-plus依赖，使用mybatis插件生成代码，基本完成area_databaes库的服务类生成。

- **2021-08-03 22:31:28 周二**

* [x] 处理完毕Lombok插件@Data注解无效问题
* [x] yml文件配置集中
* [x] Java类中功能（容器）的配置集中，如：@Import(value = {CommonApplication.class, DataSourceApplication.class})

- **2021-08-04 11:01:21 周三**

* [x] 添加：自定义.yml配置文件映射为Java的Bean类
* [x] 实现基本的Swagger2个API分组功能

- **2021-08-06 22:26:58 周四**

* [x] 添加：Redis基本API操作

- **2021-08-06 11:28:18 周五**

* [x] 测试多module配置下打jar包，本地测试、上传至服务器、在服务器上运行jar包，在本地测试接口。

- **2021-08-11 22:24:21 周二**

* [x] Reactor引入，并写基本测试示例

- **2021-08-11 22:25:30 周三**

* [ ] Reactor主要组件分析：Publisher、Processor、BaseSubscriber{Subscription、Subscriber、Disposable}、Scheduler 部分2
* [ ] 添加"ModuleGenerator" Module，该Module主要功能为使用"Velocity"库，实现代码自动生成。
* [ ] 具体概念的实际试验[IntelliJ Idea中的 Facets 与 Artifacts](https://www.cnblogs.com/bityinjd/p/9284378.html)

- **2021-08-31 16:59:19 周二**

* [ ] 安全组件选择。
* [ ] 多数据源测试。

 -  [x] MySQL配置完成（3个数据源）；
 -  [x] Redis配置完成（2个数据源）；

- **2021-09-06 17:07:13 周一**

* [x] ModuleGenerator 中 `resource` 目录下添加 `java`、`js`、`sql`、`vue`、`xml` 配置模板；
* [ ] Vue前端搭建；
* [ ] 前端与端接口通信；


- **2021-09-07 22:09:41 周二**

* [x] 熟悉、修改 app-ui框架layout中的3部分，了解main.js在Vue中的作用和调用顺序

- **2021-11-12 10:43:34 周五**

* [x] 经过在一段时间的间隔后，再次编辑该项目时做总结：总结服务快速启动方式、总结项目各个进度层级。
      **1级**：[正在开发的综合项目] 该综合项目是目前一直整合开发的，目的是行程一个可行的实际项目，对知识点进行验证和实践。
            AppRun
            ModuleCommon
            ModuleDataSource
      **2级**：[后期跟进子项目，将会加入到综合项目] 
            ModuleGenerator
            ModuleHadoop
            ModuleShiro
      **3级**：[单独项目] 该项目不会加入[综合项目]中，始终以单项目的方式进行开发，该种类型的项目的目的是以学习知识点为目的存在的。
            SampleLibAsm
            SampleLibSpring
            SampleLibSpringBoot
            SampleAnnoSource
  
- **2021-12-23 11:45:48 周四**

* [x] ElasticSearch 基本操作实现


- **2021-12-23 14:23:52 周四**

注解、反射、APT、JavaPoet框架使用

* [ ] 注解的3种生命周期 《Annotation-Reflection-APT-Poet》文件夹
* [ ] 注解Runtime与反射
* [x] Source与APT结合生成代码(2021-12-29 17:23:31 实现完成流程，看到结果)- SampleAnnoSource
* [ ] JavaPoetd代码生成辅助框架- SampleAnnoSource#PoetMain
* [ ] filter - ModuleCommon#filter#BaseFilter

- **2021-12-31 16:42:32 周五**

* [ ] 全局异常、统一结果包装处理、校验全局处理：测试包：ElasticSearch
* [ ] Cron 定时任务：ModuleCron
* [ ] Spring Security With JWT