<template>
  <el-button @click="click">ss</el-button>
  <data-grid ref="datagrid" :data="demo" :dictionaries="dictionaries" :fields="fields" :height="400" :page="page"/>
  <el-form :model="model" label-position="right" label-width="120px">
    <field-render v-for="field in fields" v-model:value="model[field.prop]" :field="field"
                  :options="dictionaries[field.dictionary ? field.dictionary : field.prop]"/>
  </el-form>
</template>

<script>
import DataGrid from "./components/DataGrid.vue";
import FieldRender from "./components/FieldRender.vue";
import axios from "./utils/axios";

export default {
  name: 'App',
  components: {FieldRender, DataGrid},
  data() {
    return {
      dictionaries: {
        demo: {
          demo: "aa",
          test: "bb"
        }
      },
      model: {},
      visible: true,
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
      fields: [{
        fieldType: 'radio',
        type: 'password',
        prop: 'demo',
        label: '测试',
        width: 1000,
        formatter: function () {
          console.log(this.h('h1', {}, 'Some title'))
          return "dddddd";
        }
      }, {
        fieldType: 'datePicker',
        type: 'datetime',
        prop: 'time',
        label: '时间',
        format: 'YYYY-MM-DD HH:mm:ss',
        width: 1000,
      }],
      demo: [
        {
          id: 'id',
          demo: "demo",
          time: '2020-11-11 00:00:00'
        }, {
          id: 'id',
          demo: "test",
          time: '2020-11-11 00:00:00'
        }
      ],
      time: null,
      date: null,
    }
  },
  methods: {
    load(params = {}) {
      if (this.pageable) {
        params.pageNum = this.page.pageNum;
        params.pageSize = this.page.pageSize;
      }
      axios.post(this.url, copy(params, this.params)).then(res => {
        this.data = res.data;
        this.page.total = res.total;
        this.loading = false;
      }).catch(err => {
        this.loading = false;
      })
    },
    click() {
      console.log(this.$refs.datagrid.getSelectedIds())
      this.$axios.post('/demo', this.model).then((res) => {
        console.log(res)
      });
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
