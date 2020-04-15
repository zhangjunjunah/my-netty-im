<template>
    <div class="chat-window-div">
      <el-container>
        <el-header height="40px">
          <dialog-title ></dialog-title>
        </el-header>
        <el-main id="dialog-el-main" ref="main">
          <dialog-msg-window @setScroll="setScroll"></dialog-msg-window>
        </el-main>
        <el-footer height="160px">
          <input-window @pushMsg="pushMsg"></input-window>
        </el-footer>
      </el-container>
    </div>
</template>

<script>
  import DialogMsgWindow from '@/components/mainWindow/chatDialog/DialogMsgWindow'
  import InputWindow from '@/components/mainWindow/inputDialog/InputWindow'
  import DialogTitle from '@/components/mainWindow/chatTitle/DialogTitle'

  export default {
      name: "MainWindow",
      components:{
        DialogMsgWindow,
        InputWindow,
        DialogTitle
      },
      data(){
        return {
        }
      },
      methods:{
        pushMsg:function (msg) {
          //this.chatList.messages.push(msg);
        },
        setScroll(){
          console.log("scrollTop", this.$refs.main.$el.scrollTop);
          console.log("scrollHeight", this.$refs.main.$el.scrollHeight);
          this.$refs.main.$el.scrollTop = this.$refs.main.$el.scrollHeight;
        }
      },

  }
</script>

<style scoped>
.chat-window-div{
  height: 100%;
  width: 100%;
}
.el-container {
  height:100%;
  border-radius: 0px 0px 5px 5px;
  overflow: hidden;
  margin-left: 2px;
}
.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: 100%;
  padding: 0px;
  overflow: auto;

}
  .el-header{
    text-align: left;
    padding: 0 10px;
    vertical-align: middle;
    border-bottom:solid 1px #ebebec;
  }
.el-footer{
  border-top: solid 1px #ebebec;
}
</style>
