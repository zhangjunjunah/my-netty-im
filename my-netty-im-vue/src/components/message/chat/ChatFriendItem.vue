<template>
    <div class="chat-item-div">
      <el-avatar :class="userStatus" :size="45" :src="avatarUrl"
                 class="avator"></el-avatar>
      <span class="chat-obj-name-span">{{userName}}</span>

    </div>
</template>

<script>
  export default {
    name: "ChatFriendItem",
    props: {
      userName: {
        type: String,
        required: true,
      },
      userId: {
        type: String,
        required: true,
      },
      headPortrait: {
        type: String,
        required: false
      },
      userStatus: {
        type: String,
        required: false
      }
    },
    data() {
      return {
        avatarUrl: require("@/" + this.headPortrait),
      }
    }
  }
</script>

<style scoped>
.chat-item-div {
  height: 60px;
  text-align: left;
  margin-left: 10px;
  padding:5px 0px;
}
.chat-obj-name-span{
  display: inline-block;
  vertical-align: top;
  height: 45px;
  line-height: 45px;
  width: calc(100% - 50px);
  text-align: left;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.OFFLINE {
  -webkit-filter: grayscale(100%);
  -moz-filter: grayscale(100%);
  -ms-filter: grayscale(100%);
  -o-filter: grayscale(100%);
  filter: grayscale(100%);

  filter: gray;
}


</style>
