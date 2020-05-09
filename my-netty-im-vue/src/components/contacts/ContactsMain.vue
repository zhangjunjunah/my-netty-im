<template>
  <el-row>
    <el-col :span="5">
      <friend-main></friend-main>
    </el-col>
    <el-col :span="19">
      <component v-bind:is="currentWindow"></component>
    </el-col>
  </el-row>
</template>

<script>
  import BlankWindow from '@/components/message/mainWindow/BlankWindow'
  import FriendMain from '@/components/contacts/friend/FriendMain'

  export default {
    name: "ContactsMain",
    components: {
      FriendMain,
      BlankWindow
    },
    data() {
      return {
        currentWindow: "BlankWindow"
      }
    }
  }
</script>

<style scoped>

</style>
