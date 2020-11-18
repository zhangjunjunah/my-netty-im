import Vue from 'vue'
import VueWebSocket from '@/websocket';
import Constant from '@/constants'
import MessagePayload from '@/websocket/message/MessagePayload'

import Vuex from 'vuex'
import StartConversationTransfer from "../websocket/transfer/StartConversationTransfer";

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
      avatarSrc: '',
      activeId: '',
      type: ''
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
    conversationList: [],
    friendRel: [],
    vueWebsocket: null,
    activeTime: null,
    currentWindow: "MessageMain"
  },
  mutations: {
    /**
     * 用户信息
     * @param state
     * @param chatUser
     */
    setPersonInfo(state, imUser) {
      state.personalInformation.avatarSrc = imUser.avatarSrc;
      state.personalInformation.userId = imUser.userId;
      state.personalInformation.userName = imUser.userName;
    },
    /**
     * 会话列表
     * @param state
     * @param conversationList
     */
    setConversationList(state, conversationList) {
      state.conversationList.splice(0);
      if (conversationList == null) {
        return;
      }
      for (let conversation of conversationList) {
        state.conversationList.push(conversation);
      }
    },
    /**
     * 朋友列表
     * @param state
     * @param friendList
     */
    setFriendRel(state, friendRel) {
      state.friendRel.splice(0);
      if (friendRel == null) {
        return;
      }
      for (let friend of friendRel) {
        state.friendRel.push(friend);
      }
    },
    /**
     * 当前聊天用户
     * @param state
     * @param activeChat
     */
    setActiveChat(state, activeChat) {
      state.chat.title = activeChat.remarkName;
      state.chat.userName = activeChat.remarkName;
      state.chat.avatarSrc = activeChat.avatarSrc;
      state.chat.activeId = activeChat.friendId;
      if (Constant.CHAT_NOTIFICATION_ID == activeChat.friendId) {
        state.chat.type = Constant.CHAT_TYPE_NOTIFICATION;
      } else {
        state.chat.type = Constant.CHAT_TYPE_INFO;
      }

    },
    initWebSocket(state) {
      let vueWebsocket = new VueWebSocket(Constant.WS_PROTOCOL, Constant.WS_IP, Constant.WS_PORT, Constant.WS_URI, Constant.HEART_BEAT_TIMEOUT);
      vueWebsocket.connect();
      state.vueWebsocket = vueWebsocket;
    },
    connect(state) {
      let messagePayload = new MessagePayload(Constant.CONNECT, state.personalInformation);
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    sendPrivateMsg(state, msg) {
      let messagePayload = new MessagePayload(Constant.PUBLISH_PRIVATE, msg);
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    getMessage(state) {
      let msg = {};
      let messagePayload = {};
      if (Constant.CHAT_TYPE_INFO === state.chat.type) {
        msg.receiver = state.personalInformation.userId;
        msg.sender = state.chat.activeId;
        messagePayload = new MessagePayload(Constant.GET_HIS_MSG, msg);
      } else {
        msg.userId = state.personalInformation.userId;
        messagePayload = new MessagePayload(Constant.GET_NOTIFICATION, msg);
      }
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    lastActiveTime(state, activeTime) {
      state.activeTime = activeTime;
    },
    getLastActiveTime(state) {
      return state.activeTime;
    },
    updateFriendStatus(state, data) {
      Vue.set(state.conversationList, data.index, data.chatUser);
    },
    switchMain(state, currentMain) {
      state.currentWindow = currentMain;
    },
    startConversation(state, friendMsg) {
      let startConversationTransfer = new StartConversationTransfer(state.personalInformation.userId, state.personalInformation, friendMsg);
      let messagePayload = new MessagePayload(Constant.FLUSH_CONVERSION, startConversationTransfer);
      state.vueWebsocket.send(messagePayload.toJSON());
    },
    addFriend(state, notificationMessage) {
      let messagePayload = new MessagePayload(Constant.NOTIFICATION, notificationMessage);
      state.vueWebsocket.send(messagePayload.toJSON());
    }

  },
  actions: {
    initWebSocket: ({commit}) => commit('initWebSocket'),
    getMessage: ({commit}) => commit('getMessage'),
    lastActiveTime: ({commit}) => commit('lastActiveTime'),
  }
});
