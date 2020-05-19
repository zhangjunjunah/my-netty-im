<template>
  <span>{{node.data.groupName}}</span>
</template>

<script>
  export default {
    name: "GroupItem",
    props: {
      node: {
        type: Object,
        required: true
      }
    },
  }
</script>

<style scoped>

</style>
