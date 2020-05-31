<template>
    <div class="chat-item-div">
      <el-avatar :class="userStatus" :size="45" :src="avatarUrl"
                 class="avator"></el-avatar>
      <span class="chat-obj-name-span">{{remarkName}}</span>

    </div>
</template>

<script>

  import Constant from '@/constants';


  export default {
    name: "ChatFriendItem",
    props: {
      remarkName: {
        type: String,
        required: true,
      },
      friendName: {
        type: String,
        required: true,
      },
      friendId: {
        type: String,
        required: true,
      },
      avatarSrc: {
        type: String,
        required: false
      },
      userStatus: {
        type: String,
        required: false
      }
    },
    data() {
      return {}
    },
    computed: {
      avatarUrl() {
        return Constant.DEFAULT_IMG_URL_PREFIX + this.avatarSrc;
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
.chat-obj-name-span {
  display: inline-block;
  vertical-align: top;
  height: 45px;
  line-height: 45px;
  width: calc(100% - 50px);
  text-align: left;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  cursor: context-menu;
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
