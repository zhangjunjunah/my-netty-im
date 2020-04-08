<template>
   <div class="chat-list-div">
     <div v-for="(item, index) in chatFriendList" class="chat-list-item" @click="selected(item.id,item.name)" :class="activeId== item.id?'selectedChatItem':'normalChatItem'">
       <chat-friend-item :chatObjName="item.name" :avatarUrl="item.avatarSrc"></chat-friend-item>
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
            chatFriendList:[{
              id:1,
              name:"测试用户一",
              avatarSrc:require("@/assets/img/user1.jpg")
            },{
              id:2,
              name:"jack",
              avatarSrc:require("@/assets/img/jack.jpg")
            },{
              id:3,
              name:"是一位名字非常非常非常非常非常非常非常长的朋友",
              avatarSrc:require("@/assets/img/long.jpg")
            }],
            activeId: 0
          }
      },
      methods: {
        selected: function(chatId,name) {
          this.activeId = chatId;
          this.$store.commit("setChatTitle",name);
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
