<template>
  <div class="window-div">
    <el-container>
      <el-header>
        <header-window @switchMain="switchMain"></header-window>
      </el-header>
      <el-main >
        <component v-bind:is="currentWindow"></component>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import HeaderWindow from '@/components/headerWindow/HeaderWindow'
  import MessageMain from '@/components/message/MessageMain'
  import CloudMain from '@/components/cloud/CloudMain'
  import ContactsMain from '@/components/contacts/ContactsMain'

  export default {
    name: "Window",
    components: {
      HeaderWindow,
      MessageMain,
      CloudMain,
      ContactsMain
    },
    data() {
      return {
        currentWindow: "MessageMain"
      }
    },
    computed: {
    },
    created() {
      this.$store.commit('initWebSocket');
      setTimeout(() => {
        this.$store.commit('connect')
      }, 1000);

    },
    methods: {
      switchMain(mainName) {
        this.currentWindow = mainName;
      }
    }

  }
</script>

<style scoped>
.window-div{
  margin:0 auto;
  border:1px solid rgba(255, 227, 236, 0);
  width: 75%;
  height: 80%;
  border-radius: 5px;

}
.el-container {
  height:100%;
  border-radius: 0px 0px 5px 5px;
  overflow: hidden;
}

.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: 100%;
  border-radius: 0px 0px 5px 5px;
  padding: 0px;
  overflow: hidden;
}

.el-header, .el-footer {
  background-color: #148fed;
  color: #fff1ff;
  text-align: center;
  line-height: 60px;
  width: 100%;
  border-radius: 5px 5px 0px 0px;
  padding-bottom: 2px;
  padding: 7.5px 13px;
}

</style>
