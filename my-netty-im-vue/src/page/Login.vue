<template>
    <div class="login-div">
      <el-container>
        <el-header style="height: 225px;"  class="login-header">
          <div class="header-div">
            <i class="iconfont  iconIMliaotian-duihua"></i>
            IM
          </div>
        </el-header>
        <el-main class="login-main">
          <div class="login-main-div">
            <el-input v-model="userId" id="userId-input" style="width: 300px;"  placeholder="请用户ID"></el-input>
            <el-input v-model="password" id="password-input" type="password" style="width: 300px;"  placeholder="密码随意"></el-input>
          </div>
          <div class="login-button-div">
            <el-button @click="login()" class="login-button" style="width: 300px" type="primary">登<i
              class="blank-i"></i>录
            </el-button>
          </div>
        </el-main>
      </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
      data(){
          return {
            userId:"",
            password:""
          }
      },
      methods:{
        login() {
          this.postRequest("/api/user/login", {
            userId: this.userId
          }).then(res => {
            if (res.status == 200) {
              if (res.data.CODE == 200) {
                //将朋友列表和个人信息放入store
                this.$store.commit("setPersonInfo", res.data.DATA.CHAT_USER);
                this.$store.commit("setFriendList", res.data.DATA.FRIEND_LIST);
                //跳转到聊天页面
                this.$router.push({path: '/conversation'})
              }

            }
          }, res => {
            console.error("/api/user/login error");
          })
        }
      },
    }
</script>

<style scoped>
.login-div{
  width: 540px;
  height: 420px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}
  .login-header{
    text-align: center;
    color: white;
    background: #0776d1;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
  }
  .login-main{
    height: 100%;
    background: #ebf2f9;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
  }
  .el-container{
    height: 100%;
    border-radius: 8px;
  }
  .header-div{
    box-sizing: border-box;
    height: 100%;
    text-align: center;
    padding: 65px;
    font-size: 100px;

  }
  .login-main-div{
    box-sizing: border-box;
    text-align: center;
    height: 75%;
  }
  .login-button-div{
    box-sizing: border-box;
    text-align: center;
    height: 25%;
  }
  .login-button{
    font-size: 15px;
  }
  .blank-i{
    margin: 0px 10px;
  }
  .iconfont{
    font-size: 100px;
  }

</style>
