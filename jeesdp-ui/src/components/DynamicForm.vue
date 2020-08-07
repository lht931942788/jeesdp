<template>
    <el-form :inline="form.inline" :label-position="form.labelPosition"
             :label-width="form.labelWidth" :rules="form.rules">
        <dynamic-form-item v-for="field in form.fields" :field="field" :model="model[field.prop]"></dynamic-form-item>
    </el-form>
</template>

<script>

    import DynamicFormItem from "./DynamicFormItem";

    export default {
        name: "DynamicForm",
        components: {DynamicFormItem},
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