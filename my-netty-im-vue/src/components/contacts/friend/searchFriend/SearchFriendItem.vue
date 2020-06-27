<template>
  <div class="search-friend-div">
    <el-avatar :size="45" :src="avatarSrc"
               class="avator"></el-avatar>
    <div>
      <div class="nick-div">{{imUser.nickName}}</div>
      <el-button @click="addFriend()" size="mini" type="primary">加好友</el-button>
    </div>
  </div>
</template>

<script>

  import Constant from '@/constants';
  import store from "@/store";

  export default {
    name: "SearchFriendItem",
    props: {
      imUser: {
        type: Object
      }
    },
    data() {
      return {
        avatarSrc: Constant.DEFAULT_IMG_URL_PREFIX + this.imUser.avatarSrc,
      }
    },
    methods: {
      addFriend() {
        let notificationMessage = {};
        notificationMessage.notificationType = Constant.NOTIFICATION_MESSAGE_ADD_FRIEND;
        notificationMessage.receiver = this.imUser.userId;
        notificationMessage.sender = store.state.personalInformation.userId;
        store.commit("addFriend", notificationMessage);
      }
    }
  }
</script>

<style scoped>
  .search-friend-div {
    display: flex;
    margin-top: 15px;
  }

  .nick-div {
    padding: 2px 5px 8px 5px;

  }
</style>
