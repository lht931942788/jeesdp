<template>
  <div>
    <el-table :data="data" :height="height" :row-key="table.rowKey" @selection-change="selectionChange" border
              tooltip-effect="dark">
      <template v-for="field in table.fields">
        <el-table-column v-if="field.selection" :type="field.selection" align="center" fixed="left" header-align="center"
                         width="40">
        </el-table-column>
        <el-table-column :align="field.align?'center':field.align" :fixed="field.fixed"
                         :header-align="field.headerAlign?'center':field.headerAlign"
                         :label="field.label" :prop="field.prop" :show-overflow-tooltip="true"
                         :width="field.width" v-else-if="field.slot === undefined">
        </el-table-column>
        <el-table-column :align="field.align?'center':field.align" :fixed="field.fixed"
                         :header-align="field.headerAlign?'center':field.headerAlign"
                         :label="field.label" :prop="field.prop" :show-overflow-tooltip="true"
                         :width="field.width" v-else>
          <slot :field="field" :name="field.slot" :row="scope.row" slot-scope="scope"></slot>
        </el-table-column>
      </template>
    </el-table>
    <el-pagination v-if="pageable" @size-change="page.sizeChange" @current-change="page.currentChange"
                   :page-sizes="[10, 20, 30, 40, 50, 100]" layout="jumper,prev,pager,next,sizes,total"
                   :current-page="page.pageNum" :page-size="page.pageSize" :total="page.total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "DataGrid",
  props: {
    table: {
      type: Object,
      required: true,
    },
    pageable: {
      type: Boolean,
      default: true,
    },
    page: {
      type: Object,
    },
    data: {
      type: Array
    },
    height: {
      type: Number
    }
  },
  data() {
    return {
      selected: [],
    }
  },
  methods: {
    selectionChange(selection) {
      let ids = [];
      for (let selected of selection) {
        ids.push(selected[this.table.rowKey]);
      }
      this.selected = ids;
    },
    currentChange(current) {
      this.$emit('currentChange', current);
    },
    sizeChange(size) {
      this.$emit('sizeChange', size);
    },
    getSelected() {
      return this.selected;
    },
  },
  created() {
    console.log(this.table)
  }
}
</script>

<style scoped>
.el-pagination {
  border: 1px solid #EBEEF5;
  border-top: none;
  padding-bottom: 10px;
}
</style>