import AbstractMsgHandler from "./AbstractMsgHandler";
import Constants from '@/constants'

import store from '@/store';

export default class ReceiveMsg extends AbstractMsgHandler {
  match(signal) {
    return Constants.PUBLISH_PRIVATE == signal;
  }

  handleMsg(data) {
    console.log("store", store.state.chat.activeId);
    console.log("sender", data.sender);
    debugger;
    if (data.sender == store.state.chat.activeId) {
      let m = {};
      m.self = false;
      m.content = data.content;
      m.timeStr = data.timeStr;
      store.state.message.push(m);
    }
  }
}
