<template>
  <div class="header-div">
    <div class="avatar-div">
      <el-avatar :size="45" :src="avatarUrl" class="avator"></el-avatar>
    </div>
    <div class="tab-div">
      <i class="iconfont iconmessage tab-icon"></i>
      <i class="iconfont iconlianxiren2 tab-icon"></i>
      <i class="iconfont iconicon_- tab-icon"></i>
    </div>
  </div>
</template>

<script>
  export default {
    name: "HeaderWindow",
    data() {
      return {}
    },
    computed: {
      avatarUrl() {
        if (this.$store.state.personalInformation.avatarUrl == '') {
          return;
        }
        return require("@/" + this.$store.state.personalInformation.avatarUrl);
      }
    }
  }
</script>

<style scoped>
  .header-div {
    width: 100%;
    height: 100%;
    vertical-align: top;
  }

  .avatar-div {
    display: inline-block;
    float: left;
  }

  .tab-div {
    display: inline-block;
    height: 100%;
    margin-top: -5px;
  }

  .tab-icon {
    font-size: 28px;
    margin: 10px;
    height: 100%;

  }
</style>
