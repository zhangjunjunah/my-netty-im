<template>
  <div class="friend-item-div">
    <el-avatar :size="45" :src="avatarSrc"
               class="avator"></el-avatar>
    <div class="friend-name-div">
      <span>{{node.data.remarkName}} ({{node.data.friendName}})</span>

    </div>
  </div>
</template>

<script>

  import Constant from '@/constants';

  export default {
    name: "FriendItem",
    data() {
      return {
        avatarSrc: Constant.DEFAULT_IMG_URL_PREFIX + this.node.data.avatarSrc,
      }
    },
    props: {
      node: {
        type: Object,
        required: true
      },
      computed: {}
    }
  }
</script>

<style scoped>
  .friend-item-div {
    width: 100%;
    height: 45px;
    vertical-align: middle;
    display: inline-block;
    text-align: left;
  }

  .friend-name-div {
    display: inline-block;
    vertical-align: top;
    height: 45px;
    line-height: 45px;
    font-size: 12px;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .friend-name-div span {

  }

</style>
