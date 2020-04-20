<template>
  <div class="header-div">
    <div class="avatar-div">
      <el-avatar :size="45" :src="avatarUrl" class="avator"></el-avatar>
    </div>
    <div class="tab-div">
      <i :class="'MessageMain'==currentMain?'light':''" @click="switchMain('MessageMain')"
         class="iconfont iconmessage tab-icon"></i>
      <i :class="'ContactsMain'==currentMain?'light':''" @click="switchMain('ContactsMain')"
         class="iconfont iconlianxiren2 tab-icon"></i>
      <i :class="'CloudMain'==currentMain?'light':''" @click="switchMain('CloudMain')"
         class="iconfont iconicon_- tab-icon"></i>
    </div>
  </div>
</template>

<script>
  export default {
    name: "HeaderWindow",
    data() {
      return {
        currentMain: 'MessageMain'
      }
    },
    computed: {
      avatarUrl() {
        if (this.$store.state.personalInformation.avatarUrl == '') {
          return;
        }
        return require("@/" + this.$store.state.personalInformation.avatarUrl);
      }
    },
    methods: {
      switchMain(mainName) {
        this.currentMain = mainName;
        this.$emit("switchMain", mainName);
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
    color: gainsboro;

  }

  .light {
    color: white;
  }
</style>
