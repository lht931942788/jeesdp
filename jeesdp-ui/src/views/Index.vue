<template>
  <el-container>
    <el-header>Header</el-header>
    <el-container>
      <el-aside style="overflow: hidden; background-color: #545c64" width="256px">
        <aside-menu :menus="menus"/>
      </el-aside>
      <el-container>
        <el-main class="jeesdp-content"
                 style="border: 1px solid #EBEEF5; box-sizing:border-box; padding: 0; height: calc(100% - 60px)">
          <router-view/>
          <el-backtop :visibility-height="0" target=".jeesdp-content"/>
        </el-main>
        <el-footer style="height: 38px; line-height: 38px; text-align: center">Footer</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>

export default {
  name: "Index",
  data() {
    return {
      menus: [{
        name: '首页',
        path: '/home',
      }, {
        name: '系统管理',
        path: '/system',
        children: [{
          name: '部门管理',
          path: '/system/dept',
        }, {
          name: '菜单管理',
          path: '/system/menu',
        }, {
          name: '角色管理',
          path: '/system/role',
        }]
      }]
    }
  },
  methods: {}
}
</script>

<style scoped>
.el-backtop {
  z-index: 21;
}

.el-container {
  height: 100%;
  width: 100%;
  margin: 0;
  overflow: hidden;
}
</style>
