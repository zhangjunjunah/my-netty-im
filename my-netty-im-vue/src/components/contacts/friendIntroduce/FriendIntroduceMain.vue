<template>
  <div class="friend-introduce-main-div">
    <friend-introduce-content></friend-introduce-content>
  </div>
</template>

<script>
  import FriendIntroduceContent from '@/components/contacts/friendIntroduce/FriendIntroduceContent'

  export default {
    name: "FriendIntroduceMain",
    components: {
      FriendIntroduceContent
    }
  }
</script>

<style scoped>
  .friend-introduce-main-div {
    width: 100%;
    height: 100%;
    background-color: #e5e7ec;
    overflow: auto;
    text-align: center
  }
</style>
