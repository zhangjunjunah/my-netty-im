<template>
  <div class="notification-item-div">
    <el-avatar :size="45" :src="avatarUrl"></el-avatar>
    <div class="notification-info-div">
      <span>姓名</span>
      <span>好友请求</span>
      <el-dropdown @click="handleClick" class="fr" split-button type="primary">
        操作
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>同意</el-dropdown-item>
          <el-dropdown-item>拒绝</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
  export default {
    name: "NotificationItem",
    data() {
      return {
        avatarUrl: ""
      }
    },
    methods: {
      handleClick() {

      }
    }
  }
</script>

<style scoped>
  .notification-item-div {
    text-align: left;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60px;

  }

  .notification-info-div {
    display: inline-block;
    width: 90%;
  }

  .fr {
    float: right;
  }
</style>
