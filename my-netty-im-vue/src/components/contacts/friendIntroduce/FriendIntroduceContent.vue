<template>
  <div class="friend-introduce-content-div">
    <div class="friend-avatar-div">
      <img :src="avatarSrc" class="friend-avatar-img"/>
    </div>
    <div class="friend-info-div">
      <div class="friend-name-div">
        {{currentFriend.remarkName}}
      </div>
      <div class="friend-intro-item" v-for="(u,i) in friendIntro">
        <span class="friend-intro-name">{{i}}</span>
        <span class="friend-intro-value">{{u}}</span>
      </div>
      <div @click="jumpLink" class="msg-div">
        <i class="el-icon-chat-dot-square chat-i"></i>
      </div>
    </div>
  </div>
</template>

<script>
  import Constant from '@/constants';
  import store from '@/store';

  export default {
    name: "FriendIntroduceContent",
    props: {
      currentFriend: {
        type: Object,
      }
    },
    data() {
      return {}
    },
    computed: {
      avatarSrc() {
        return Constant.DEFAULT_IMG_URL_PREFIX + this.currentFriend.avatarSrc;
      },
      friendIntro() {
        return {
          "账号": this.currentFriend.friendId,
          "昵称": this.currentFriend.friendName
        };
      }
    },
    methods: {
      jumpLink() {
        store.commit("startConversation", this.currentFriend);
        store.commit("switchMain", "MessageMain");
      }
    }
  }
</script>

<style scoped>
  .friend-introduce-content-div {
    width: 350px;
    padding: 5px 0px 5px 0px;
    min-height: 490px;
    display: inline-block;
    height: 90%;
  }

  .friend-avatar-div {
    width: 100%;
    height: 50%;
  }

  .friend-avatar-img {
    width: 100%;
    height: 100%;
    border-top-right-radius: 5px;
    border-top-left-radius: 5px;
  }

  .friend-info-div {
    width: 100%;
    height: 50%;
    background-color: white;
    position: relative;
    border-bottom-right-radius: 5px;
    border-bottom-left-radius: 5px;
  }

  .friend-name-div {
    padding-top: 10px;
    padding-bottom: 20px;
    font-weight: 500;
    font-size: 18px;
  }

  .friend-intro-item {
    font-weight: normal;
    padding-top: 10px;
    font-size: 12px;
    text-align: left;

  }

  .friend-intro-name {
    color: #b5b5b5;
    margin-right: 10px;
    margin-left: 25%;
  }

  .msg-div {
    position: absolute;
    width: 40px;
    height: 40px;
    z-index: 10;
    top: -20px;
    right: 20px;
    background-color: #0181ef;
    border-radius: 20px;
    line-height: 40px;
    cursor: pointer;
  }

  .chat-i {
    color: white;
    font-size: 20px;
    transform: rotateY(180deg);
  }
</style>
