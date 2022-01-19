# AppRun - ESS&古诗文


- [Tomcat官网下载地址](http://tomcat.apache.org/)
- [JDK8官网下载](https://www.oracle.com/java/technologies/downloads/#java8)
- [Nginx官网下载](http://nginx.org/en/download.html)
- [Spring Boot Gradle发布war到tomcat的方法示例](https://www.jb51.net/article/136912.htm)
- [Spring Boot Gradle Plugin_2.1.0参考指南](https://blog.csdn.net/gavinchen1985/article/details/81673132)
- [Gradle + Springboot 远程ssh部署](https://my.oschina.net/ardede/blog/3044081)


## ESS&古诗文 项目说明
 
### 文档

- 外链：[【腾讯文档】ES&古诗文&Spring](https://docs.qq.com/doc/DY0lsY0hvWnJOV0tl)
- `Books`的数据来源：通过Python爬虫项目 [BookOfSong](https://github.com/YannisCheng/BookOfSong)  
- `Songs`的数据来源：[非常全的古诗词数据，收录了从先秦到现代的共计85万余首古诗词。](https://github.com/Werneror/Poetry)

### 数据说明

**Books（10本）**：

- `《古文观止》`
- `《老子》`
- `《论语》`  
- `《毛选1-3》`
- `《三国志》`
- `《史记》`
- `《诗经》`
- `《周易》`
- `《庄子》`
- `《资治通鉴》`

**Songs（共分32个时间段）**：

从先秦到现代的诗歌，约：`85万首` 诗词。

具体时间段为：

```Text
SongOfCurrent
SongOfHan
SongOfJin
SongOfJonToYuan
SongOfLiao
SongOfMinGuo
SongOfMing1
SongOfMing2
SongOfMing3
SongOfMing4
SongOfMingEndQing
SongOfNearCurrent
SongOfNow
SongOfQing1
SongOfQing2
SongOfQingEnd
SongOfQingMinGuo
SongOfSong1
SongOfSong2
SongOfSong3
SongOfSong4
SongOfSongEndJin
SongOfSongEndYuan
SongOfSongNorth
SongOfSui
SongOfSuiEndTang
SongOfTang
SongOfTangEndSong
SongOfXianQian
SongOfYuan
SongOfYuanEndMing
SongWeiJin
```


## API主题2种

- 本项目默认使用：[Swagger2-doc API集合 蓝色主题-Dev环境](http://localhost:8089/NoahArk_Dev/doc.html)
- [Swagger2-swagger-ui API集合 绿色主题-Dev环境](http://localhost:8089/NoahArk_Dev/swagger-ui.html)

### 已有API分类：

- 项目健康报告：NoahArk-Actuator REST API
- 数据源基础操作：NoahArk-DataSource REST API 
  - MySQL：area_database操作
  - Redis：database 0 操作
- Hadoop生态：NoahArk-Hadoop REST API


## Dev环境服务部署、配置与启动

### 服务部署

- 虚拟机：had-dn1
- 虚拟机：had-dn2

### 配置说明

```yaml
 服务器：虚拟机had-dn1
 服务：
    MySQL
    Redis-6379 命令：redis-server
    Redis-6380 命令：sudo redis-server /etc/redis/redis6380.conf
    pro环境：elasticsearch，dev环境无。

 服务地址：localhost:8089
 用户名：yannis
 密码：adminc
```

### Dev环境TCP连接、各项服务启动

具体执行的命令、服务配置参考：[【腾讯文档】内网穿透](https://docs.qq.com/doc/DY2FMQ2tnU1FsTmxH)

#### （确定）启动虚拟机had-dn1、had-dn2中的 TCP ssh 连接服务 （2个）

- had-dn1 ssh TCP 启动（1）:

```shell
sudo systemctl start frpc@dn1_gz1
```

- 远程连接:

```shell
ssh adminc@cn6.frp.cool -p 11496
```

- had-dn2 ssh TCP 启动（2）:

```shell
sudo systemctl start frpc@dn2_gz2
```

- 远程连接:

```shell
ssh adminc@cn-gz2.frp.cool -p 15311
```

#### 启动虚拟机had-dn1、had-dn2中的本地ES服务

- 启动2台虚拟机had-dn1、had-dn12中的ES服务：

```shell
estart
```

- 确认本地是否启动成功：

```shell
curl localhost:9002
```

#### 启动ES集群对外访问地址（使用had-dn1的地址为对外地址）（1个）

- 启动ES服务器对外的访问地址（3）：

```shell
sudo systemctl start frpc@dn1_es
```

- 验证是否启动成功：

```shell
curl http://es.yannises.cn/
```

#### 启动MySQL服务及对外访问（1个）

- 启动MySQL服务：

```shell
sudo systemctl start  frpc@dn1_mysql_hn
```

- 验证启动结果（客户端验证通过即可）：

```shell
mysql -uadminc -p -hcn9.frp.cool -p12921
```

#### 启动Redis服务及对外访问（1个）

- 启动Redis服务：

```shell
sudo systemctl start frpc@dn1_redis
```

- 验证启动结果（命令行通过即可）：

```shell
redis-cli --raw -h cn-zj2.frp.cool -p 12220
```


## Dev环境前端

 启动地址：http://localhost:9528/ 


## AppRun服务端代码打包部署

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


