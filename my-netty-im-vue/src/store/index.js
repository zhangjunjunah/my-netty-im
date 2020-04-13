import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    personalInformation:{
      avatarUrl: '',
      userId: '',
      userName: ''
    },
    chat: {
      title: "",
      username: '',
      headPortrait: ''
    },
    message: []
  },
  mutations: {
    setChatTitle(state, title) {
      state.chat.title = title;
    }
  },
});
