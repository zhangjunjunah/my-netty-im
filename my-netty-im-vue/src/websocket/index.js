export default class VueWebSocket {
  constructor(ws_protocol, ip, port, uri) {
    this.ws_protocol = ws_protocol;
    this.ip = ip;
    this.port = port;
    this.uri = uri;
    this.url = this.ws_protocol + "://" + this.ip + ":" + this.port + "/" + this.uri;
  }

  connect() {
    this.ws = new WebSocket(this.url);
    let that = this.ws;
    this.ws.onopen = function (event) {
      console.log("ws open");
    }
    this.ws.onmessage = function (event) {
      console.log("ws onmessage[" + event.data + "]");
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


}
