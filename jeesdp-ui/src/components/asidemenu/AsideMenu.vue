<template>
  <el-menu active-text-color="#ffd04b" background-color="#545c64" router text-color="#fff" unique-opened
           @select="onSelect">
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
    value: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      model: {},
    }
  },
  methods: {
    onSelect(index, path) {
      console.log(index, path)
      for (let menu in this.menus) {

      }
    }
  },
  watch: {
    model(newValue, oldValue) {
      this.$emit('update:value', newValue);
    },
    value(newValue, oldValue) {
      this.model = newValue;
    }
  }
}
</script>

<style scoped>

</style>
