<template>
  <div>
    <grid :fields="fields" :prop="prop"></grid>
  </div>
</template>
<style>
.el-tag {
  margin-top: 4px;
}
</style>
<script>
import Grid from "../components/Grid";

export default {
  components: {
    Grid,
  },
  data() {
    return {
      prop: {
        permission: {
          add: 'system:formlist:add',
          remove: 'system:formlist:remove',
          edit: 'system:formlist:edit',
        },
        url: {
          save: 'form/save',
          remove: 'form/removeByIds',
          row: 'form/getById',
          update: 'form/update',
          data: 'form/listByPage',
        },
      },
      fields: [{
        selection: 'selection',
      }, {
        name: '名称',
        prop: 'name',
        type: 'input',
        width: 100,
        search: true,
        defaultValue: 2,
      }, {
        name: '备注',
        prop: 'remark',
        type: 'select',
        dictable: true,
        url: 'form/list'
      }, {
        name: '状态',
        prop: 'status',
        type: 'input',
      }, {
        name: '操作',
        width: 200,
        slot: 'op',
        edit: true,
      }],
    }
  },
  methods: {
    demo() {
      console.log(this.value)
    },
  }
};


</script>