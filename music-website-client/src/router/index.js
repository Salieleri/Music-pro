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
        },
        {
          path: '/registry',
          name: 'registry',
          component: () => import('@/pages/registry')
        },
        {
          path: '/search',
          name: 'search',
          component: () => import('@/pages/Search')
        },
        {
          path: '/setting',
          name: 'setting',
          component: () => import('@/pages/setting')
        },
        {
          path: 'SongList',
          name: 'SongList',
          component: () => import('@/pages/SongList')
        },
        {
          path: 'song-list-album/:id',
          name: 'song-list-album',
          component: () => import('@/pages/SongList_Album')
        },
        {
          path: 'mymusic',
          name: 'mymusic',
          component: () => import('@/pages/mymusic')
        },
        {
          path: 'songcomment/:id',
          name: 'songcomment',
          component: () => import('@/pages/songcomment')
        }
      ]
    }
  ],
  mode: 'history'
})
