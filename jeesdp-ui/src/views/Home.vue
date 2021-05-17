<template>
  <el-input
      v-model="input"
      placeholder="请输入内容"
      readonly
  >
    <template #prefix>
      <span>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
        <el-tag type="info">demo</el-tag>
      </span>
      <input style="width: 40px"/>
    </template>
  </el-input>
</template>
<script>

import Model from "./system/Model.vue";

export default {
  name: 'Home',
  components: {Model},
  data() {
    return {
      input: ''
    }
  },
  created() {
    // this.func.ceshi = eval('(demo,e) => { console.log(e) }')
  }
}
</script>

<style scoped>
.el-backtop {
  z-index: 99999;
}
</style>
