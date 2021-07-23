<template>
  <el-form ref="form" :inline="form.inline" :label-position="form.labelPosition"
           :label-width="form.labelWidth" :model="model" :rules="form.rules">
    <template v-for="field in form.fields">
      <el-input style="display: none" v-if="field.type === 'hidden'" v-model="model[field.prop]"></el-input>
      <el-form-item v-else :label="field.name" :prop="field.prop">
        <el-input v-if="field.type === 'input'" v-model="model[field.prop]"
                  :placeholder="field.placeholder ? field.placeholder : '请填写' + field.name"
                  :style="{width:field.width ? field.width : '100%'}" :max="field.max" :min="field.min"></el-input>

        <el-input v-if="field.type === 'password'" type="password" v-model="model[field.prop]"
                  :placeholder="field.placeholder ? field.placeholder : '请填写' + field.name"
                  :style="{width:field.width ? field.width : '100%'}"></el-input>

        <el-input v-if="field.type === 'textarea'" autosize type="textarea" v-model="model[field.prop]"
                  :placeholder="field.placeholder ? field.placeholder : '请填写' + field.name"
                  :style="{width:field.width ? field.width : '100%'}" :max="field.max" :min="field.min"></el-input>

        <el-select v-if="field.type === 'select'" v-model="model[field.prop]" :multiple="field.multiple"
                   :placeholder="field.placeholder ? field.placeholder : '请选择' + field.name"
                   :style="{width:field.width ? field.width : '100%'}" :filterable="field.filterable">
          <el-option v-for="option in field.options" :key="option[field.value ? field.value : 'id']"
                     :label="option[field.label ? field.label : 'name']"
                     :value="option[field.value ? field.value : 'id']"></el-option>
        </el-select>

        <el-input-number v-if="field.type === 'inputNumber'" v-model="model[field.prop]"
                         :placeholder="field.placeholder ? field.placeholder : '请填写' + field.name"
                         :style="{width:field.width ? field.width : '100%'}"></el-input-number>

        <el-time-select v-if="field.type === 'timeSelect'" v-model="model[field.prop]"
                        :picker-options="field.pickerOptions"
                        :placeholder="field.placeholder  ? field.placeholder : '请选择' + field.name"
                        :style="{width:field.width ? field.width : '100%'}"></el-time-select>

        <el-date-picker v-if="field.type === 'datePicker'" v-model="model[field.prop]" :type="field.pickerType"
                        :picker-options="field.pickerOptions"
                        :placeholder="field.placeholder  ? field.placeholder : '请选择' + field.name"
                        :format="field.format" :value-format="field.valueFormat"
                        :style="{width:field.width ? field.width : '100%'}"></el-date-picker>

        <el-cascader v-if="field.type === 'cascader'"
                     :options="field.options  ? field.placeholder : '请选择' + field.name"></el-cascader>
      </el-form-item>
    </template>
  </el-form>
</template>

<script>

export default {
  name: 'DynamicForm',
  props: {
    form: {
      type: Object,
      required: true,
    },
    model: {
      type: Object,
      required: true,
    },
  },
  methods: {
    reset() {
      this.$refs.form.resetFields();
    },
    validate(func) {
      this.$refs.form.validate((valid) => {
        return func(valid);
      });
    }
  },
  created() {
    if (this.form.labelPosition === undefined) {
      this.form.labelPosition = 'right'
    }
  }
}
</script>

<style scoped>

</style>