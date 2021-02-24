<template>
  <el-menu active-text-color="#ffd04b" background-color="#545c64" router text-color="#fff" unique-opened>
    <sub-menu :menus="menus"/>
  </el-menu>
</template>

<script>
import SubMenu from "./SubMenu.vue";

export default {
  name: "AsideMenu",
  components: {SubMenu},
  props: {
    menus: {
      type: Array,
      required: true
    },
  },
}
</script>

<style scoped>

</style>
