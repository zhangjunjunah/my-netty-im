<template>
    <div class="input-div">
      <tool-bar></tool-bar>
      <div class="input-msg-div">
        <textarea @keyup.enter="sendMsg()" autofocus class="input-msg-textarea" placeholder="请输入内容" v-model="textarea">

        </textarea>
      </div>
      <div class="opera-div">
        <el-button @click="sendMsg()" size="small" type="primary">发送(Enter)</el-button>
      </div>
    </div>
</template>

<script>
  import ToolBar from '@/components/message/mainWindow/inputDialog/toolBar/ToolBar'

  export default {
        name: "InputWindow",
      components:{
        ToolBar
      },
      data(){
          return {
            textarea:"",
          }
      },
      methods:{
        sendMsg:function () {
          if(this.textarea==""){
            return;
          }
          let msg = {};
          msg.content=this.textarea;
          msg.self=true;
          msg.timeStr = this.$timeUtils.getNowTimeStr();
          this.$store.state.messages.push(msg);
          msg.receiver = this.$store.state.chat.activeId;
          msg.sender = this.$store.state.personalInformation.userId;
          msg.sendDate = msg.timeStr;
          this.$store.commit('sendPrivateMsg', msg);
          this.textarea="";

        }
      },
    }
</script>

<style scoped>
.input-div{
  margin-top: 5px;
  height: 155px;
}
  .input-msg-div{
    min-height: 70px;
  }
  .opera-div{
    height: 45px;
    text-align: right;
    vertical-align: middle;
  }
.input-msg-textarea{
  display: block;
  padding: 5px;
  line-height: 1.5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  background-color: #FFF;
  border:none;
  resize: none;
  outline: none;
  min-height: 40px;
  height: 65px;
  font-family: Micrsofot Yahei;
}
</style>
