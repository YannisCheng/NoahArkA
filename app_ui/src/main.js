import Vue from 'vue'
import router from './router'
import App from './App'
// 引入全局scss样式文件
import '@/styles/global.scss'
import ElementsUI from 'element-ui'

Vue.config.productionTip = false
Vue.config.devtools = true
Vue.use(ElementsUI)

new Vue({
  el: '#app',
  render: h => h(App),
  router
})
