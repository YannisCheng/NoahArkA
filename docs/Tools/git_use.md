# 关于Git的使用

2021-08-20 20:26:52 周五

## Git修改/切换远程仓库

1. **移除或重命名原来的源名称**

示例：

```shell
git remote rename origin old-origin
```

2. **设置新源的地址**

示例：

```shell
git remote add origin https://gitlab.com/wuxianqiang/my-project.git
```

3. **从新源pull内容**

示例：
注意：在第一次从新源中拉取内容时，一定要设置新源的：远程库名称（此处为：origin），新源的远程分支名称（此处为master），如果不修改则默认为 原来分支 的配置。

```shell
git pull origin master
```

4. **向新源push内容**

示例：
注意：在push时，同样需要设置新源的远程库名称、新源的远程分支名称。

```shell
git push -u rigin master
```