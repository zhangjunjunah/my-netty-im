import Vue from 'vue'
import Router from 'vue-router'
import Window from '@/components/Window'
import Login from '@/page/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/conversation',
      name: 'Window',
      component: Window
    },
    {
      path:"/login",
      name:'login',
      component: Login
    }
  ],
  //mode: 'history'
})
