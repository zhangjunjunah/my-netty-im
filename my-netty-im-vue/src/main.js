// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import TimeUtils from '@/util/TimeUtils';
import './permission';

import '../static/css/global.css'
import '../static/css/style.css'
import store from './store'
import {deleteRequest, getRequest, postRequest, putRequest} from '@/api'

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.$timeUtils = TimeUtils;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})



