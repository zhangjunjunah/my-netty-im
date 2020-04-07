<template>
    <div class="chat-item-div">
      <el-avatar :size="39" :src="circleUrl" class="avator"></el-avatar>
      <span class="chat-obj-name-span">{{chatObjName}}</span>

    </div>
</template>

<script>
    export default {
        name: "ChatFriendItem",
        props:{
          chatObjName:{
            type: String,
            required: true,
          },
      },
    }
</script>

<style scoped>
.chat-item-div {
  width: 100%;
  height: 40px;
}

div:active{ background-color: #ebebec}
div:hover{ background-color: #ebebec}
.chat-obj-name-div{
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.avator{
  text-align: left;
}
</style>
