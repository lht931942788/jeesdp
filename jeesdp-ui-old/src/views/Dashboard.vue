<template>
  <div ref="container" style="height: 360px;width: 100%;"></div>
</template>

<script>
import * as monaco from 'monaco-editor';
import 'monaco-editor/esm/vs/basic-languages/java/java.contribution'

export default {
  data() {
    return {
      monacoEditor: {}
    }
  },
  mounted: function () {
    // 初始化编辑器，确保dom已经渲染，dialog中要写在opened中
    this.monacoEditor = monaco.editor.create(this.$refs.container, {
      value: null,
      language: "java",
      minimap: {
        enabled: false
      },
    });
  },
  methods: {}
}
</script>