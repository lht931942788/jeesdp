<template>
  <el-form-item
      v-if="field.type"
      :label="field.label + '：'"
      :label-width="field.labelWidth"
      :prop="field.prop"
      :rules="validate && !preview ? field.rules : undefined"
  >
    <template v-if="preview && model && field.type !== 'editor' && Array.isArray(model)">
      <template v-if="field.options && field.options.type && field.options.type.indexOf('range') > -1">
        <div>{{ model[0] + ' - ' + model[1] }}</div>
      </template>
      <template v-else>
        <div>{{ model.join(',') }}</div>
      </template>
    </template>
    <template v-if="preview && model && !Array.isArray(model)">
      <div>{{ model }}</div>
    </template>

    <el-radio-group
        v-if="field.type === 'radio' && !preview"
        v-model="model"
    >
      <el-radio
          v-for="item in options"
          :key="item.value"
          :label="item.value"
          :disabled="field.disabled"
      >
        {{ item.label }}
      </el-radio>
    </el-radio-group>

    <el-checkbox-group
        v-if="field.type === 'checkbox' && !preview"
        v-model="model"
    >
      <el-checkbox
          v-for="item in options"
          :key="item.value"
          :label="item.value"
          :disabled="field.disabled"
      >
        {{ item.label }}
      </el-checkbox>
    </el-checkbox-group>

    <el-input
        v-if="field.type === 'input' && !preview"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder" :type="field.options.type"
        clearable
    />

    <el-time-picker
        v-if="field.type === 'timePicker' && !preview"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
        clearable
    />

    <el-date-picker
        v-if="field.type === 'datePicker' && !preview"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
        :type="field.options.type"
        clearable
    />

    <el-switch
        v-if="field.type === 'switch' && !preview"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
    />

    <el-cascader
        v-if="field.type === 'cascader' && !preview"
        v-model="model"
        :disabled="field.disabled"
        :options="options"
        :placeholder="field.placeholder"
        clearable
        filterable
    />

    <el-select v-if="field.type === 'select' && !preview"
               v-model="model"
               :disabled="field.disabled"
               :placeholder="field.placeholder"
               clearable
               filterable
    >
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>

    <wang-editor v-if="field.type === 'editor' && !preview" v-model:value="value"/>
  </el-form-item>

</template>

<script>
import dayjs from "dayjs";

export default {
  name: "FormItem",
  props: {
    value: {
      required: true,
    },
    field: {
      type: Object,
      required: true,
    },
    preview: {
      type: Boolean,
      default: false,
    },
    validate: {
      type: Boolean,
      default: true,
    },
    options: {
      type: Object,
    },

  },
  emits: ['update:value'],
  data() {
    return {
      model: null,
    }
  },
  created() {
    this.model = this.value;
  },
  watch: {
    model: {
      handler(newValue, oldValue) {
        let value = newValue;
        if (newValue && (this.field.type === 'datePicker' || this.field.type === 'timePicker')) {
          value = dayjs(newValue).format("YYYY-MM-DD HH:mm:ss");
        }
        this.$emit('update:value', value);
      }
    },
    value: {
      handler(newValue, oldValue) {
        this.model = newValue;
      }
    },
  },
}
</script>

<style scoped>

</style>
