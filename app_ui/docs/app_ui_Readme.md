# App_UI_Readme

2021-09-07 21:59:49 周二

## 参考
### 官网

- [Vue.js](https://cn.vuejs.org/)
- []()
- []()
- []()
- []()
  
### 操作
- []()
- [手摸手，带你用vue撸后台 系列](https://juejin.cn/post/6844903476661583880)


## 框架选择

- []()

## VUE项目结构描述

### 问题1：main.js文件的作用（Vue的启动、打包）

参考：https://www.zhihu.com/question/63576546/answer/1636719372

![图片](images/Vue启动过程图片描述.jpg)

1、`index.xml` 为vue项目 `默认首页`，里面默认引用了 `app.vue根组件`
2、`main.js` 为vue项目的 `入口文件`，加载了各种公共组件(需要引用和初始化组件实例)。
   比如：`app.vue`、`main.js` 中引入相关资源文件引入Vue实际完整写法是 ：  
   
   - `import Vue from "../node_modules/vue/dist/vue.js` 即从node_modules中加载相应名称的模块。
   - `import App from './App'就是引入同目录层次下的App.vue` 文件。
   - `import router from './router'` 引入路由文件。




## React.js

### 常用依赖

```yaml
  "devDependencies": {
    "webpack-bundle-analyzer": "^4.4.0",
  }
```
