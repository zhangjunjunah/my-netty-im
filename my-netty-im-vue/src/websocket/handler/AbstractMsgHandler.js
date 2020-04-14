import MsgHandler from "./MsgHandler";

export default class AbstractMsgHandler extends MsgHandler {

  constructor(vueWebsocket) {
    super();
    this.vueWebsocket = vueWebsocket;
  }

  get vueWebsocketClient() {
    return this.vueWebsocket;
  }

}
