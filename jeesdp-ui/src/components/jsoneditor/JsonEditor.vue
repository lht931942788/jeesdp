<template>
  <div ref="editor"></div>
</template>

<script>
import "jsoneditor/dist/jsoneditor.css";
import JSONEditor from "jsoneditor";

export default {
  name: "JsonEditor",
  props: {
    value: {
      type: String,
    },
    disabled: {
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
  methods: {
    setText(text) {
      return this.editor.setText(text);
    },
    getText() {
      return this.editor.getText();
    },
    setMode(name) {
      this.editor.setMode(name);
    }
  },
  mounted() {
    let mode = this.disabled ? 'preview' : 'code';
    this.editor = new JSONEditor(this.$refs.editor, {
      mode: mode,
      onChange: () => {
        this.$emit('update:value', this.editor.get());
      }
    });
    if (!this.disabled) {
      document.querySelector(".jsoneditor-poweredBy").remove();
    }
    if (this.value instanceof String) {
      this.value = JSON.parse(this.value);
    }
    this.editor.set(this.value);
  },
}
</script>

<style scoped>

</style>
