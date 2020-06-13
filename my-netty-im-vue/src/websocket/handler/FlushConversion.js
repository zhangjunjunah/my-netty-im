import AbstractMsgHandler from "./AbstractMsgHandler";
import Constants from '@/constants'

import store from '@/store';

export default class FlushConversion extends AbstractMsgHandler {
  match(signal) {
    return Constants.FLUSH_CONVERSION == signal;
  }

  handleMsg(data) {
    store.state.conversationList.splice(0);
    for (let conversation of data) {
      store.state.conversationList.push(conversation);
    }
    store.commit("setActiveChat", store.state.conversationList[0]);
  }
}
