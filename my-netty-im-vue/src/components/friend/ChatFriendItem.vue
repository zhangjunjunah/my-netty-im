<template>
    <div class="chat-item-div">
      <el-avatar :size="45" :src="avatarUrl" class="avator"></el-avatar>
      <span class="chat-obj-name-span">{{chatObjName}}</span>

    </div>
</template>

<script>
  export default {
    name: "ChatFriendItem",
    props: {
      chatObjName: {
        type: String,
        required: true,
      },
      avatarUrl:{
        type: String,
        required: false
      }

    },
  }
</script>

<style scoped>
.chat-item-div {
  height: 50px;
  text-align: left;
  margin-left: 10px;
  padding:5px 0px;
}
.chat-obj-name-span{
  display: inline-block;
  vertical-align: top;
  height: 45px;
  line-height: 45px;
  width: 173px;
  text-align: left;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
