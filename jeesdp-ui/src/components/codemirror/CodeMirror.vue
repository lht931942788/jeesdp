<template>
  <div ref="editor" style="height:200px;width:600px;"></div>
</template>

<script>

import {basicSetup, EditorState, EditorView} from "@codemirror/basic-setup"
import {StreamLanguage} from "@codemirror/stream-parser"
import {javascript} from "@codemirror/legacy-modes/mode/javascript"

export default {
  name: "CodeMirror",
  data() {
    return {
      code: '',
      editor: null
    }
  },
  mounted() {
    let view = new EditorView({
      state: EditorState.create({
        extensions: [
          basicSetup,
          StreamLanguage.define(javascript),
        ]
      }),
      parent: this.$refs.editor
    })
  }
}
</script>

<style scoped>

</style>
