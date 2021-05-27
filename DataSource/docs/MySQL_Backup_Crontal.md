# MySQL数据备份工具-mysqldump

- [MYSQL配置文件、备份与恢复](https://www.cnblogs.com/lynk/p/10413075.html)

## MySQl

查看MySQL配置项


```bash
mysql> show global variables;
```

## 工具：crontab

- [mac下crontab执行定时脚本](https://blog.csdn.net/ty_hf/article/details/72354230)

### macOS

基本命令：

```
crontab -e
crontab -l : 查看定时任务
crontab -r : 删除定时任务
```

脚本定时格式(共6列)：


```
f1 f2 f3 f4 f5 program
分 时 日 月 周 执行程序
```

f1：分钟，1～59
f2：小时，1～23（0表示子夜）
f3：日，1～31
f4：月，1～12
f5：星期，0～6（0表示星期天）
program：要运行的命令

例子：

```
 */1 * * * *  /Users/yannischeng/YC_USR/mysql_dump_bash.sh >> /Users/yannischeng/YC_USR/mysql_log.txt
```

更多描述：

![](/images/crontab_use.png)

## 示例脚本


```
#!/bin/bash

#保存备份个数，备份3天数据
number=31
#备份保存路径
backup_dir=/Users/yannischeng/YC_USR/MysqlDump
#日期
dd=`date +%Y-%m-%d-%H-%M-%S`
#备份工具
tool=mysqldump
#用户名
username=root
#密码
password=Admin_1126
#将要备份的数据库
database_name=49_class_database

#如果文件夹不存在则创建
if [ ! -d $backup_dir ];
then
    mkdir -p $backup_dir;
fi

#简单写法  mysqldump -u root -p123456 users > /root/mysqlbackup/users-$filename.sql
$tool -u $username -p$password $database_name > $backup_dir/$database_name-$dd.sql

#写创建备份日志
echo "create $backup_dir/$database_name-$dd.dupm" >> $backup_dir/log.txt

#找出需要删除的备份
delfile=`ls -l -crt  $backup_dir/*.sql | awk '{print $9 }' | head -1`

#判断现在的备份数量是否大于$number
count=`ls -l -crt  $backup_dir/*.sql | awk '{print $9 }' | wc -l`

if [ $count -gt $number ]
then
  #删除最早生成的备份，只保留number数量的备份
  rm $delfile
  #写删除文件日志
  echo "delete $delfile" >> $backup_dir/log.txt
fi

# 将本地的备份文件远程传递到另一台服务器的指定路径下
scp $backup_dir/$database_name-$dd.sql adminc@192.168.10.112:/home/adminc/文档/
```
