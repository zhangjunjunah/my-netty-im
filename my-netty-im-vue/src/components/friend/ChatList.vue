<template>
   <div class="chat-list-div">
     <div v-for="(item, index) in chatFriendList">
       <chat-friend-item :chatObjName="item.name"></chat-friend-item>
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
              name:"测试用户一"
            },{
              name:"jack"
            },{
              name:"是一位名字非常非常非常非常非常非常非常长的朋友"
            }]
          }
      },
    }
</script>

<style scoped>
  .chat-list-div{
    height: 100%;
    background-color: #fafafa;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
  }
</style>
