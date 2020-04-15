import MsgHandler from "./MsgHandler";

export default class AbstractMsgHandler extends MsgHandler {

  constructor(vueWebsocket, store) {
    super();
    this.vueWebsocket = vueWebsocket;
    this.store = store;
  }

  get vueWebsocketClient() {
    return this.vueWebsocket;
  }

}
