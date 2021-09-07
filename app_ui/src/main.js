import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI)

// 阻止启动生产消息
// 参考：https://blog.csdn.net/losedguest/article/details/86569293
Vue.config.productionTip = false

// main.js文件 的作用
// 对于 webpack：main.js是entry的配置，是webpack的入口文件，webpack先读取main.js，进行依赖收集然后打包编译
// 对于 Vue：bootstrap vue（Vue是 '单页应用'，在该文件中初始化一个根组件，类似于计算机启动时的'引导程序'），同时包含需要依赖的资源，供项目使用
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
