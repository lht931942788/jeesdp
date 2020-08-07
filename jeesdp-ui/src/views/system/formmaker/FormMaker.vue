<template>
    <fm-making-form ref="makingform" style="height: 100%;">
        <template slot="action">
            <el-button-group>
                <el-button @click="save" icon="fa fa-floppy-o" title="保存" type="primary"></el-button>
                <el-button @click="back" icon="fa fa-reply" title="返回" type="primary"></el-button>
            </el-button-group>
        </template>
    </fm-making-form>
</template>
<style>
    .fm2-container > .el-footer {
        display: none;
    }
</style>
<script>
    export default {
        name: 'FormMaker',
        methods: {
            save() {
                let content = this.$refs.makingform.getJSON();
                this.$ajax.post('/form/update', {
                    id: this.getQueryValue('id'),
                    content: JSON.stringify(content)
                }).then((data) => {
                    this.$message({
                        type: data.type,
                        message: this.$t(data.code)
                    });
                })
            },
            back() {
                window.location.href = '/#/system/formlist';
            }
        },
        created() {
            this.$ajax.post('/form/getById', {
                id: this.getQueryValue('id')
            }).then((data) => {
                this.$refs.makingform.setJSON(JSON.parse(data.content));
            })
        }
    }
</script>