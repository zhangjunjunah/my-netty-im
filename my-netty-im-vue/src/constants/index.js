const WS_PROTOCOL = 'ws';
//const WS_IP = '192.1.216.140';
const WS_IP = 'localhost';
const WS_PORT = 8091;
const WS_URI = 'im';
//心跳时间30s
const HEART_BEAT_TIMEOUT = 30000;
//signal
const CONNECT = 'CONNECT';
const DISCONNECT = 'DISCONNECT';
const CONNECT_ACK = 'CONNECT_ACK';
const PUBLISH_PRIVATE = 'PUBLISH_PRIVATE';
const GET_HIS_MSG = 'GET_HIS_MSG';
const PUBLISH_FRIEND = 'PUBLISH_FRIEND';
const PING = 'PING';
export default {
  WS_PROTOCOL,
  WS_IP,
  WS_PORT,
  WS_URI,
  CONNECT,
  DISCONNECT,
  CONNECT_ACK,
  PUBLISH_PRIVATE,
  GET_HIS_MSG,
  PING,
  PUBLISH_FRIEND,
  HEART_BEAT_TIMEOUT
}
