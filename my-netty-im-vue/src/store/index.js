import Vue from 'vue'
import VueWebSocket from '@/websocket';
import Constant from '@/constants'
import MessagePayload from '@/websocket/message/MessagePayload'

import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    personalInformation: {
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
    messages: [],
    friendList: [],
    vueWebsocket: null,
    activeTime: null
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
      let vueWebsocket = new VueWebSocket(Constant.WS_PROTOCOL, Constant.WS_IP, Constant.WS_PORT, Constant.WS_URI, Constant.HEART_BEAT_TIMEOUT);
      vueWebsocket.connect();
      state.vueWebsocket = vueWebsocket;
    },
    connect(state) {
      let messagePayload = new MessagePayload(Constant.CONNECT, JSON.stringify(state.personalInformation));
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    sendPrivateMsg(state, msg) {
      let messagePayload = new MessagePayload(Constant.PUBLISH_PRIVATE, JSON.stringify(msg));
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    getMessage(state) {
      let msg = {};
      msg.receiver = state.personalInformation.userId;
      msg.sender = state.chat.activeId;
      let messagePayload = new MessagePayload(Constant.GET_HIS_MSG, JSON.stringify(msg));
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    lastActiveTime(state, activeTime) {
      state.activeTime = activeTime;
    },
    getLastActiveTime(state) {
      return state.activeTime;
    }
  },
  actions: {
    initWebSocket: ({commit}) => commit('initWebSocket'),
    getMessage: ({commit}) => commit('getMessage'),
    lastActiveTime: ({commit}) => commit('lastActiveTime'),
  }
});
