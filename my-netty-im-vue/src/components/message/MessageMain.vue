<template>
  <el-row>
    <el-col :span="5">
      <chat-list></chat-list>
    </el-col>
    <el-col :span="19">
      <component v-bind:is="currentWindow"></component>
    </el-col>
  </el-row>
</template>

<script>
  import ChatList from '@/components/message/chat/ChatList'
  import ChatMainWindow from '@/components/message/mainWindow/ChatMainWindow'
  import NotificationMainWindow from '@/components/message/mainWindow/NotificationMainWindow'
  import BlankWindow from '@/components/message/mainWindow/BlankWindow'
  import Constant from '@/constants'


  export default {
    name: "Message",
    components: {
      ChatList,
      ChatMainWindow,
      BlankWindow,
      NotificationMainWindow
    },
    computed: {
      currentWindow() {
        if (this.$store.state.chat.activeId == '') {
          return "BlankWindow";
        }
        return this.$store.state.chat.type == Constant.CHAT_TYPE_INFO ? "ChatMainWindow" : "NotificationMainWindow";
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
</style>
