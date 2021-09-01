# Redis

- [菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
- Redis使用场景：

 [](/images/redis-使用场景.png)


## 安装

- [Ubuntu20.04 安装 redis](https://blog.csdn.net/wm9028/article/details/108796389)

## 配置

### 多数据源配置

 - [SpringBoot声明连接多个redis数据源配置模版](https://blog.csdn.net/weixin_36586564/article/details/105197361?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.control&spm=1001.2101.3001.4242)

### 基本配置

配置文件：`/etc/redis/redis.conf`
修改

- **远程访问 默认bind 127.0.0.1 ::1**

	```
	bind 0.0.0.0 
	```

- **更改端口**

	```
	port 6379 
	```

- **设置密码**

	```
	requirepass admin
	```

### 1台服务器创建多个Redis实例

- [一台服务器部署两个或多个Redis](https://blog.csdn.net/weixin_42555019/article/details/93066379?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.baidujs&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.baidujs)

- **配置新redis文件，避免两个redis冲突：**

	```
	cp /etc/redis/redis.conf  /etc/redis/redis6380.conf
	```

- **打开新创建的配置文件：**

	```
	sudo vim /etc/redis/redis6380.conf
	```

- **找到以下几项，进行修改：**

	```
	pidfile /var/run/redis/redis-server-6380.pid
	logfile /var/log/redis/redis-server-6380.log
	port 6380
	dbfilename dump_6380.rdb
	```

- **运行 `redis6380` 实例服务：**

	```
	sudo redis-server /etc/redis/redis6380.conf
	```
	
- **验证：查看是否有6380端口**

	```
	netstat -lnpt
	```
	
- **连接 `redis6380` 客户端：**

	```
	redis-cli -h 127.0.0.1 -p 6380 -a admin
	```

## 命令

- **添加开机启动项：**

	```
	systemctl enable redis-server.service
	```
	
- **启动Redis服务**

	```
	sudo systemctl start redis-server
	```

- **连接本地Redis：**

	```
	redis-cli
	```

- **连接远程服务器上的Redis：**

	```
	alias redistart79='redis-cli --raw -h 127.0.0.1 -p 6379 -a "admin"'
	alias redistart80='redis-cli --raw -h 127.0.0.1 -p 6380 -a "admin"'
	```

