export default class MessagePayload {
  sign;
  body;

  constructor(sign, body) {
    this.sign = sign;
    this.body = body;
  }

  toJSON() {
    let message = {
      sign: this.sign,
      body: this.body,
    }
    return JSON.stringify(message);
  }

}
