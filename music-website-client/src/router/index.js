import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '*',
      redirect: '/404'
    },
    {
      path: '/404',
      name: '404',
      component: () => import('@/pages/404')
    },
    {
      path: '/',
      name: 'Main',
      component: () => import('@/pages/Main'),
      children: [
        {
          path: '/',
          name: 'home',
          component: () => import('@/pages/home')
        },
        {
          path: '/login',
          name: 'login',
          component: () => import('@/pages/loginin')
        }
      ]
    }
  ],
  mode: 'history'
})
