# Postgresql

## 安装

- [如何在 Ubuntu 20.04 上安装 PostgreSQL](https://www.itcoder.tech/posts/how-to-install-postgresql-on-ubuntu-20-04/)
- [Linux 上安装 PostgreSQL](https://www.runoob.com/postgresql/linux-install-postgresql.html)

## 配置

- 顶级用户名：postgres
- 密码：admin
- 端口号：5433。 [允许远程连接](https://blog.csdn.net/zgahxxwht/article/details/90609336)

## 命令


- **启动Postgresql服务：**

	```shell
	alias poststart='sudo /etc/init.d/postgresql start'
	```
	
- **停止Postgresql服务：**

	```shell
	alias poststop='sudo /etc/init.d/postgresql stop'
	```

- **连接本地Postgresql服务：**

	```shell
	alias psqllogin='sudo -u postgres psql'
	```

- **远程连接Postgresql服务**


	```shell
	psql -h localhost -p 5433 -U postgress runoobdb
	```


