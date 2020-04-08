<template>
    <div class="dialog-title-div">
      <span class="chat-title">{{title}}</span>
    </div>
</template>

<script>
  export default {
    name: "DialogTitle",
    computed:{
      title(){
        return this.$store.state.chat.title;
      }
    }
  }
</script>

<style scoped>
.dialog-title-div{
  height: 100%;

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
