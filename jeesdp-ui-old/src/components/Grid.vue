<template>
  <div>
    <div ref="header">
      <dynamic-form ref="search" v-if="search.fields.length > 0" :form="search" :model="search.params">
        <el-form-item>
          <el-button-group>
            <el-button :title="$t('button.search')" @click="load()" type="primary">
              {{ $t('button.search') }}
            </el-button>
            <el-button :title="$t('button.reset')" @click="clear" type="primary">
              {{ $t('button.reset') }}
            </el-button>
          </el-button-group>
        </el-form-item>
      </dynamic-form>

      <div style="padding-bottom: 10px">
        <el-button-group slot="toolBar">
          <el-button :title="$t('button.add')" @click="add()" icon="el-icon-plus" type="primary"
                     v-if="prop.permission.add && checkPermission(prop.permission.add)"></el-button>

          <el-button :title="$t('button.remove')" @click="remove()" icon="el-icon-delete" type="danger"
                     v-if="prop.permission.remove && checkPermission(prop.permission.remove)"></el-button>
          <slot name="toolBar"></slot>
        </el-button-group>
      </div>
    </div>

    <data-grid ref="grid" :table="table" :data="data" :dict="dict" :height="height" :pageable="pageable" :page="page">
      <template :slot="field.slot" slot-scope="scope" v-for="field in table.fields">
        <el-button-group v-if="field.slot && field.slot === 'op'">
          <el-button :title="$t('button.edit')" @click="edit(scope.row.id)" icon="el-icon-edit" type="primary"
                     v-if="prop.permission.edit && checkPermission(prop.permission.edit) && field.edit"></el-button>
          <slot :field="scope.field" :name="field.slot" :row="scope.row"></slot>
        </el-button-group>
        <slot :field="scope.field" :name="field.slot" :row="scope.row" v-else-if="field.slot"></slot>
      </template>
    </data-grid>

    <el-dialog v-if="form.fields.length > 0" :close-on-click-modal="false" :title="title" :visible.sync="visible"
               @closed="closed" width="30%">
      <dynamic-form ref="dynamicForm" :form="form" :model="model"></dynamic-form>
      <span class="dialog-footer" slot="footer">
          <el-button @click="visible = false">{{ $t('button.cancel') }}</el-button>
          <el-button :loading="loading" @click="save" type="primary">{{ $t('button.ok') }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'Grid',
  props: {
    prop: {
      type: Object,
    },
    fields: {
      type: Array,
      required: true,
    },
    pageable: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      data: [],
      remote: {},
      dict: {},
      page: {
        pageNum: 1,
        pageSize: 10,
        sizeChange: size => {
          this.page.pageSize = size;
          this.load();
        },
        currentChange: current => {
          this.page.pageNum = current;
          this.load();
        }
      },
      loading: false,
      visible: false,
      title: '',
      order: null,
      height: 500,
      model: {},
      form: {
        fields: [],
        rules: {}
      },
      search: {
        inline: 'inline',
        params: {},
        fields: []
      },
      table: {fields: []},
    }
  },
  /*watch: {
      search: {
          handler(newName, oldName) {
              console.log('obj.a changed');
          },
          immediate: true,
          deep: true
      }
  },*/
  methods: {
    load() {
      let params = {};
      if (this.search) {
        params = this.search.params
      }
      if (this.pageable) {
        params['pageNum'] = this.page.pageNum;
        params['pageSize'] = this.page.pageSize;
      }
      if (this.order) {
        params['order'] = this.order;
      }
      this.$ajax.post(this.prop.url.data, params).then((data) => {
        this.data = data.list;
        if (this.pageable) {
          this.page.total = data.total;
        }
      })
    },
    add() {
      this.open(this.$t('button.add'));
    },
    save() {
      this.$refs.dynamicForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          let url = this.prop.url.update;
          console.log(this.model[this.table.rowKey])
          if (!this.model[this.table.rowKey] || this.model[this.table.rowKey] === '' || this.model[this.table.rowKey] === null) {
            url = this.prop.url.save;
            alert(1)
          }
          this.$ajax.post(url, this.model).then((data) => {
            this.$message({
              type: data.type,
              message: this.$t(data.code)
            });
            if (data.status === 1) {
              this.load();
              this.close();
            } else {
              this.closed();
            }
          })
        }
      });
    },
    edit(id) {
      this.$ajax.post(this.prop.url.row, {id: id}).then((data) => {
        this.model = data;
        this.open(this.$t('button.edit'));
      })
    },
    remove() {
      let selected = this.$refs.grid.getSelected();
      if (selected.length > 0) {
        this.$confirm('确定要删除这些数据？', '提示', {
          type: 'warning',
        }).then(() => {
          this.$ajax.post(this.prop.url.remove, {ids: selected}).then((data) => {
            this.load();
            this.$message({
              type: data.type,
              message: this.$t(data.code)
            });
          })
        }).catch(() => {
        });
      } else {
        this.$message.error('请选择一条或多条数据！');
      }
    },
    clear() {
      this.reset('search');
      this.load();
    },
    open(title) {
      this.title = title;
      this.visible = true;
    },
    close() {
      this.visible = false;
    },
    closed() {
      this.reset('dynamicForm');
      this.loading = false;
    },
    sortChange(column) {
      if (column.order) {
        this.order = column.prop + ' ' + column.order.replace('ending', '');
      } else {
        this.order = null;
      }
      this.load();
    },
    reset(name) {
      this.$refs[name].reset();
    },
    resize() {
      let height = document.body.offsetHeight - 116 - this.$refs.header.offsetHeight;
      if (this.pageable) {
        height = height - 49
      }
      this.height = height;
    }
  },
  created() {
    for (let field of this.fields) {
      if (field.search) {
        this.search.fields.push(field);
      }
      if (!field.formHide && field.slot !== 'op' && field.prop) {
        if (field.type === 'select' && field.url) {
          this.$ajax.post(field.url).then((result) => {
            field.options = result;
            this.remote[field.prop] = result;
            let dict = new Map();
            for (let item of result) {
              dict.set(item[field.value ? field.value : 'id'], item[field.label ? field.label : 'name'])
            }
            this.dict[field.prop] = dict;
          })
        }
        this.form.fields.push(field);
        if (field.rules) {
          this.form.rules[field.prop] = field.rules;
        }
        if (field.defaultValue) {
          this.$set(this.model, field.prop, field.defaultValue);
          this.model[field.prop] = field.defaultValue;
        } else {
          this.$set(this.model, field.prop, null);
        }
      }
      if (!field.tableHide) {
        this.table.fields.push(field);
      }
    }
    if (this.prop.rowKey) {
      this.table.rowKey = this.prop.rowKey;
      this.$set(this.model, this.prop.rowKey, null);
    } else {
      this.table.rowKey = 'id';
      this.$set(this.model, 'id', null);
    }
    if (this.prop.labelWidth === undefined) {
      this.form.labelWidth = '80px';
    }
    this.load();
  },
  mounted() {
    this.$nextTick(() => {
      this.resize();
      window.onresize = () => {
        this.resize();
      }
    })
  }
}
</script>