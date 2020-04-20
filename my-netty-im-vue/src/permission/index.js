import router from "../router";
import store from "../store";

router.beforeEach((to, from, next) => {
  if (to.matched.some(m => m.meta.login)) {
    // 对路由进行验证
    if (store.state.personalInformation.userId != '') { // 已经登陆
      next()   // 正常跳转到你设置好的页面
    } else {
      // 未登录,跳转到登陆页面，并且带上 将要去的地址，方便登陆后跳转。
      next({path: '/login'})
    }
  } else {
    if (to.path == "/") {
      next({path: '/login'})
    } else {
      next()
    }
  }
})
