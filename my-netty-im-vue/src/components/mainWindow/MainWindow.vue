<template>
    <div class="chat-window-div">
      <el-container>
        <el-header height="40px">
          <dialog-title ></dialog-title>
        </el-header>
        <el-main ref="main">
          <dialog-msg-window :chatList="chatList"@setScroll="setScroll" ></dialog-msg-window>
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
              chatList:
                {
                  id: 1,
                  user: {
                    name: 'jack',
                    img: require('@/assets/img/jack.jpg')
                  },
                  messages: [
                    {
                      content: '测试',  //聊天内容
                      self: false,
                      timeStr:"2020-04-08 15:33:00"
                    },
                    {
                      content: '这条消息非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常长',  //聊天内容
                      self: false,
                      timeStr:"2020-04-08 16:33:00"
                    },
                    {
                      content: '你也可以发送消息哦!',
                      self: true,
                      timeStr:"2020-04-09 15:33:00"
                    }
                  ],
                  index: 1  // 当前在聊天列表中的位置,从1开始

                },
        }
      },
      methods:{
        pushMsg:function (msg) {
          this.chatList.messages.push(msg);
        },
        setScroll(){
          console.log("scrollTop",this.$refs.main.scrollTop);
          console.log("scrollHeight",this.$refs.main.scrollHeight);
          this.$refs.main.scrollTop = this.$refs.main.scrollHeight;
        }
      }

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
