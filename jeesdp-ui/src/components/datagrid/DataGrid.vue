<template>
  <el-table v-loading="loading" :data="data" :height="height" :row-key="rowKey" border
            element-loading-spinner="el-icon-loading" element-loading-text="拼命加载中"
            f tooltip-effect="dark" @selection-change="selectionChange" @row-dblclick="rowCblclick">

    <el-table-column v-if="selection" align="center" header-align="center" type="selection"/>

    <template v-for="field in fields">
      <template v-if="field.fieldType !== 'hidden'">
        <el-table-column :align="field.align ? field.align : 'center'" :fixed="field.fixed"
                         :header-align="field.headerAlign ? field.headerAlign : 'center'"
                         :label="field.label" :prop="field.prop" :show-overflow-tooltip="true"
                         :width="field.width ? field.width : 150">

          <template v-if="'timePicker,datePicker'.indexOf(field.fieldType) > -1" #default="scope">
            {{ $dayjs(scope.row[field.prop]).format(field.format) }}
          </template>

          <template v-else-if="'select,radio,checkbox'.indexOf(field.fieldType) > -1" #default="scope">
            {{ dictionaries[field.dictionary ? field.dictionary : field.prop][scope.row[field.prop]] }}
          </template>

          <template v-else-if="field.buttons" #default="scope">
            <el-button-group>
              <el-button v-for="button in field.buttons" :icon="button.icon" :type="button.type"
                         @click="eval(button.click)">
                {{ button.name }}
              </el-button>
            </el-button-group>
          </template>

          <template v-else-if="field.slot" #default="scope">
            <slot :name="field.slot" :row="scope.row"/>
          </template>

        </el-table-column>
      </template>
    </template>
  </el-table>

  <el-pagination v-if="pageable" :current-page="page.pageNum" :page-size="page.pageSize"
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
    dictionaries: {
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
  },
  data() {
    return {
      selection: [],
    }
  },
  methods: {
    getSelected() {
      return this.selected;
    },
    getSelectedIds() {
      return this.selection.map(row => {
        return row[this.rowKey];
      });
    },
    selectionChange(selection) {
      this.selection = selection;
    },
    rowCblclick(row, column, event) {

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
