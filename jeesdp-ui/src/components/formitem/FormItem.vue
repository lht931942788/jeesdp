<template>
  <el-form-item
      v-if="field.type"
      :label="field.label"
      :label-width="field.labelWidth"
      :prop="field.prop"
      :rules="validate ? field.rules : undefined"
  >

    <el-radio-group
        v-if="field.type === 'radio'"
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
        v-if="field.type === 'checkbox'"
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
        v-if="field.type === 'input'"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder" :type="field.options.type"
        clearable
    />

    <el-time-picker
        v-if="field.type === 'time'"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
        :is-range="field.isRange"
        clearable
    />

    <el-date-picker
        v-if="field.type.indexOf('date') > -1"
        v-model="model"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
        :type="field.type"
        clearable
    />

    <el-switch
        v-if="field.type === 'switch'"
        v-model="model"
        :clearable="true"
        :disabled="field.disabled"
        :placeholder="field.placeholder"
    />

    <el-cascader
        v-if="field.type === 'cascader'"
        v-model="model"
        :disabled="field.disabled"
        :options="options"
        :placeholder="field.placeholder"
        clearable
        filterable
    />

    <el-select v-if="field.type === 'select'"
               v-model="model"
               :disabled="field.disabled"
               :placeholder="field.placeholder"
               :multiple="field.multiple"
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

    <wang-editor v-if="field.type === 'editor'" v-model:value="value"/>
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
    if (this.value) {
      if ('range'.indexOf(this.field.type) > -1) {
        this.model = this.value.split(' - ')
      } else if (('select'.indexOf(this.field.type) > -1 && this.field.multiple) || 'checkbox'.indexOf(this.field.type) > -1) {
        this.model = this.value.split(',')
      } else {
        this.model = this.value;
      }
    }
  },
  watch: {
    model: {
      handler(newValue, oldValue) {
        let value = newValue;
        if (newValue && ('date'.indexOf(this.field.type) > -1 || this.field.type === 'time')) {
          if ('range'.indexOf(this.field.type) > -1) {
            value[0] = dayjs(newValue[0]).format('YYYY-MM-DD HH:mm:ss');
            value[1] = dayjs(newValue[0]).format('YYYY-MM-DD HH:mm:ss');
            value = value.join(" - ");
          } else {
            value = dayjs(newValue).format('YYYY-MM-DD HH:mm:ss');
          }
        } else if (('select'.indexOf(this.field.type) > -1 && this.field.multiple) || 'checkbox'.indexOf(this.field.type) > -1) {
          value = newValue.join(',');
        }
        this.$emit('update:value', value);
      }
    },
    value: {
      handler(newValue, oldValue) {
        if ('range'.indexOf(this.field.type) > -1) {
          this.model = newValue.split(' - ')
        } else if (('select'.indexOf(this.field.type) > -1 && this.field.multiple) || 'checkbox'.indexOf(this.field.type) > -1) {
          this.model = this.value.split(',')
        } else {
          this.model = newValue;
        }
      }
    },
  },
}
</script>

<style scoped>

</style>
