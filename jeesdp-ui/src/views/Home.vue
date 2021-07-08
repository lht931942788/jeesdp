<template>
  <div class="el-select-tree">
    <el-popover v-model:visible="visible" :offset="offset" :width="width" trigger="click">
      <el-tree v-if="multiple" ref="tree" :data="data" :filter-node-method="filterNode"
               accordion @check-change="onCheckChange"/>
      <el-tree v-else ref="tree" :data="data" :filter-node-method="filterNode" accordion @node-click="onNodeClick"/>
      <template #reference>
        <el-input ref="selectTree" v-model:value="input" :placeholder="placeholder" readonly style="width: 500px">
          <template v-if="multiple" #prefix>
              <span>
                <el-tag closable type="info" @click="visible = !visible">demo</el-tag>
              </span>
          </template>
          <template v-else-if="filterable" #prefix>
            <input ref="search" v-model="search" :style="{width: width + 'px'}" class="search"/>
          </template>
          <template #suffix>
            <i v-if="clearable" :class="visible?'el-icon-arrow-up':'el-icon-arrow-down'"></i>
            <i v-else :class="visible?'el-icon-arrow-up':'el-icon-arrow-down'"></i>
            <i v-if="show" class="el-icon-close el-input__icon" @click="clear"> </i>
          </template>
        </el-input>
      </template>
    </el-popover>
  </div>
</template>
<script>

export default {
  name: 'Home',
  props: {
    multiple: {
      type: Boolean,
      default: true
    },
    placeholder: {
      type: String,
      default: '请选择'
    },
    filterable: {
      type: Boolean,
      default: false
    },
    clearable: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      show: false,
      search: '',
      visible: false,
      width: 0,
      offset: 10,
      data: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          children: [{
            label: '三级 1-1-1'
          }]
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
          children: [{
            label: '三级 2-1-1'
          }]
        }, {
          label: '二级 2-2',
          children: [{
            label: '三级 2-2-1'
          }]
        }]
      }, {
        label: '一级 3',
        children: [{
          label: '二级 3-1',
          children: [{
            label: '三级 3-1-1'
          }]
        }, {
          label: '二级 3-2',
          children: [{
            label: '三级 3-2-1'
          }]
        }]
      }],
      input: ''
    }
  },
  methods: {
    onCheckChange(data, demo, test) {
      console.log(data, demo, test)
    },
    onNodeClick(data, node, test) {
      if (node.isLeaf) {
        this.input = node.data.label;
        this.visible = false;
      }
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    clear() {
      console.log(111)
    }
  },
  created() {
    // this.func.ceshi = eval('(demo,e) => { console.log(e) }')
  },
  mounted() {
    this.width = this.$refs.selectTree.input.offsetWidth - 26
  },
  watch: {
    search(value) {
      this.$refs.tree.filter(value);
    }
  },
}
</script>

<style scoped>
.el-backtop {
  z-index: 99999;
}
</style>
