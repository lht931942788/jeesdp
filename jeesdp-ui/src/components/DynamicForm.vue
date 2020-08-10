<template>
  <el-form :inline="form.inline" :label-position="form.labelPosition"
           :label-width="form.labelWidth" :rules="form.rules">
    <template v-for="field in form.fields" :field="field" :model="model[field.prop]">
      <el-input style="display: none" v-if="field.type === 'hidden'" v-model="model"></el-input>
      <el-form-item v-else :label="field.label" :prop="field.prop">
        <el-input v-if="field.type === 'input'" v-model="model"
                  :placeholder="field.placeholder" :style="{width:field.width?field.width:'100%'}"></el-input>

        <el-select v-if="field.type === 'select'" v-model="model" :multiple="field.multiple"
                   :placeholder="field.placeholder" :style="{width:field.width?field.width:'100%'}">
          <el-option v-if="field.url" v-for="option in data[field.prop]" :key="field.label"
                     :label="option[field.label?field.label:'id']"
                     :value="option[field.value?field.value:'name']"></el-option>
          <el-option v-else v-for="option in field.options" :key="field.label"
                     :label="option[field.label?field.label:'id']"
                     :value="option[field.value?field.value:'name']"></el-option>
        </el-select>

        <el-input-number v-if="field.type === 'inputNumber'" v-model="model"
                         :style="{width:field.width?field.width:'100%'}"></el-input-number>

        <el-time-select v-if="field.type === 'timeSelect'" v-model="model"
                        :picker-options="field.pickerOptions" :placeholder="field.placeholder"
                        :style="{width:field.width?field.width:'100%'}"></el-time-select>

        <el-date-picker v-if="field.type === 'datePicker'" v-model="model" :type="field.pickerType"
                        :picker-options="field.pickerOptions" :placeholder="field.placeholder"
                        :format="field.format" :value-format="field.valueFormat"
                        :style="{width:field.width?field.width:'100%'}"></el-date-picker>
        <el-cascader v-if="field.type === 'cascader'" :options="field.options"></el-cascader>
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
      required: true
    }
  },
  created() {
    console.log(this.form)
  }
}
</script>

<style scoped>

</style>