<template>
  <a-menu style="width: 256px" :default-selected-keys="['5']" :default-open-keys="['1','2']" mode="inline" theme="dark"
          @click="menuClick">
    <sub-menu v-for="menu in menus" :menu="menu"></sub-menu>
  </a-menu>
</template>

<script>
import SubMenu from "@/components/SubMenu";

export default {
  name: 'App',
  components: {SubMenu},
  data() {
    return {
      menus: [{
        id: '1',
        name: 'demo',
        icon: 'desktop',
        children: [{
          id: '2',
          name: 'demo2',
          children: [{
            id: '3',
            name: 'demo3',
          }, {
            id: '4',
            name: 'demo4',
          }, {
            id: '5',
            name: 'demo5',
            icon: 'desktop',
          }]
        }]
      }, {
        id: '6',
        name: 'demo',
        icon: 'desktop',
      }, {
        id: '7',
        name: 'demo',
        icon: 'desktop',
      }, {
        id: '8',
        name: 'demo',
        icon: 'desktop',
        children: [{
          id: '9',
          name: 'demo3',
        }, {
          id: '10',
          name: 'demo4',
        }, {
          id: '11',
          name: 'demo5',
          icon: 'desktop',
        }]
      }]
    }
  }, methods: {
    menuClick(node) {
      console.log(node)
    }
  }
}
</script>
