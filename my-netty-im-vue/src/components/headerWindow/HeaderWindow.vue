<template>
  <div class="header-div">
    <div class="avatar-div">
      <el-avatar :size="45" :src="avatarUrl" class="avator"></el-avatar>
    </div>
    <div class="tab-div">
      <i :class="'MessageMain'==currentMain?'light':''" @click="switchMain('MessageMain')"
         class="iconfont iconmessage tab-icon"></i>
      <i :class="'ContactsMain'==currentMain?'light':''" @click="switchMain('ContactsMain')"
         class="iconfont iconlianxiren2 tab-icon"></i>
      <i :class="'CloudMain'==currentMain?'light':''" @click="switchMain('CloudMain')"
         class="iconfont iconicon_- tab-icon"></i>
    </div>
    <div class="connect-div">
      <el-tooltip :content="connectStatusTitle" class="item" effect="dark" placement="top">
        <div :class="connectStatus" id="status-div"></div>
      </el-tooltip>
    </div>
  </div>
</template>

<script>

  import store from "@/store";
  import Constant from '@/constants';

  export default {
    name: "HeaderWindow",
    data() {
      return {
        currentMain: 'MessageMain',
        connectStatus: 'disconnect-color',
        connectStatusTitle: null
      }
    },
    computed: {
      avatarUrl() {
        if (this.$store.state.personalInformation.avatarSrc == '') {
          return;
        }
        return this.$store.state.personalInformation.avatarSrc;
      },
    },
    methods: {
      switchMain(mainName) {
        this.currentMain = mainName;
        this.$emit("switchMain", mainName);
      },
      setConnectStatus() {
        let now = new Date().getTime();
        let diff = now - store.state.activeTime;
        //console.log("now", now, "activeTime", store.state.activeTime, "diff", diff);
        if (diff > 3 * Constant.HEART_BEAT_TIMEOUT) {
          this.connectStatus = "disconnect-color";
          this.connectStatusTitle = "与服务端断开连接";
        } else if (diff < 3 * Constant.HEART_BEAT_TIMEOUT && diff >= Constant.HEART_BEAT_TIMEOUT) {
          this.connectStatus = "warn-connect-color";
          this.connectStatusTitle = "与服务端失去连接";
        } else {
          this.connectStatus = "connect-color";
          this.connectStatusTitle = "与服务端正常连接";
        }
      }
    },
    mounted() {
      this.setConnectStatus();
      setInterval(this.setConnectStatus, 2000);

    }
  }
</script>

<style scoped>
  .header-div {
    width: 100%;
    height: 100%;
    vertical-align: top;
    position: relative;
  }

  .avatar-div {
    display: inline-block;
    float: left;
  }

  .tab-div {
    display: inline-block;
    height: 100%;
    margin-top: -5px;
  }

  .tab-icon {
    font-size: 28px;
    margin: 10px;
    height: 100%;
    color: gainsboro;

  }

  .light {
    color: white;
  }

  .connect-div {
    display: inline-block;
    float: right;
    position: absolute;
    left: 98%;
    top: 40%;

  }

  #status-div {
    height: 10px;
    width: 10px;
    border-radius: 50%;
  }

  .connect-color {
    background-color: #00dd00;
  }

  .warn-connect-color {
    background-color: #ffe506; /*#ffec0e;*/
    animation: fade 4000ms infinite;
  }

  .disconnect-color {
    background-color: red;
    animation: fade 2000ms infinite;
  }

  @keyframes fade {
    from {
      opacity: 1.0;
    }
    50% {
      opacity: 0.4;
    }
    to {
      opacity: 1.0;
    }
  }

  @-webkit-keyframes fade {
    from {
      opacity: 1.0;
    }
    50% {
      opacity: 0.4;
    }
    to {
      opacity: 1.0;
    }
  }
</style>
