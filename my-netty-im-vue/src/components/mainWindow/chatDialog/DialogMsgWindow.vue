<template>
  <div class="dialog-msg-div">
    <ul v-for="item in chatlist.messages">
      <div class="msg-main-div">
        <div class="msg-avatar-div">
          <el-avatar :size="45" :src="chatlist.user.img" class="avator"></el-avatar>
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
                    date: now
                  },
                  {
                    content: '这条消息非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常非常长',  //聊天内容
                    date: now
                  },
                  {
                    content: '你也可以发送消息哦!',
                    date: now
                  }
                ],
                index: 1  // 当前在聊天列表中的位置,从1开始

              },
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
    margin-left: 10px;
    position: relative;
    padding: 5px 10px;
    max-width: 330px;
    min-height: 45px;
    line-height: 24px;
    box-sizing: border-box;
    text-align: left;
    word-break: break-all;
    background-color: #eeeeee;
    border-radius: 8px;
    vertical-align: top;
    font-size: 14px;
    font-family: "\5FAE\8F6F\96C5\9ED1";
    letter-spacing:1.5px;
  }
  .content-div::before {
    content: " ";
    position: absolute;
    top: 18px;
    right: 100%;
    border: 8px solid transparent;
    border-right-color: #eeeeee;
  }
</style>
