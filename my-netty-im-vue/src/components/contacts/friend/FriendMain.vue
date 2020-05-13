<template>
  <div class="friend-list-div">
    <el-tree :data="friends" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
  </div>
</template>

<script>
  export default {
    name: "FriendMain",
    data() {
      return {
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      adaptFriendRel(friendRel) {
        debugger;
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
        if (this.$store.state.friendRel == null) {
          return [];
        }
        this.adaptFriendRel(this.$store.state.friendRel);

      }
    }
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
</style>
