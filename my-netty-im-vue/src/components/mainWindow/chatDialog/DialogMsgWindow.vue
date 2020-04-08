<template>
  <div class="dialog-msg-div">
    <ul v-for="item in chatlist.messages">
      <div class="msg-main-div" :class="item.self==true?'self':''">
        <div class="msg-avatar-div">
          <el-avatar :size="45" :src="item.self==true?selfAvatarUrl:chatlist.user.img" class="avator"></el-avatar>
        </div>
        <div class="content-div">{{item.content}}</div>
      </div>
    </ul>
  </div>
</template>

<script>
  const now = new Date();
    export default {
        name: "DialogMsgWindow",
        data(){
          return {
            chatlist:
              {
                id: 1,
                user: {
                  name: 'jack',
                  img: require('@/assets/img/jack.jpg')
                },
                messages: [
                  {
                    content: '测试',  //聊天内容
                    self: false
                  },
                  {
                    content: '这条消息非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常长',  //聊天内容
                    self: false
                  },
                  {
                    content: '你也可以发送消息哦!',
                    self: true
                  }
                ],
                index: 1  // 当前在聊天列表中的位置,从1开始

              },
          }
        },
      computed:{
          selfAvatarUrl(){
            return this.$store.state.personalInformation.avatarUrl;
          }
      }
    }
</script>

<style  scoped>
  .dialog-msg-div{
    min-height:514px;
    margin-top: 5px;

  }
  .msg-avatar-div{
    display: inline-block;
  }
  .msg-main-div{
    text-align: left;
    margin: 5px 10px;
  }
  .content-div{
    display: inline-block;
    margin:0px 10px;
    position: relative;
    padding: 10px;
    max-width: 450px;
    min-height: 45px;
    line-height: 25px;
    box-sizing: border-box;
    text-align: left;
    word-break: break-all;
    background-color: #eeeeee;
    border-radius: 8px;
    vertical-align: top;
    font-size: 15px;
    font-family: sans-serif;
    /*letter-spacing:1.5px;*/
  }
  .content-div::before {
    content: " ";
    position: absolute;
    top: 15px;
    right: 100%;
    border: 8px solid transparent;
    border-right-color: #eeeeee;
  }
  .self{
    text-align: right;
  }
  .self>.msg-avatar-div{
    float:right;
  }
  .self>.content-div{
    background-color: #2683f5;
    color:white;
  }
  .self>.content-div::before{
    right: -15px;
    border-right-color: transparent;
    border-left-color: #2683f5;
  }
</style>
