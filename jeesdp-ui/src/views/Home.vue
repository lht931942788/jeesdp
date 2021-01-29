<template>
  <common-grid ref="grid" :dictionaries="dictionaries" :fields="fields"/>
  <div id="editor"></div>
</template>
<script>

export default {
  name: 'Home',
  data() {
    return {
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
      console.log(row)
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
    /*ClassicEditor.create(document.querySelector('#editor'), {
      initialData: '<h2>Initial data</h2><p>Foo bar.</p>',
      language: 'zh-cn',
    }).then(editor => {
      console.log(editor);
      //console.log(Array.from(editor.ui.componentFactory.names()))
    }).catch(error => {
      console.error(error);
    });*/
    //this.$refs.grid.setData(this.demo);
  },
  created() {
    console.log(this.user.username)
    this.user.username = 222
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
.ck-content {
  height: 600px !important;
}
</style>
