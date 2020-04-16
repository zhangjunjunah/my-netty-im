import AbstractMsgHandler from "./AbstractMsgHandler";
import Constants from '@/constants'

import store from '@/store';

export default class GetHisMsg extends AbstractMsgHandler {
  match(signal) {
    return Constants.GET_HIS_MSG == signal;
  }

  handleMsg(data) {
    store.state.messages.splice(0);
    for (let message of data) {
      store.state.messages.push(message);
    }
  }
}
