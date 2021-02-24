<template>
  <el-container>
    <el-header style="height: auto">
      <el-row>
        <el-form v-if="searchable" ref="search" v-model="params" inline label-position="right">
          <el-col :span="24">
            <template v-for="field in fields">
              <form-item v-if="field.searchable" v-model:value="params[field.prop]"
                         :field="field" :options="dictionaries[field.dictionary ? field.dictionary : field.prop]"/>
            </template>
            <slot name="search"></slot>
            <el-form-item>
              <el-button-group>
                <el-button :title="'查 询'" type="primary" @click="search">查 询</el-button>
                <el-button :title="'清 空'" type="primary" @click="clear">清 空</el-button>
              </el-button-group>
            </el-form-item>
          </el-col>
        </el-form>
        <el-col :span="24">
          <el-button-group>
            <el-button :title="$t('button.add')" icon="el-icon-plus" type="primary" @click="add"/>
            <el-button :title="'修改'" icon="el-icon-edit" type="primary" @click="edit"/>
            <el-button :title="'删除'" icon="el-icon-delete" type="danger" @click="remove"/>
            <slot name="header-buttons"></slot>
          </el-button-group>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <data-grid ref="datagrid" :data="data" :dictionaries="dictionaries" :fields="fields" :loading="tableLoading"
                 :page="page" :pageable="pageable">
        <template v-for="slot in slots" v-slot:[slot]="scope">
          <slot :name="slot" :row="scope.row"/>
        </template>
      </data-grid>
      <slot/>
    </el-main>
  </el-container>

  <el-dialog v-model="dialogVisible" :title="title" :width="width" @closed="closed">
    <el-form ref="form" :model="model" label-position="right" label-width="120px">
      <template v-for="field in fields">
        <form-item v-model:value="model[field.prop]" :field="field"
                   :options="dictionaries[field.dictionary ? field.dictionary : field.prop]"/>
      </template>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button :loading="loading" title="确定" type="primary" @click="save">确定</el-button>
        <el-button title="取消" @click="close">取消</el-button>
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
    dictionaries: {
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
      slots: ["op"],
      op: 'op',
      data: [],
      model: {},
      params: {},
      page: {
        pageNum: 1,
        pageSize: 10,
        total: 0,
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
    search() {
      this.load();
    },
    load(params = {}) {
      this.tableLoading = true;
      if (this.pageable) {
        params.pageNum = this.page.pageNum;
        params.pageSize = this.page.pageSize;
      }
      this.$axios.post('', copy(params, this.params)).then(res => {
        this.data = res.list;
        this.page.total = res.total;
        this.tableLoading = false;
      }).catch(err => {
        this.tableLoading = false;
      })
    },
    add() {
      this.open(this.$t('button.add'));
    },
    edit() {
      let selectedIds = this.$refs.datagrid.getSelectedIds();
      if (selectedIds.length === 1) {
        this.$axios.post('', {
          id: selectedIds[0],
        }).then(res => {
          this.model = res;
          this.open("修改");
        })
      } else {
        this.$notify({message: '请选择一条记录！', type: 'error'});
      }
    },
    save() {
      this.loading = true;
      this.$refs.form.validate((valid, obj) => {
        console.log(obj)
        if (valid) {
          this.$axios.post('', this.model).then(res => {
            this.$notify({message: '操作成功！', type: 'success'});
            this.close();
            this.loading = false;
          }).catch(err => {
            this.loading = false;
          })
        }
      });
    },
    remove(id) {
      let selectedIds = [];
      if (id) {
        selectedIds.push(id);
      } else {
        selectedIds = this.$refs.datagrid.getSelectedIds();
      }

      if (selectedIds.length !== 1) {
        this.$axios.post('', {
          ids: selectedIds,
        }).then(res => {
          this.$notify({message: '操作成功！', type: 'success'});
        })
      } else {
        this.$notify({message: '请选择一条或多条记录！', type: 'error'});
      }
    },
    open(title) {
      this.title = title;
      this.dialogVisible = true;
    },
    close() {
      this.dialogVisible = false;
    },
    closed() {
      this.model = {};
    },
    clear() {
      this.params = {};
      this.load();
    },
    setData(data) {
      this.data = data;
    }
  },
  created() {
    this.fields.forEach(item => {
      if (item.slot) {
        this.slots.push(item.slot);
      }
    })
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
