# AppRun 项目部署

## SpringBoot2+Gradle打包

 - [Spring Boot Gradle发布war到tomcat的方法示例](https://www.jb51.net/article/136912.htm)
 - [Spring Boot Gradle Plugin_2.1.0参考指南](https://blog.csdn.net/gavinchen1985/article/details/81673132)
 - [Gradle + Springboot 远程ssh部署](https://my.oschina.net/ardede/blog/3044081)

## 部署

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