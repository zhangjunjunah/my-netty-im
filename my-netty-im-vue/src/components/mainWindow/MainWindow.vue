<template>
    <div class="chat-window-div">
      <el-container>
        <el-header height="40px"><span class="chat-title">标题</span></el-header>
        <el-main >
          聊天窗体
        </el-main>
      </el-container>
    </div>
</template>

<script>
    export default {
        name: "MainWindow"
    }
</script>

<style scoped>
.chat-window-div{
  height: 100%;
  width: 100%;
}
  .el-header{
    text-align: left;
    padding: 0 10px;
    vertical-align: middle;
    border-bottom:solid 1px #ebebec;
  }
  .chat-title{
    height: 100%;
    display: block;
    vertical-align: text-bottom;
    line-height:40px;
    font-size: 18px;
    font-weight: bold;
  }
</style>
