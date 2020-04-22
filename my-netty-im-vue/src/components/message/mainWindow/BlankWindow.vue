<template>
  <div class="blank-window-div">
    <div class="logo-div">
      <i class="iconfont icontianchongxing- logo-icon"></i>
    </div>
    <div class="slogan-div">
      主动一点，你的世界会<br/>有所不同
    </div>

  </div>
</template>

<script>
  export default {
    name: "BlankWindow"
  }
</script>

<style scoped>
  .blank-window-div {
    height: 100%;
    width: 100%;
  }

  .logo-div {
    height: 70%;
    width: 100%;
    position: relative;
    text-align: center;

  }

  .logo-icon {
    font-size: 100px;
    color: #148fed;
    vertical-align: bottom;
    bottom: 20%;
    margin: 0 auto;
    left: 45%;
    position: absolute;
  }

  .slogan-div {
    color: #ccc;
  }
</style>
