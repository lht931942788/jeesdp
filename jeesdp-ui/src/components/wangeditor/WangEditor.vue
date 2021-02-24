<template>
  <div ref="editor"></div>
</template>

<script>
import WangEditor from "wangeditor";

export default {
  name: "WangEditor",
  props: {
    height: {
      type: Number,
      default: 300
    },
    value: {
      required: true
    }
  },
  emits: ['update:value'],
  data() {
    return {
      editor: null
    }
  },
  mounted() {
    this.editor = new WangEditor(this.$refs.editor);
    this.editor.config.zIndex = 20;
    this.editor.config.onchange = (newHtml) => {
      this.$emit('update:value', newHtml);
    };
    this.editor.config.uploadVideoServer = '/api/upload-video';
    this.editor.config.uploadVideoHeaders = {};
    this.editor.config.uploadImgServer = '/upload-img';
    this.editor.config.uploadImgHeaders = {};
    this.editor.create();
    this.editor.txt.html(this.value);
  },
  watch: {
    value: {
      handler(newValue, oldValue) {
        this.editor.txt.html(this.value);
      }
    }
  }
}
</script>

<style scoped>

</style>
