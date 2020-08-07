<template>
    <div class="content">
        <common-grid :form="form" :prop="prop" :search="search" :table="table" ref="grid">
            <template slot="op" slot-scope="scope">
                <el-button @click="demo(scope.row)" icon="el-icon-setting" title="表单设计" type="primary"></el-button>
            </template>
        </common-grid>
    </div>
</template>

<script>

    export default {
        name: "FormList",
        data() {
            return {
                prop: {
                    permission: {
                        add: 'system:formlist:add',
                        remove: 'system:formlist:remove',
                        edit: 'system:formlist:edit',
                    },
                    url: {
                        save: 'form/save',
                        remove: 'form/removeByIds',
                        row: 'form/getById',
                        update: 'form/update',
                        data: 'form/listByPage',
                    },
                },
                search: {
                    inline: true,
                    fields: [{
                        type: 'input',
                        prop: 'name',
                        width: '178px',
                        label: '名称',
                    }],
                    params: {
                        name: '',
                    },
                },
                form: {
                    labelWidth: '120px',
                    fields: [{
                        type: 'hidden',
                    }, {
                        type: 'input',
                        prop: 'name',
                        label: '名称',
                    }, {
                        type: 'input',
                        prop: 'remark',
                        label: '备注',
                    }],
                    model: {
                        id: '',
                        name: '',
                        remark: '',
                    },
                    rules: {
                        name: [{
                            required: true,
                            message: '名称不能为空'
                        }],
                    },
                },
                table: {
                    rowKey: "id",
                    fields: [{
                        type: 'selection',
                    }, {
                        prop: 'name',
                        width: 100,
                        label: '名称',
                    }, {
                        prop: 'remark',
                        label: '备注',
                    }, {
                        slot: 'op',
                        edit: true,
                        width: 200,
                        label: '操作',
                    }],
                },
            };
        },
        methods: {
            demo(row) {
                window.location.href = "/#/system/formmaker?id=" + row.id
            },
        },
        created() {

        }
    }

</script>