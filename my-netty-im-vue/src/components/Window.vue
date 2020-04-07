<template>
  <div class="window-div">
    <el-container>
      <el-header>Header</el-header>
      <el-main >
        <el-row>
          <el-col :span="5">
            <chat-list></chat-list>
          </el-col>
          <el-col :span="19">
            <chat-window></chat-window>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    import ChatList from '@/components/friend/ChatList'
    import ChatWindow from '@/components/mainWindow/ChatWindow'
    export default {
        name: "Window",
        components:{
          ChatList,
          ChatWindow
        }
    }
</script>

<style scoped>
.window-div{
  margin:0 auto;
  border:1px solid rgba(255, 227, 236, 0);
  width: 75%;
  height: 80%;
  border-radius: 5px
}
.el-header,.el-footer {
  background-color: #148fed;
  color: #fff1ff;
  text-align: center;
  line-height: 60px;
  width: 100%;
  border-radius: 5px 5px 0px 0px;
}
.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: 100%;
  border-radius: 0px 0px 5px 5px;
  padding: 0px;
}
.el-container {
  height:100%;
  border-radius: 0px 0px 5px 5px
}
.el-col{
    height: 100%;
  }
.el-row{
  height: 100%;
}
</style>
