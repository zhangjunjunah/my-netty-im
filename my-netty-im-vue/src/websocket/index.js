import ReceiveMsg from "./handler/ReceiveMsg";
import GetHisMsg from "./handler/GetHisMsg";

export default class VueWebSocket {

  handlerChain = [];

  constructor(ws_protocol, ip, port, uri) {
    this.ws_protocol = ws_protocol;
    this.ip = ip;
    this.port = port;
    this.uri = uri;
    this.url = this.ws_protocol + "://" + this.ip + ":" + this.port + "/" + this.uri;
    this.initHandlerChain();
  }

  connect() {
    this.ws = new WebSocket(this.url);
    let that = this;
    this.ws.onopen = function (event) {
      console.log("ws open");
    }
    this.ws.onmessage = function (event) {
      console.log("ws onmessage[" + event.data + "]");
      that.handleMsg(event.data);
    }
    this.ws.onclose = function (event) {
      console.log("ws onclose");
    }
    this.ws.onerror = function (event) {
      console.log("connect error");
    }
  }

  send(data) {
    console.log("send message " + data);
    this.ws.send(data);
  }


  initHandlerChain() {
    this.handlerChain.push(new ReceiveMsg(this));
    this.handlerChain.push(new GetHisMsg(this));
  }

  handleMsg(data) {
    let msg = JSON.parse(data);
    for (let i = 0; i < this.handlerChain.length; i++) {
      if (this.handlerChain[i].match(msg.sign)) {
        this.handlerChain[i].handleMsg(JSON.parse(msg.body));
      }
    }
  }
}
