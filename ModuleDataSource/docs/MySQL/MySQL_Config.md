# MySQL

## 安装

- [在Ubuntu20.04上安装MySQL8.0及正确配置](https://blog.csdn.net/cruiserblog/article/details/106934570)

## 配置

- 配置文件：`/etc/mysql/mysql.cnf`
- 数据库名：area_database、49_class_database
- 端口号：3306
- 服务器地址：macOS/Ubuntu
- 用户名：root
- 密码(macOS/Ubuntu)：Admin_1126



## MySQL多数据源配置

- [如何在springboot里配置多个mysql数据源?](https://blog.csdn.net/qq_33036061/article/details/105274651)

## 命令

**启动MySQL服务**


```shell
service mysql start
```

**连接MySQL**：

```shell
alias mysqlstart='sudo mysql -u root -p'
```

**基本操作**：

进入 `MySQL`环境后：

- 显示所有数据库：

```
show databases;
```

- 使用当前数据库：


```
use XXX
```

- 显示当前数据库中所有的表


```
show tables;
```

