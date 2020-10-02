<template>
  <div>
    <notification-item></notification-item>
  </div>
</template>

<script>

  import NotificationItem from '@/components/message/mainWindow/notification/NotificationItem'

  export default {
    name: "NotificationWindow",
    components: {
      NotificationItem
    }
  }
</script>

<style scoped>

</style>
