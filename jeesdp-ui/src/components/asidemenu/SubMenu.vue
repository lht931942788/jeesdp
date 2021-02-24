<template>
  <template v-for="menu in menus">
    <el-submenu v-if="menu.children" :index="menu.path">
      <template #title>
        <i :class="menu.icon"></i>
        <span>{{ menu.name }}</span>
      </template>
      <sub-menu :menus="menu.children"/>
    </el-submenu>
    <el-menu-item v-else :index="menu.path">
      <i :class="menu.icon"></i>
      <span>{{ menu.name }}</span>
    </el-menu-item>
  </template>
</template>

<script>

export default {
  name: "SubMenu",
  props: {
    menus: {
      type: Array,
      required: true
    },
  }
}
</script>

<style scoped>

</style>
