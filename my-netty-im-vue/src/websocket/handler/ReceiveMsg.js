import AbstractMsgHandler from "./AbstractMsgHandler";
import Constants from '@/constants'

import store from '@/store';

export default class ReceiveMsg extends AbstractMsgHandler {
  match(signal) {
    return Constants.PUBLISH_PRIVATE == signal;
  }

  handleMsg(data) {
    if (data.sender == store.state.chat.activeId) {
      let m = {};
      m.self = false;
      m.content = data.content;
      m.timeStr = data.timeStr;
      store.state.messages.push(m);
    }
  }
}
