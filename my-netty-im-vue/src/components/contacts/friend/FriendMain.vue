<template>
  <div class="friend-list-div">
    <el-tree :data="friends" :props="defaultProps" @node-click="handleNodeClick" node-key="id">
      <component :is="node.data.groupId==null?'FriendItem':'GroupItem'" :node="node" slot-scope="{ node, data }">
      </component>
    </el-tree>
    <el-button @click="addFriend()" circle class="add-friend-button" icon="el-icon-plus"></el-button>
    <el-dialog :visible.sync="dialogTableVisible" title="找好友">
      <div class="dialog-div">
        <el-form :model="ruleFriendForm" :rules="rules" ref="ruleFriendForm">
          <el-form-item prop="queryMsg">
            <el-input class="input-with-select" placeholder="请输入IM号、账号或昵称查找" v-model="ruleFriendForm.queryMsg">
              <el-button @click="queryFriend()" icon="el-icon-search" slot="append">查找</el-button>
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="search-result-div">
        <el-row :gutter="20" class="row-bg">
          <el-col :key="result.userId" :span="8" v-for="result in searchList">
            <search-friend-item :imUser="result"></search-friend-item>
          </el-col>
        </el-row>
      </div>
    </el-dialog>
  </div>

</template>

<script>

  import FriendItem from '@/components/contacts/friend/FriendItem'
  import GroupItem from '@/components/contacts/friend/GroupItem'
  import SearchFriendItem from '@/components/contacts/friend/searchFriend/SearchFriendItem'


  export default {
    name: "FriendMain",
    components: {
      FriendItem,
      GroupItem,
      SearchFriendItem
    },
    data() {
      return {
        defaultProps: {
          children: 'friendList',
          label: 'groupName',
        },
        dialogTableVisible: false,
        ruleFriendForm: {
          queryMsg: ""
        },
        rules: {
          queryMsg: [
            {required: true, message: '请输入IM号、账号或昵称再点击查找', trigger: 'blur'},
          ],
        },
        searchList: [
          {
            "userId": "708640701826465792",
            "userName": "test001",
            "password": "123456",
            "nickName": "机锋小摩托",
            "avatarSrc": "/jack.jpg",
            "phoneNum": null,
            "mail": null,
            "createTime": null
          },
          {
            "userId": "711598999756341248",
            "userName": "test004",
            "password": "123456",
            "nickName": "测试用户三",
            "avatarSrc": "/14/4/ff3376d2-c5ea-4bdd-adfb-2105bfc8a562.jpg",
            "phoneNum": null,
            "mail": null,
            "createTime": null
          },
        ]
      }
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
        this.$emit("setCurrentFriend", data);
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
      },
      addFriend() {
        this.dialogTableVisible = true;
      },
      queryFriend() {
        this.$refs['ruleFriendForm'].validate((valid) => {
          if (valid) {
            this.doQueryFriend();
          } else {
            return false;
          }
        });
      },
      doQueryFriend() {
        let url = "/api/user/queryFriend?queryMsg=" + this.ruleFriendForm.queryMsg;
        this.getRequest(url).then(res => {
          if (res.status == 200) {
            if (res.data.CODE == 200) {
              console.log("users", res.data);
            } else {
              this.$message({
                showClose: true,
                message: res.data.MESSAGE,
                type: 'warning',
                duration: 10000,
                offset: 55,
              });
            }
          }
        }, res => {
          console.error("/api/user/queryFriend error");
          this.$message({
            showClose: true,
            message: '服务端未响应,请联系管理员！',
            type: 'error',
            duration: 10000,
            offset: 55,
          });
        })
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

  .dialog-div {
    width: 60%;
    text-align: center;
    display: inline-block;
  }

  .search-result-div {
    width: 80%;
    text-align: center;
    display: inline-block;
    max-height: 300px;
    overflow-y: auto;
    overflow-x: hidden;
  }

  .el-row {
    margin-bottom: 20px;
  }

  .el-col {
    border-radius: 4px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

</style>
