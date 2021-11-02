# AppRun 项目部署

- [Tomcat官网下载地址](http://tomcat.apache.org/)
- [JDK8官网下载](https://www.oracle.com/java/technologies/downloads/#java8)
- [Nginx官网下载](http://nginx.org/en/download.html)

## SpringBoot2+Gradle打包

 - [Spring Boot Gradle发布war到tomcat的方法示例](https://www.jb51.net/article/136912.htm)
 - [Spring Boot Gradle Plugin_2.1.0参考指南](https://blog.csdn.net/gavinchen1985/article/details/81673132)
 - [Gradle + Springboot 远程ssh部署](https://my.oschina.net/ardede/blog/3044081)

## 部署
### 简单步骤

- [在IDEA中如何把Gradle下的Spring boot项目打包并部署到服务器](http://www.manongjc.com/detail/10-dmhvfejsgqiponw.html)

**springboot自带tomcat，有java环境可以直接打包成jar直接运行。**

基本步骤：

1. 在各个 `build.gradle` 文件中添加 

```gradle
bootJar {
    // true：开启；false：禁用
    enabled = true
}
```

2. 在主项目中执行 `bootJar` task后，会在执行目录生成jar文件： `主项目/build/libs/xxxx.jar`。

3. 现在本地执行一下命令： 

```
java -jar xxxx.jar
```

在浏览器进行测试。

4. 通过一下命令将本地的 `jar` 包上传到服务器：

```
scp xxx.jar adminc@had-dn1:/home/adminc/Document/xxx.jar
```

5. 在服务器上同样执行：

```
java -jar xxxx.jar
```

### Windows Server实际基础操作

- [Tomcat8+JDK8安装与配置](https://www.cnblogs.com/temari/p/13576532.html)
- [Tomcat7目录结构详解（非常详细）](https://blog.csdn.net/jdjdndhj/article/details/52694202?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link)
- [Tomcat简单介绍（安装和目录结构介绍）](https://blog.csdn.net/qq_41517071/article/details/82181003)
- [tomcat目录结构及配置文件说明](https://www.iteye.com/blog/liuguidong-2253775)

要点：

1. 安装、配置JDK环境
2. 配置、安装Tomcat环境；webapps目录下放置jar/war包、前端包
3. 配置Nginx环境；html 文件夹下放置前端

注意：

端口的开通


