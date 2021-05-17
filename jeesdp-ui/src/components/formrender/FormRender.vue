<template>
  <el-form ref="form" :disabled="disabled" :inline="inline" :label-position="labelPosition" :label-suffix="labelSuffix"
           :label-width="labelWidth + 'px'" :model="model" status-icon>
    <form-item v-for="field in fields" v-model:value="model[field.prop]" :field="field"
               :options="options[field.dict ? field.dict : field.prop]"/>
    <slot :model="model"/>
  </el-form>
</template>

<script>
export default {
  name: "FormRender",
  props: {
    fields: {
      type: Array,
      required: true
    },
    model: {
      type: Object,
      default: {}
    },
    labelPosition: {
      type: String,
      default: 'right'
    },
    labelSuffix: {
      type: String,
      default: '：'
    },
    labelWidth: {
      type: Number,
      default: 120
    },
    inline: {
      type: Boolean,
      default: false
    },
    validate: {
      type: Boolean,
      default: true
    },
    disabled: {
      type: Boolean,
      default: false
    },
    options: {
      type: Object
    },
  },
  methods: {
    validate(callback) {
      this.$refs.form.validate((valid, messages) => {
        callback(valid, messages);
      });
    },
    reset() {
      this.$refs.form.resetFields();
    },
    clearValidate(fields) {
      this.$refs.form.clearValidate(fields);
    },
    getData() {
      return this.model;
    }
  }
}
</script>

<style scoped>

</style>
