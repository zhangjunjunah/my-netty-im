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
  import ChatList from '@/components/message/friend/ChatList'
  import MainWindow from '@/components/message/mainWindow/MainWindow'
  import BlankWindow from '@/components/message/mainWindow/BlankWindow'

  export default {
    name: "Message",
    components: {
      ChatList,
      MainWindow,
      BlankWindow
    },
    computed: {
      currentWindow() {
        return this.$store.state.chat.activeId == '' ? "BlankWindow" : "MainWindow"
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
