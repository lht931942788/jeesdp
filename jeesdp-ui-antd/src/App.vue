<template>
  <demo-sub-menu :menus="menus"></demo-sub-menu>
</template>

<script>
import DemoSubMenu from "@/components/DemoSubMenu";

export default {
  name: 'App',
  components: {DemoSubMenu},
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
          }]
        }]
      }]
    }
  }
}
</script>
