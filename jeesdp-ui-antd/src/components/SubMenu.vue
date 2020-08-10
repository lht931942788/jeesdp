<template functional>
  <a-sub-menu v-if="props.menu.children && props.menu.children.length > 0"
              :key="props.menu.url && props.menu.url !== ''? props.menu.url : props.menu.id">
    <template slot="title">
      <a-icon v-if="props.menu.icon" :type="props.menu.icon"></a-icon>
      <span>{{ props.menu.name }}</span>
    </template>
    <template v-for="item in props.menu.children">
      <sub-menu :menu="item"></sub-menu>
    </template>
  </a-sub-menu>
  <a-menu-item v-else :key="props.menu.url && props.menu.url !==''? props.menu.url : props.menu.id">
    <a-icon v-if="props.menu.icon" :type="props.menu.icon"></a-icon>
    {{ props.menu.name }}
  </a-menu-item>
</template>
<script>
import SubMenu from './SubMenu'

export default {
  name: 'SubMenu',
  props: {
    menu: {
      type: Object,
      required: true
    }
  },
  components: {
    SubMenu,
  },
};
</script>