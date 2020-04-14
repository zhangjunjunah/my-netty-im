import Vue from 'vue'
import VueWebSocket from '@/websocket';
import Constant from '@/constants'

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
      userName: '',
      headPortrait: '',
      activeId: ''
    },
    /**
     *messages: [
     {
                      content: '测试',  //聊天内容
                      self: false,
                      timeStr:"2020-04-08 15:33:00"
                    },
     {
                      content: '这条消息非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常长',  //聊天内容
                      self: false,
                      timeStr:"2020-04-08 16:33:00"
                    },
     {
                      content: '你也可以发送消息哦!',
                      self: true,
                      timeStr:"2020-04-09 15:33:00"
                    }
     ],
     */
    message: [],
    friendList: [],
    vueWebsocket: null
  },
  mutations: {
    /**
     * 用户信息
     * @param state
     * @param chatUser
     */
    setPersonInfo(state, chatUser) {
      state.personalInformation.avatarUrl = chatUser.headPortrait;
      state.personalInformation.userId = chatUser.userId;
      state.personalInformation.userName = chatUser.userName;
    },
    /**
     * 好友列表
     * @param state
     * @param friendList
     */
    setFriendList(state, friendList) {
      state.friendList.splice(0);
      for (let friend of friendList) {
        state.friendList.push(friend);
      }
    },
    /**
     * 当前聊天用户
     * @param state
     * @param activeChat
     */
    setActiveChat(state, activeChat) {
      state.chat.title = activeChat.userName;
      state.chat.userName = activeChat.userName;
      state.chat.headPortrait = activeChat.headPortrait;
      state.chat.activeId = activeChat.userId;

    },
    initWebSocket(state) {
      let vueWebsocket = new VueWebSocket(Constant.WS_PROTOCOL, Constant.WS_IP, Constant.WS_PORT, Constant.WS_URI);
      vueWebsocket.connect();
      state.vueWebsocket = vueWebsocket;
    }
  },
  actions: {
    initWebSocket: ({commit}) => commit('initWebSocket'),
  }
});
