<template>
  <el-container>
    <el-header style="height: auto">
      <el-row>
        <el-form v-if="searchable" ref="search" v-model="params" inline label-position="left" label-suffix="：">
          <el-col :span="24">
            <template v-for="field in fields">
              <form-item
                  v-if="field.searchable && field.type !== 'operation' && field.type !== 'index' && field.type !== 'expand'"
                  v-model:value="params[field.prop]" :field="field"
                  :options="options[field.dict ? field.dict : field.prop]"
                  :validate="false"/>
            </template>
            <slot :params="params" name="search"></slot>
            <el-form-item>
              <el-button-group>
                <el-button :title="'查询'" type="primary" @click="onSearch">查询</el-button>
                <el-button :title="'清空'" type="primary" @click="onClear">清空</el-button>
              </el-button-group>
            </el-form-item>
          </el-col>
        </el-form>
        <el-col :span="24">
          <el-button-group>
            <el-button :title="$t('button.add')" icon="el-icon-plus" type="primary" @click="onAdd"/>
            <el-button :title="'修改'" icon="el-icon-edit" type="primary" @click="onEdit"/>
            <el-button :title="'删除'" icon="el-icon-delete" type="danger" @click="onRemove"/>
            <slot name="header-buttons"></slot>
          </el-button-group>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <data-grid ref="datagrid" :data="data" :fields="fields" :loading="tableLoading" :options="options"
                 :page="page" @row-dblclick="onRowDblclick">
        <template v-for="field in fields" v-slot:[field.slot]="scope">
          <slot :name="field.slot" :row="scope.row"/>
        </template>
      </data-grid>
      <slot/>
    </el-main>
  </el-container>

  <el-dialog v-model="dialogVisible" :title="title" :width="width" @closed="closed">
    <el-form ref="form" :model="model" label-position="right" label-suffix="：" label-width="120px">
      <template v-for="field in fields">
        <form-item v-if="field.type !== 'operation' && field.type !== 'index' && field.type !== 'expand'"
                   v-model:value="model[field.prop]" :field="field"
                   :options="options[field.dict ? field.dict : field.prop]"/>
      </template>
      <slot :model="model" name="form"/>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button v-if="!preview" :loading="loading" title="确定" type="primary" @click="onSave">确定</el-button>
        <el-button title="取消" @click="onClose">取消</el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script>
export default {
  name: "CommonGrid",
  props: {
    fields: {
      type: Array,
      required: true
    },
    options: {
      type: Object
    },
    width: {
      default: '65%'
    },
    pageable: {
      type: Boolean,
      default: true
    },
    searchable: {
      type: Boolean,
      default: true
    },
    rowKey: {
      type: String,
      default: 'id'
    }
  },
  data() {
    return {
      title: '',
      loading: false,
      tableLoading: false,
      dialogVisible: false,
      slots: [],
      data: [],
      model: {},
      params: {},
      preview: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        total: 100,
        sizeChange: (pageSize) => {
          this.page.pageSize = pageSize;
          this.load();
        },
        currentChange: (pageNum) => {
          this.page.pageNum = pageNum;
          this.load();
        },
      },
    }
  },
  methods: {
    onAdd() {
      this.open(this.$t('button.add'));
    },
    onEdit() {
      let selectedIds = this.$refs.datagrid.getSelectedIds();
      if (selectedIds.length === 1) {
        this.get(selectedIds[0]).then(res => {
          this.model = res;
          this.open("修改");
        })
      } else {
        this.$notify({message: '请选择一条记录！', type: 'error'});
      }
    },
    onSave() {
      this.$refs.form.validate((valid, messages) => {
        if (valid) {
          this.loading = true;
          this.save(this.model).then(res => {
            this.$notify({message: '操作成功！', type: 'success'});
            this.load();
            this.close();
            this.loading = false;
          }).catch(err => {
            this.loading = false;
          })
        } else {
          this.$notify({message: '请填写必填项！', type: 'error'});
        }
      });
    },
    onRemove(id) {
      let selectedIds = [];
      if (id) {
        selectedIds.push(id);
      } else {
        selectedIds = this.$refs.datagrid.getSelectedIds();
      }
      if (selectedIds.length !== 1) {
        this.remove().then(res => {
          this.$notify({message: '操作成功！', type: 'success'});
          this.load();
        })
      } else {
        this.$notify({message: '请选择一条或多条记录！', type: 'error'});
      }
    },
    onSearch() {
      this.load();
    },
    onClear() {
      this.params = {};
      this.load();
    },
    onRowDblclick(row, column) {
      this.model = row;
      this.preview = true;
      this.open('查看');
    },
    onClose() {
      this.close();
    },
    load(params = {}) {
      this.tableLoading = true;
      if (this.pageable) {
        params.pageNum = this.page.pageNum;
        params.pageSize = this.page.pageSize;
      }
      this.list(copy(params, this.params)).then(res => {
        this.data = res.list;
        this.page.total = res.total;
        this.tableLoading = false;
      }).catch(err => {
        this.tableLoading = false;
      })
    },
    get(id) {
      return this.$axios.post('', {id: id,});
    },
    save(data) {
      return this.$axios.post('', data);
    },
    list(params) {
      return this.$axios.post('', params);
    },
    remove(ids) {
      return this.$axios.post('', {ids: ids,});
    },
    open(title) {
      this.title = title;
      this.dialogVisible = true;
    },
    close() {
      this.dialogVisible = false;
    },
    closed() {
      if (this.preview) {
        this.preview = false;
      } else {
        this.$refs.form.resetFields();
      }
    },
    setData(data) {
      this.data = data;
    }
  },
  created() {

  }
}

function copy(source, target) {
  for (let key in source) {
    if (source.hasOwnProperty(key)) {
      target[key] = source[key];
    }
  }
  return target;
}
</script>

<style scoped>
.el-main {
  padding: 20px 0;
}

.el-header {
  padding: 0;
}
</style>
