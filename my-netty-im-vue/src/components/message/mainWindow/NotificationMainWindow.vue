<template>
  <div class="notification-window-div">
    <el-container>
      <el-header height="40px">
        <dialog-title></dialog-title>
      </el-header>
      <el-main ref="main">
        <notification-window></notification-window>
      </el-main>
    </el-container>
  </div>
</template>

<script>

  import NotificationWindow from '@/components/message/mainWindow/notification/NotificationWindow'

  export default {
    name: "NotificationMainWindow",
    components: {
      NotificationWindow,
    },
  }
</script>

<style scoped>
  .notification-window-div {
    height: 100%;
    width: 100%;
  }

  .el-main {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    height: 100%;
    padding: 0px;
    overflow: auto;
    margin-left: 2px;

  }

  .el-header {
    text-align: left;
    padding: 0 10px;
    vertical-align: middle;
    border-bottom: solid 1px #ebebec;
  }

  .el-footer {
    border-top: solid 1px #ebebec;
  }


</style>
