export default class StartConversationTransfer {
  userId;
  myBean;
  FriendBean;

  constructor(userId, myBean, FriendBean) {
    this.userId = userId;
    this.myBean = myBean;
    this.FriendBean = FriendBean;
  }

  toJSON() {
    let message = {
      userId: this.userId,
      myBean: this.myBean,
      FriendBean: this.FriendBean,
    }
    return message;
  }

}
