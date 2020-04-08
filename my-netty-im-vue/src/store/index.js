import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    personalInformation:{
      avatarUrl:require("@/assets/img/me.jpg")
    },
    chat: {
      title: ""
    }
  },
  mutations: {
    setChatTitle(state, title) {
      state.chat.title = title;
    }
  },
});
