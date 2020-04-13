export default class VueWebSocket {
  constructor(ws_protocol, ip, port) {
    this.ws_protocol = ws_protocol;
    this.ip = ip;
    this.port = port;
    this.url = this.ws_protocol + "://" + this.ip + ":" + this.port;
  }

  connect() {
    this.ws = new WebSocket(this.url);
    let that = this.ws;
    this.ws.onopen = function (event) {
      console.log("ws open");
    }
    this.ws.onmessage = function (event) {
      console.log("ws onmessage[" + event.data + "]");
      websocketObj.processMessage(event.data);
      websocketObj.lastInteractionTime(new Date().getTime());
    }
    this.ws.onclose = function (event) {
      console.log("ws onclose");
      websocketObj.ws.close();
      clearInterval(websocketObj.pingIntervalId);
      if (!websocketObj.userDisconnect) {
        console.log("reconnect websocket");
        websocketObj.reconnect(event);
      }
    }
    this.ws.onerror = function (event) {
      console.log("connect error");
    }
  }


}
