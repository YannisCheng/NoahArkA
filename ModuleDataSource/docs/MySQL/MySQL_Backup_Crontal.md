# MySQL数据备份工具-mysqldump

- [MYSQL配置文件、备份与恢复](https://www.cnblogs.com/lynk/p/10413075.html)
- [Centos下mysql数据库备份与恢复的方法](https://www.cnblogs.com/running-mydream/p/4682182.html)
- [linux定时备份mysql数据库，及解决crontab执行时生成数据库文件为空的问题](https://blog.csdn.net/sanyuesan0000/article/details/52690492)

## MySQl

### mysqldump工具
/usr/local/bin/mysqldump
/usr/local/Cellar/mysql/8.0.21/bin/mysqldump
/System/Volumes/Data/usr/local/bin/mysqldump
/System/Volumes/Data/usr/local/Cellar/mysql/8.0.21/bin/mysqldump

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

macOS下本地备份可用脚本。

```
#!/bin/bash

# - - - - - - - - - - - - - - - - 本地备份 变量设置 - - - - - - - - - - - - - - - -
#用户名
username=root
#密码
password=Admin_1126
#将要备份的数据库
database_name=49_class_database
#执行目录下的保存备份个数
number=3
#备份保存路径
backup_dir=/Users/yannischeng/YC_USR/MysqlDump
#日期
dd=`date +%Y-%m-%d-%H-%M-%S`
#如果文件夹不存在则创建
if [ ! -d $backup_dir ];
then
    mkdir -p $backup_dir;
fi

# - - - - - - - - - - - - - - - - 本地备份 命令执行 - - - - - - - - - - - - - - - -
#简单写法  mysqldump -u root -p123456 users > /root/mysqlbackup/users-$filename.sql
# 一定要注意mysqldump使用全绝对路径，否则定时任务执行后得到的文件大小为0
# /usr/local/bin/mysqldump -u $username -p$password $database_name：备份$database_name数据库
# | gzip：对备份后的文件进行压缩
/usr/local/bin/mysqldump -u $username -p$password $database_name | gzip > $backup_dir/$database_name-$dd.sql.gz

#写创建备份日志
echo "create $backup_dir/$database_name-$dd.dupm" >> $backup_dir/create_log.txt

#找出需要删除的备份
delfile=`ls -l -crt  $backup_dir/*.sql.gz | awk '{print $9 }' | head -1`

#判断现在的备份数量是否大于$number
count=`ls -l -crt  $backup_dir/*.sql.gz | awk '{print $9 }' | wc -l`

if [ $count -gt $number ]
then
  #删除最早生成的备份，只保留number数量的备份
  rm $delfile
  #写删除文件日志
  echo "delete $delfile" >> $backup_dir/delete_log.txt
fi

# - - - - - - - - - - - - - - - - 远程传输 命令执行 - - - - - - - - - - - - - - - -
# 远程文件传输
#scp $backup_dir/$database_name-$dd.sql adminc@192.168.10.112:/home/adminc/文档/
```


## 表数据导入 source

参考：[Failed to open file ''****.sql'', error: 2的解决方案](https://blog.csdn.net/u010268820/article/details/85989433)

1. 进入mysql命令行
2. 通过 `use database` 选定数据库
3. 执行 `source 全路径SQL文件` 导入sql中的数据

