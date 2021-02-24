<template>
  <div ref="container" :style="{height: height + 'px'}"></div>
</template>

<script>
import * as monaco from "monaco-editor";
import EditorWorker from 'monaco-editor/esm/vs/editor/editor.worker?worker'
import JsonWorker from 'monaco-editor/esm/vs/language/json/json.worker?worker'
import CssWorker from 'monaco-editor/esm/vs/language/css/css.worker?worker'
import HtmlWorker from 'monaco-editor/esm/vs/language/html/html.worker?worker'
import TsWorker from 'monaco-editor/esm/vs/language/typescript/ts.worker?worker'

self.MonacoEnvironment = {
  getWorker(_, label) {
    if (label === 'json') {
      return new JsonWorker();
    }
    if (label === 'css' || label === 'scss' || label === 'less') {
      return new CssWorker();
    }
    if (label === 'html' || label === 'handlebars' || label === 'razor') {
      return new HtmlWorker();
    }
    if (label === 'typescript' || label === 'javascript') {
      return new TsWorker();
    }
    return new EditorWorker();
  }
}
export default {
  name: "MonacoEditor",
  props: {
    value: {
      type: String,
    },
    height: {
      type: Number,
      default: 300
    },
    language: {
      type: String,
      default: 'html'
    },
    readonly: {
      type: Boolean,
      default: false
    }
  },
  emits: ['update:value'],
  data() {
    return {
      editor: null
    }
  },
  mounted() {
    this.editor = monaco.editor.create(this.$refs.container, {
      value: this.value,
      language: this.language,
      minimap: {
        enabled: false
      },
      readOnly: this.readonly,
      theme: 'dark'
    });
    this.editor.addCommand(monaco.KeyMod.CtrlCmd | monaco.KeyCode.KEY_S, (a) => {
      this.$emit('update:value', this.editor.getModel());
    })
  }
}
</script>
<style scoped>

</style>
