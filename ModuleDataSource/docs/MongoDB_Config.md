# MongoDB

- [基本使用](https://blog.csdn.net/yutu75/article/details/110941936)

## 安装

- [安装mongodb企业版](https://docs.mongodb.com/manual/tutorial/install-mongodb-enterprise-on-ubuntu/)


## 配置

配置文件：`/etc/mongod.conf`

文件权限修改：

```
sudo chown -R mongodb:mongodb /var/lib/mongodb
sudo chown -R mongodb:mongodb /var/log/mongodb
sudo chown mongodb:mongodb /tmp/*.sock
```

修改后需要重启：

```
sudo systemctl restart mongod
```

账号：

| 数据库 | 用户名 | 密码 |
| --- | :-- | --- |
| ~ | root | root|
|admin | admin | admin|
|mongoTest| mongoUser | mongo|



## 命令

### 服务相关

- **启动MongoDB服务：**

	```
	sudo systemctl start mongod
	```
	
- **查看MongoDB的服务运行状态：**

	```
	sudo systemctl status mongod
	```	
	
- **启动mongo的默认shell环境：**

	```
	mongo
	```
	
- **启动指定远程地址、指定端口号的mongo的shell环境：**

	```
	alias mongostart='mongo --port 27017  --authenticationDatabase "admin" -u "admin" -p'
	alias mongostartdb='mongo --port 27017  --authenticationDatabase "mongoTest" -u "mongoUser" -p'
	```

### 用户创建相关

#### 给Admin数据库创建账户管理员：admin
 
 当前`账户管理员(admin)`只能用于管理数据库`账号`，不能进行数据库操作。

- **进入/切换数据库到admin中：**


	```shell
	use admin
	```

- **执行创建命令：**

	```shell
	MongoDB Enterprise > db.createUser({user:'admin',pwd:'admin',roles:[{role:'userAdminAnyDatabase',db:'admin'}]})
	Successfully added user: {
		"user" : "admin",
		"roles" : [
			{
				"role" : "userAdminAnyDatabase",
				"db" : "admin"
			}
		]
	}
	```

#### 创建超级管理员：root

当前`超级管理员(root)`可以进行数据库相关`操作`。

- **进入/切换数据库到admin中：**

	```shell
	use admin
	```
	
- **执行创建命令：**

	```shell
	MongoDB Enterprise > db.createUser({user:'root',pwd:'root',roles:[{role:'root',db:'admin'}]})
	Successfully added user: {
		"user" : "root",
		"roles" : [
			{
				"role" : "root",
				"db" : "admin"
			}
		]
	}
	```

#### 创建用户自己的数据库的角色

`帐号` 是跟着 `数据库` **绑定** 的，所以是什么数据库的用户，就必须在指定库里授权和验证！

- **切换数据库，如果当前库不存在则自动创建：**

	```shell
	MongoDB Enterprise > use mongoTest
	switched to db mongoTest
	```

- **执行创建命令：**


	```shell
	MongoDB Enterprise > db.createUser({user:'mongoUser',pwd:'mongo',roles:[{role:'dbOwner',db:'mongoTest'}]})
	Successfully added user: {
		"user" : "mongoUser",
		"roles" : [
			{
				"role" : "dbOwner",
				"db" : "mongoTest"
			}
		]
	}
	```

## 异常：

- [MongoDB 启动报错ExecStart=/usr/bin/mongod --config /etc/mongod.conf (code=exited, status=14)](https://blog.csdn.net/petrel2015/article/details/106759071)

## 卸载：

**注意：要卸载掉原版本中的 `各项服务`**

```
sudo apt-get purge mongodb-org
sudo apt-get autoremove mongodb-org-mongos mongodb-org-tools mongodb-org mongodb-org-server mongodb-org-shell
```


