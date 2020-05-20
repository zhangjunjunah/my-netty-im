<template>
  <div class="friend-list-div">
    <el-tree :data="friends" :props="defaultProps" @node-click="handleNodeClick" node-key="id">
      <component :is="node.data.groupId==null?'FriendItem':'GroupItem'" :node="node" slot-scope="{ node, data }">
      </component>
    </el-tree>
    <el-button circle class="add-friend-button" icon="el-icon-plus"></el-button>
  </div>
</template>

<script>

  import FriendItem from '@/components/contacts/friend/FriendItem'
  import GroupItem from '@/components/contacts/friend/GroupItem'

  export default {
    name: "FriendMain",
    components: {
      FriendItem,
      GroupItem
    },
    data() {
      return {
        defaultProps: {
          children: 'friendList',
          label: 'groupName'
        }
      }
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      adaptFriendRel(friendRel) {
        let groupList = [];
        for (let g of friendRel) {
          let group = {};
          group.label = g.groupName;
          group.id = g.groupId;
          groupList.push(group);
          if (g.friendList == null) {
            continue;
          }
          let children = [];
          for (let f of g.friendList) {
            let friend = {};
            friend.label = f.friendName;
            children.push(friend);
          }
          group.children = children;
        }
        return groupList;
      }
    },
    computed: {
      friends() {
        /*if (this.$store.state.friendRel == null) {
          return [];
        }
        return this.adaptFriendRel(this.$store.state.friendRel);*/
        return this.$store.state.friendRel;

      }
    },
  }
</script>

<style scoped>
  .friend-list-div {
    height: 100%;
    background-color: #fafafa;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    overflow: auto;
    margin-top: 2px;
  }

  .friend-list-div .el-tree {
    background-color: #fafafa;
  }

  .friend-list-div .el-tree {
    color: black;
  }

  .add-friend-button {
    position: absolute;
    bottom: 20px;
    right: 20px;
    background-color: #0181ef;
    color: white;
  }

</style>
