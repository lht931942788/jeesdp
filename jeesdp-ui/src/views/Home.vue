<template>
  <el-date-picker
      v-model="test"
      placeholder="选择日期时间"
      type="datetime">
  </el-date-picker>
  <common-grid ref="grid" :fields="fields" :options="options">
    <template #demo="{ row }">
      {{ row.demo }}
    </template>
    <template #operation="{ row }">
      {{ row.demo }}
    </template>
  </common-grid>
  <el-button @click="click">哈哈</el-button>
</template>
<script>

export default {
  name: 'Home',
  data() {
    return {
      test: '',
      options: {
        demo: [{
          value: "aa",
          label: "测试",
        }, {
          value: "bb",
          label: "测试2",
        }, {
          value: "cc",
          label: "测试3",
        },]
      },
      fields: [{
        type: 'select',
        prop: 'ceshi',
        label: '测试',
        dict: 'demo',
        rules: [{type: 'string', required: true, message: '请选择测试'}],
      }, {
        type: 'radio',
        prop: 'demo',
        label: '测试',
        slot: 'demo',
      }, {
        type: 'datePicker',
        prop: 'time',
        label: '时间',
        format: 'YYYY-MM-DD HH:mm:ss',
        rules: [{type: 'date', required: true, message: '请选择日期'}],
        searchable: true,
        options: {
          type: 'datetime'
        },
      }, {
        type: 'editor',
        prop: 'text',
        label: '测试',
      }, {
        prop: 'operation',
        type: 'operation',
        label: '操作',
        slot: 'operation',
        fixed: 'right'
      }],
      demo: [
        {
          id: '1',
          demo: 'demo',
          ceshi: 'aa',
          time: '2020-11-11 00:00:00'
        }, {
          id: '2',
          demo: 'demo',
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
      let test = this.test;
      this.$axios.post('/demo', {
        demo: '2021-03-10 17:35:15'
      }).then((res) => {
        this.test = res.data.demo
      });
    },
    next() {
      if (this.active++ > 2) this.active = 0;
    }
  },
  mounted() {
    this.$refs.grid.setData(this.demo);
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
