<template>
    <div class="input-div">
      <tool-bar></tool-bar>
      <div class="input-msg-div">
        <textarea class="input-msg-textarea" placeholder="请输入内容" autofocus>

        </textarea>
      </div>
      <div class="opera-div">
        <el-button size="small" type="primary">确定</el-button>
      </div>
    </div>
</template>

<script>
  import ToolBar from '@/components/mainWindow/inputDialog/toolBar/ToolBar'
    export default {
        name: "InputWindow",
      components:{
        ToolBar
      },
      data(){
          return {
            textarea:"",
          }
      }
    }
</script>

<style scoped>
.input-div{
  margin-top: 5px;
  height: 155px;
}
  .input-msg-div{
    min-height: 70px;
  }
  .opera-div{
    height: 45px;
    text-align: right;
    vertical-align: middle;
  }
.input-msg-textarea{
  display: block;
  padding: 5px;
  line-height: 1.5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  background-color: #FFF;
  border:none;
  resize: none;
  outline: none;
  min-height: 40px;
  height: 65px;
  font-family: Micrsofot Yahei;
}
</style>
