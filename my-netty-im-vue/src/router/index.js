import Vue from 'vue'
import Router from 'vue-router'
import Window from '@/components/Window'
import Login from '@/page/Login'
import Register from '@/page/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/conversation',
      name: 'Window',
      component: Window,
      meta: {login: true}
    },
    {
      path: "/login",
      name: 'login',
      component: Login
    },
    {
      path: "/register",
      name: 'register',
      component: Register
    }
  ],
  //mode: 'history'
})
