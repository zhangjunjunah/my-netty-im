<template>
   <div class="chat-list-div">
     <div :class="activeId== item.userId?'selectedChatItem':'normalChatItem'" @click="selected(item)"
          class="chat-list-item" v-for="(item, index) in chatFriendList">
       <chat-friend-item :headPortrait="item.headPortrait" :userId="item.userId"
                         :userName="item.userName"></chat-friend-item>
     </div>
   </div>
</template>

<script>
  import ChatFriendItem from '@/components/friend/ChatFriendItem'

  export default {
      name: "ChatList",
      components:{
        ChatFriendItem
      },
      data(){
          return {
            activeId: ''
          }
      },
    computed: {
      chatFriendList() {
        let list = [];
        for (let friend of this.$store.state.friendList) {
          let f = friend;
          list.push(f);
        }
        return list;
      }
    },
      methods: {
        selected: function (chatUser) {
          this.activeId = chatUser.userId;
          this.$store.commit("setActiveChat", chatUser);
        }
      }
    }
</script>

<style scoped>
  .chat-list-div{
    height: 100%;
    background-color: #fafafa;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    overflow: auto;
    margin-top: 2px;
    min-width: 150px;
  }

  .chat-list-item:active{ background-color: #ebebec}
  .chat-list-item:hover{ background-color: #ebebec}
  .selectedChatItem{
    background-color: #ebebec
  }
  .normalChatItem{
    background-color: #fafafa;
  }

</style>
