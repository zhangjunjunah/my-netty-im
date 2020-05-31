<template>
   <div class="chat-list-div">
     <div :class="activeId== item.userId?'selectedChatItem':'normalChatItem'" @click="selected(item)"
          class="chat-list-item" v-for="(item, index) in chatFriendList">
       <chat-friend-item :avatarSrc="item.avatarSrc" :friendId="item.friendId"
                         :friendName="item.friendName" :remarkName="item.remarkName"
                         :userStatus="item.userStatus"></chat-friend-item>
     </div>
   </div>
</template>

<script>
  import ChatFriendItem from '@/components/message/chat/ChatFriendItem'

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
        for (let friend of this.$store.state.conversationList) {
          let f = friend;
          list.push(f);
        }
        return list;
      }
    },
    methods: {
      selected: function (chatUser) {
        this.activeId = chatUser.friendId;
        this.$store.commit("setActiveChat", chatUser);
        this.$store.dispatch("getMessage", chatUser);
      }
    },
    beforeCreate() {

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
    /*min-width: 150px;*/
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
