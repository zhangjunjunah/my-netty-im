// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import TimeUtils from '@/util/TimeUtils';

import '../static/css/global.css'
import '../static/css/style.css'
import store from './store'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$timeUtils = TimeUtils;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
