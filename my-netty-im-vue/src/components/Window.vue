<template>
  <div class="window-div">
    <el-container>
      <el-header>
        <header-window></header-window>
      </el-header>
      <el-main >
        <el-row>
          <el-col :span="5">
            <chat-list></chat-list>
          </el-col>
          <el-col :span="19">
            <component v-bind:is="currentWindow"></component>
            <main-window></main-window>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import HeaderWindow from '@/components/headerWindow/HeaderWindow'
  import ChatList from '@/components/friend/ChatList'
  import MainWindow from '@/components/mainWindow/MainWindow'
  import BlankWindow from '@/components/mainWindow/BlankWindow'

  export default {
    name: "Window",
    components: {
      HeaderWindow,
      ChatList,
      MainWindow,
      BlankWindow
    },
    data() {
      return {}
    },
    computed: {
      currentWindow() {
        return this.$store.state.chat.activeId == '' ? "BlankWindow" : "MainWindow"
      }
    },
    created() {
      this.$store.commit('initWebSocket');
      setTimeout(() => {
        this.$store.commit('connect')
      }, 1000);

    },
  }
</script>

<style scoped>
.window-div{
  margin:0 auto;
  border:1px solid rgba(255, 227, 236, 0);
  width: 75%;
  height: 80%;
  border-radius: 5px;

}
.el-header,.el-footer {
  background-color: #148fed;
  color: #fff1ff;
  text-align: center;
  line-height: 60px;
  width: 100%;
  border-radius: 5px 5px 0px 0px;
  padding-bottom: 2px;
  padding: 7.5px 13px;
}
.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: 100%;
  border-radius: 0px 0px 5px 5px;
  padding: 0px;
  overflow: hidden;
}
.el-container {
  height:100%;
  border-radius: 0px 0px 5px 5px;
  overflow: hidden;
}
.el-col{
    height: 100%;
  }
.el-row{
  height: 100%;
}
</style>
