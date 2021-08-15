## my_vue

- Project setup
```
npm install
```

- Compiles and hot-reloads for development
```
npm run serve
```

- Compiles and minifies for production
```
npm run build
```

- Lints and fixes files
```
npm run lint
```

- Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

## scss配置使用
> https://blog.csdn.net/zxb89757/article/details/103256614

依赖需要："node-sass": "^4.14.1","sass-loader": "^8.0.2","sass-resources-loader": "^2.0.3",

## package.json文件依赖更新

```
npm i -g npm-check-updates
```

```
ncu -u
```

```
npm install
```

## WebStorm 代码格式化符合eslint风格 设置修改
> https://blog.zuiyu1818.cn/posts/vue_webstorm.html

## WebStorm+Vue+Chrome debug断点调试
 [配置参考](https://www.jianshu.com/p/f0e69b537f9b)
 
 [启动方式参考](https://www.cnblogs.com/dafei4/p/13027340.html)
 
## （build）打包后打开index.html文件时，浏览器里一片空白问题
[参考](https://blog.csdn.net/Oralinge/article/details/100072765?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)

## 打包时productionSourceMap设置的不同效果

 ![](https://github.com/YannisCheng/my_vue/blob/master/local_img/productionSourceMap%E8%AE%BE%E7%BD%AE%E7%9A%84%E4%B8%8D%E5%90%8C%E6%95%88%E6%9E%9C.png)
 
## vue-devtool 浏览器调试工具

## 全局安装cordova：
   
```
npm install -g cordova
```

## Vue项目打包Android Apk

[参考链接、亲测有效](https://blog.csdn.net/Two_Too/article/details/83095177)

### 1.Vue项目打包

### 2.安装插件

```
 npm install -g cordova 
```

### 3.通过 cordova 创建项目：项目名、包名

```
cordova create android-app com.yannis.vuedemo
```

### 4.将`Vue`打包后的`dist文件`中的`内容`，粘贴至新建项目中`www文件`下

### 5.进入新创建的`Android项目`根目录，执行一下命令打包Android项目：

```
cordova platforms add android --save
```

### 6.检查Android构建环境

```
cordova requirements 
```

### 7.生成秘钥（`mytest.keystore`文件前不写指定目录就认为值当前项目根目录）

```
keytool -genkey -v -keystore mytest.keystore -alias mytest -keyalg RSA -validity 20000
```

### 8.生成带有签名的Apk

```
keytool -genkey -v -keystore D:\mytest.keystore -alias mytest -keyalg RSA -validity 20000
```

### 未签名Apk

```
cordova build android --release 
```
   
