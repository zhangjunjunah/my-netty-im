import AbstractMsgHandler from "./AbstractMsgHandler";
import Constants from '@/constants'

import store from '@/store';

export default class NotifyFriendStatus extends AbstractMsgHandler {
  match(signal) {
    return Constants.PUBLISH_FRIEND == signal;
  }

  handleMsg(chatUser) {
    debugger;
    console.log("friendList", JSON.stringify(store.state.friendList));
    let index = store.state.friendList.findIndex(item => item.userId == chatUser.userId);
    if (index < 0) {
      return;
    }
    store.commit("updateFriendStatus", {index, chatUser});
  }
}
