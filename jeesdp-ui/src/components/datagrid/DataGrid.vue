<template>
  <el-table v-loading="loading" :data="data" :height="height" :row-key="rowKey"
            border highlight-current-row
            element-loading-spinner="el-icon-loading" element-loading-text="拼命加载中"
            tooltip-effect="dark" @selection-change="onSelectionChange" @row-dblclick="onRowDblclick">

    <el-table-column v-if="selection" align="center" fixed header-align="center" type="selection"/>

    <el-table-column v-if="index" :index="indexMake" align="center" fixed header-align="center" label="序号"
                     type="index"/>

    <template v-for="field in fields">

      <el-table-column v-if="field.type !== 'hidden' && field.type === 'expand'" align="center" header-align="center"
                       type="expand">

        <template #default="scope">
          <slot :name="field.slot" :row="scope.row"/>
        </template>

      </el-table-column>

      <el-table-column v-else-if="field.type !== 'hidden'" :align="field.align ? field.align : 'center'"
                       :fixed="field.fixed" :header-align="field.headerAlign ? field.headerAlign : 'center'"
                       :label="field.label" :prop="field.prop"
                       :width="field.width ? field.width : ''"
                       show-overflow-tooltip>

        <template v-if="'select,radio,checkbox'.indexOf(field.type) > -1 && field.dict" #default="scope">
          {{ translateDict(options[field.dict ? field.dict : field.prop], scope.row[field.prop]) }}
        </template>

        <template v-if="'timePicker,datePicker'.indexOf(field.type) > -1" #default="scope">
          {{ $dayjs(scope.row[field.prop]).format(field.format) }}
        </template>

        <template v-if="field.slot" #default="scope">
          <slot :field="field" :name="field.slot" :row="scope.row"/>
        </template>

      </el-table-column>
    </template>
  </el-table>

  <el-pagination v-if="page" :current-page="page.pageNum" :page-size="page.pageSize"
                 :page-sizes="[10, 20, 30, 40, 50, 100]" :total="page.total"
                 layout="jumper, prev, pager, next, sizes, total" @size-change="page.sizeChange"
                 @current-change="page.currentChange"/>
</template>

<script>

export default {
  name: "DataGrid",
  props: {
    fields: {
      type: Array,
      required: true,
    },
    data: {
      type: Array,
      required: true,
    },
    pageable: {
      type: Boolean,
      default: true
    },
    page: {
      type: Object
    },
    height: {
      type: Number
    },
    options: {
      type: Object
    },
    rowKey: {
      type: String,
      default: 'id'
    },
    loading: {
      type: Boolean,
      default: false
    },
    selection: {
      type: Boolean,
      default: true
    },
    index: {
      type: Boolean,
      default: true
    },
  },
  emits: ['rowDblclick'],
  data() {
    return {
      selectedRows: [],
    }
  },
  methods: {
    getSelected() {
      return this.selected;
    },
    getSelectedIds() {
      return this.selectedRows.map(row => {
        return row[this.rowKey];
      });
    },
    onSelectionChange(selection) {
      this.selectedRows = selection;
    },
    onRowDblclick(row, column, event) {
      this.$emit('rowDblclick', row, column, event)
    },
    indexMake(index) {
      if (this.pageable) {
        return (this.page.pageNum - 1) * this.page.pageSize + index + 1;
      }
      return index + 1;
    },
    translateDict(dict, value) {
      let result;
      dict.forEach(item => {
        if (item.value === value) {
          result = item.label;
        }
      })
      let children = dict.children;
      if (children && !result) {
        return this.translateDict(children, value);
      }
      return result;
    }
  },
}
</script>

<style scoped>

.el-pagination {
  box-sizing: border-box;
  border: 1px solid #EBEEF5;
  border-top: none;
  padding-top: 10px;
  padding-bottom: 10px;
  text-align: right;
}

</style>
