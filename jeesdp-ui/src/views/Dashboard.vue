<template>
    <div>
        <dynamic-form ></dynamic-form>
    </div>
</template>
<style>
    .el-tag {
        margin-top: 4px;
    }
</style>
<script>

    export default {
        data() {
            return {}
        },
        methods: {
            demo() {
                console.log(this.value)
            },
        }
    };


</script>