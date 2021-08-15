import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRoutes = [
  // 当首次进入页面的时候，页面中并没有显示任何内容。这是因为首次进入页面时，它的路径是 '/'。重定向
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/home/AppIndex')
  },
  {
    path: '/main',
    name: 'main',
    component: () => import('@/views/main/MainPage')
  },

  {
    path: '/slotSample',
    name: 'slotSample',
    component: () => import('@/views/slotSample/SlotPage')
  },
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
