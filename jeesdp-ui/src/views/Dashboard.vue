<template>
  <div>
    <grid :fields="fields" :prop="prop" :model="model"></grid>
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
          add: 'system:workflow:add',
          remove: 'system:workflow:remove',
          edit: 'system:workflow:edit',
        },
        url: {
          save: 'workflow/save',
          remove: 'workflow/removeByIds',
          row: 'workflow/getById',
          update: 'workflow/update',
          data: 'workflow/listByPage',
        },
      },
      model: {
        id: '',
        name: '',
        remark: '',
      },
      fields: [{
        selection: 'selection',
      }, {
        prop: 'name',
        type: 'input',
        width: 100,
        label: '名称',
      }, {
        prop: 'remark',
        type: 'input',
        label: '备注',
      }, {
        prop: 'status',
        type: 'input',
        label: '状态',
      }, {
        slot: 'op',
        edit: true,
        width: 200,
        label: '操作',
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