<template>
  <el-form-item
      v-if="field.fieldType"
      :label="field.label"
      :label-width="field.labelWidth"
      :prop="field.prop"
      :required="field.required"
      :rules="field.rules"
  >

    <el-radio-group
        v-if="field.fieldType === 'radio'"
        v-model="model"
    >
      <el-radio
          v-for="(value,key) in options"
          :key="value"
          :label="key"
      >
        {{ value }}
      </el-radio>
    </el-radio-group>

    <el-checkbox-group
        v-if="field.fieldType === 'checkbox'"
        v-model="model"
    >
      <el-checkbox
          v-for="(value,key) in options"
          :key="value"
          :label="key"
      >
        {{ value }}
      </el-checkbox>
    </el-checkbox-group>

    <el-input
        v-if="field.fieldType === 'input'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder" :type="field.type"
    />

    <el-time-picker
        v-if="field.fieldType === 'timePicker'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
    />

    <el-date-picker
        v-if="field.fieldType === 'datePicker'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
        :type="field.type"
    />

    <el-switch
        v-if="field.fieldType === 'switch'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
    />

    <el-cascader
        v-if="field.fieldType === 'cascader'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :options="options"
        :placeholder="field.placeholder"
    />

    <el-select v-if="field.fieldType === 'select'"
               v-model="model"
               :clearable="true"
               :disabled="field.disabled"
               :placeholder="field.placeholder"
    >
      <el-option
          v-for="(value,key) in options"
          :key="key"
          :label="value"
          :value="key"
      />
    </el-select>
    <wang-editor v-if="field.fieldType === 'editor'" v-model:value="value"/>
  </el-form-item>
</template>

<script>
import dayjs from "dayjs";

export default {
  name: "FormItem",
  props: {
    field: {
      type: Object,
      required: true,
    },
    value: {
      required: true,
    },
    options: {
      type: Object,
    }
  },
  emits: ['update:value'],
  data() {
    return {
      model: null,
    }
  },
  watch: {
    model: {
      handler(newValue, oldValue) {
        let value = newValue;
        if (newValue && (this.field.fieldType === 'datePicker' || this.field.fieldType === 'timePicker')) {
          value = dayjs(newValue).format("YYYY-MM-DD HH:mm:ss");
        }
        this.$emit('update:value', value);
      }
    },
    value: {
      handler(newValue, oldValue) {
        this.model = newValue;
      }
    }
  },
}
</script>

<style scoped>

</style>
