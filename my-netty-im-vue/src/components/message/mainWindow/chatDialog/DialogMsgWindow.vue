<template>
  <div class="dialog-msg-div" ref="list">
    <ul >
      <li v-for="item in messageList">
      <div class="msg-main-div" :class="item.self==true?'self':''">
        <div class="time-div"><span>{{item.timeStr | showDate}}</span></div>
        <div class="msg-avatar-div">
          <el-avatar :size="45" :src="item.self==true?selfAvatarUrl:receiveAvatarUrl" class="avator"></el-avatar>
        </div>
        <div class="content-div">{{item.content}}</div>
      </div>
      </li>
    </ul>
  </div>
</template>

<script>
  import TimeUtils from '@/util/TimeUtils'

  export default {
    name: "DialogMsgWindow",
    props: {
    },
    computed: {
      selfAvatarUrl() {
        return require("@/" + this.$store.state.personalInformation.avatarSrc);
      },
      receiveAvatarUrl() {
        return require("@/" + this.$store.state.chat.headPortrait);
      },
      messageList() {
        console.log("messages");
        return this.$store.state.messages;
      },
    },
    mounted() {
      //  在页面加载时让信息滚动到最下面
      let that =this;
      console.log("mounted");
      setTimeout(function () {
        that.$emit("setScroll");
      }, 0)

    },
    watch: {
      // 发送信息后,让信息滚动到最下面
      messageList() {
        let that = this;
        setTimeout(function () {
          that.$emit("setScroll");
        }, 0);
      }
    },
    filters: {
      showDate(timeStr) {
        return TimeUtils.getShowTime(timeStr);
      }
    }
  }
</script>

<style  scoped>
  .dialog-msg-div{
    min-height: 200px;
    margin-top: 5px;
  }
  .msg-avatar-div{
    display: inline-block;
  }
  .msg-main-div{
    text-align: left;
    margin: 5px 10px;
  }
  .time-div{
    margin: 20px 0px;
    font-size: 13px;
    text-align: center;
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
