<template>
  <el-row>
    <el-col :span="5" class="friend-col">
      <friend-main @setCurrentFriend="setCurrentFriend"></friend-main>
    </el-col>
    <el-col :span="19">
      <component v-bind:is="currentWindow"></component>
    </el-col>
  </el-row>
</template>

<script>
  import BlankWindow from '@/components/message/mainWindow/BlankWindow'
  import FriendMain from '@/components/contacts/friend/FriendMain'
  import FriendIntroduceMain from '@/components/contacts/friendIntroduce/FriendIntroduceMain'

  export default {
    name: "ContactsMain",
    components: {
      FriendMain,
      BlankWindow,
      FriendIntroduceMain
    },
    data() {
      return {
        currentFriend: {
          friendId: null,
          friendName: null,
          avatarSrc: null
        }
      }
    },
    methods: {
      setCurrentFriend(data) {
        this.$set(this.currentFriend, "friendId", data.friendId);
        this.$set(this.currentFriend, "friendName", data.friendName);
        this.$set(this.currentFriend, "avatarSrc", data.avatarSrc);
      }
    },
    computed: {
      currentWindow() {
        if (this.currentFriend.friendId == null) {
          return "BlankWindow";
        }
        return "FriendIntroduceMain";
      }
    }
  }
</script>

<style scoped>
  .el-col {
    height: 100%;
  }

  .el-row {
    height: 100%;
  }

  .friend-col {
    position: relative;
  }

</style>
