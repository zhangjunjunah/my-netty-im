<template>
  <div class="register-div">
    <el-container>
      <el-header class="register-header" style="height: 60px;">
        欢迎注册IM
      </el-header>
      <el-main class="register-main">
        <div>

        </div>
        <el-form label-width="60px" ref="form">
          <el-form-item label="">
            <div class="upload-div">
              <el-upload
                :action="uploadAvatar()"
                :before-upload="beforeAvatarUpload"
                :on-success="handleAvatarSuccess"
                :show-file-list="false"
                class="avatar-uploader">
                <img :src="imageUrl" class="avatar" v-if="imageUrl">
                <i class="el-icon-plus avatar-uploader-icon" v-else></i>
              </el-upload>
            </div>
          </el-form-item>
          <el-form-item label="">
            <el-input @input="keyUp" maxlength="25" ondragenter="return false"
                      onpaste="return false" placeholder="用户名(不能输入中文)" show-word-limit
                      v-model="registerForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input maxlength="25" placeholder="昵称" show-word-limit
                      v-model="registerForm.nickName"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input maxlength="25" placeholder="请输入密码" show-password show-word-limit
                      v-model="registerForm.password"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input maxlength="25" placeholder="请输入确认密码" show-password show-word-limit
                      v-model="registerForm.verifyPassword"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="register()" type="primary">立即注册</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import Constant from '@/constants';

  export default {
    name: "Register",
    data() {
      return {
        uploadImgUrl: "",
        defaultImgUrl: Constant.DEFAULT_UPLOAD_IMG,
        img: null,
        registerForm: {
          userName: null,
          password: null,
          nickName: null,
          verifyPassword: null,
          avatarSrc: null,
        }
      }
    },
    methods: {
      handleAvatarSuccess(res, file) {
        debugger;
        this.uploadImgUrl = URL.createObjectURL(file.raw);
        this.img = new Image();
        this.img.src = this.uploadImgUrl;
      },
      beforeAvatarUpload() {
      },
      uploadAvatar() {
        return process.env.API_HOST + "/api/upload/uploadAvatar";
      },
      register() {
        this.registerForm.avatarSrc = this.getBase64Image(this.img);
        this.postRequest("/api/user/register", this.registerForm).then(res => {
          if (res.status == 200) {
            if (res.data.CODE == 200) {
              //跳转到聊天页面
              this.$router.push({path: '/login'});
            }

          }
        }, res => {
          console.error("/api/user/register error");
        })
      },
      getBase64Image(img) {
        let canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        let ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, img.width, img.height);
        let ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
        let dataURL = canvas.toDataURL("image/" + ext);
        return dataURL;
      },
      keyUp(value) {
        this.registerForm.userName = this.registerForm.userName.replace(/[\u4e00-\u9fa5]/ig, '');
      }

    },

    computed: {
      imageUrl() {
        return this.uploadImgUrl == "" ? require("@/" + this.defaultImgUrl) : this.uploadImgUrl;
      },

    }
  }
</script>

<style scoped>
  .register-div {
    width: 540px;
    height: 610px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 8px;
  }

  .register-header {
    text-align: left;
    color: #000000;
    background: white;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
    font-size: 35px;
    line-height: 60px;
  }

  .register-main {
    height: 100%;
    background-color: white;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
    padding: 10px 70px 10px 10px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .avatar-uploader {
    text-align: center;
  }

  .upload-div {
    position: relative;
    text-align: center;
  }

  .el-button--primary {
    width: 100%;
  }


</style>
