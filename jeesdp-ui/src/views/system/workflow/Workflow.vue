<template>
    <div class="content"><el-button @click="demo()" icon="el-icon-setting" title="表单设计" type="primary"></el-button>
        <common-grid ref="grid" :form="form" :prop="prop" :search="search" :table="table">
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
                        add: 'system:workflow:add',
                        remove: 'system:workflow:remove',
                        edit: 'system:workflow:edit',
                    },
                    url: {
                        save: 'workflow/save',
                        remove: 'workflow/removeByIds',
                        row: 'workflow/getById',
                        update: 'workflow/update',
                        data: 'workflow/listByPage',
                    },
                },
                search: {
                    fields: [{
                        type: 'input',
                        prop: 'name',
                        width: '178px',
                        label: '名称',
                    }],
                    params: {
                        name: 'aaa',
                    },
                },
                form: {
                    labelWidth: '120px',
                    fields: [{
                        prop: 'id',
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
                        prop: 'status',
                        label: '状态',
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
            demo() {
               this.$refs.grid.load();
            },
        },
        created() {

        }
    }

</script>