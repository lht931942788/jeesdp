<template>
  <el-container>
    <el-aside v-if="treeable">
      <el-tree :data="treeData" :props="{children: 'children', label: 'label'}"/>
    </el-aside>
    <el-container>
      <el-header style="height: auto">
        <el-row>
          <el-col v-if="searchable" :span="24">
            <el-form ref="search" :model="params" inline label-position="right" label-width="120px">
              <template v-for="field in fields">
                <field-render v-if="field.searchable" v-model:value="params[field.prop]"
                              :field="field" :options="dictionaries[field.dictionary ? field.dictionary : field.prop]"/>
              </template>
              <slot name="search"></slot>
              <el-form-item>
                <el-button-group>
                  <el-button :title="'查询'" type="primary" @click="load">查 询</el-button>
                  <el-button :title="'清空'" type="primary" @click="clear">清 空</el-button>
                </el-button-group>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="24">
            <el-button-group>
              <el-button :title="'新增'" icon="el-icon-plus" type="primary" @click="add"/>
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
  </el-container>

  <el-dialog v-model="dialogVisible" :title="title" :width="width" @closed="closed">
    <el-form ref="form" :model="model" label-position="right" label-width="120px">
      <template v-for="field in fields">
        <field-render v-model:value="model[field.prop]" :field="field"
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
      default: '60%'
    },
    pageable: {
      type: Boolean,
      default: true
    },
    treeable: {
      type: Boolean,
      default: false
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
      slots: ["op"],
      op: 'op',
      data: [],
      treeData: [],
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
      title: '',
      loading: false,
      tableLoading: false,
      dialogVisible: false,
    }
  },
  methods: {
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
      this.open("新增");
    },
    edit(id) {
      this.$axios.post('', {
        id: id
      }).then(res => {
        this.model = res;
        this.open("修改");
      }).catch(err => {
      })
    },
    save() {
      this.loading = true;
      this.$axios.post('', this.model).then(res => {
        this.close();
      }).catch(err => {
        this.loading = false;
      })
    },
    remove() {
      console.log(this.$refs.datagrid.getSelectedIds())
      this.$refs.datagrid.getSelectedIds();
    },
    open(title) {
      this.title = title;
      this.dialogVisible = true;
    },
    close() {
      this.dialogVisible = false;
    },
    closed() {
      this.loading = false;
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
    console.log(this.user.username)
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
.jeesdp .tree-body {
  box-sizing: border-box;
  border: 1px solid #EBEEF5;
  overflow: auto;
}
</style>
