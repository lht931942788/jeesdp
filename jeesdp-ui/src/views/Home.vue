<template>
  <common-grid ref="grid" :dictionaries="dictionaries" :fields="fields"/>
  <monaco-editor :language="'json'"/>
</template>
<script>

export default {
  name: 'Home',
  data() {
    return {
      test: 'test',
      active: 1,
      dictionaries: {
        demo: {
          demo: "aa",
          test: "bb",
          2: "bbssssssssssssssssssssssssssssss",
          3: "bbssssssssssssssssssssssssssssss",
          5: "bbssssssssssssssssssssssssssssss",
          6: "bbssssssssssssssssssssssssssssss",
          9: "bbssssssssssssssssssssssssssssss",
          8: "bbssssssssssssssssssssssssssssss",
          10: "bbssssssssssssssssssssssssssssss"
        }
      },
      model: {},
      visible: true,
      fields: [{
        fieldType: 'radio',
        type: 'password',
        prop: 'demo',
        label: '测试',
        slot: 'demo'
      }, {
        fieldType: 'datePicker',
        type: 'datetime',
        prop: 'time',
        label: '时间',
        format: 'YYYY-MM-DD HH:mm:ss',
        searchable: true
      }, {
        fieldType: 'editor',
        prop: 'text',
        label: '测试',
      }, {
        prop: 'op',
        label: '操作',
        slot: 'op'
      }],
      demo: [
        {
          id: '1',
          demo: "demo",
          time: '2020-11-11 00:00:00'
        }, {
          id: '2',
          demo: "test",
          time: '2020-11-11 00:00:00'
        }
      ],
    }
  },
  methods: {
    onClick(row) {
      console.log(this.test)
    },
    click() {
      this.xxx = "llll"
      console.log(this.$refs.datagrid.getSelectedIds())
      this.$axios.post('/demo', this.model).then((res) => {
        console.log(res)
      });
    },
    next() {
      if (this.active++ > 2) this.active = 0;
    }
  },
  mounted() {
  },
  created() {
    this.user.username = 222
  }
}

</script>

<style scoped>
.el-backtop {
  z-index: 99999;
}
</style>
