<template>
  <div class="window-div">
    <el-container>
      <el-header>Header</el-header>
      <el-main>Main</el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "Window"
    }
</script>

<style scoped>
.window-div{
  margin:0 auto;
  border:1px solid #000;
  width: 75%;
  height: 80%;
  border-radius: 10px
}
.el-header,.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
  width: 100%;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  height: 100%;
}
</style>
