<template>
  <div ref="container" :style="{height: height + 'px'}"></div>
</template>

<script>
import * as monaco from 'monaco-editor'

self.MonacoEnvironment = {
  getWorker(_, label) {
    return new DefaultWorkerFactory(label);
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
