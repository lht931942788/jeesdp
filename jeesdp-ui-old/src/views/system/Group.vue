<template>
    <div>
        <div style="padding-bottom: 10px">
            <el-button-group>
                <el-button :title="$t('button.add')" @click="open($t('button.add'))" icon="el-icon-plus"
                           type="primary"
                           v-if="checkPermission('system:menu:add')"></el-button>
                <el-button :title="$t('button.remove')" @click="remove" icon="el-icon-delete" type="danger"
                           v-if="checkPermission('system:menu:remove')"></el-button>
            </el-button-group>
        </div>
        <el-table :data="tableData" @select="select" border row-key="id">
            <el-table-column align="center" prop="id" type="selection" width="40"></el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="地址" prop="url"></el-table-column>
            <el-table-column label="组件路径" prop="path"></el-table-column>
            <el-table-column label="图标" prop="iconClass"></el-table-column>
            <el-table-column label="类型" prop="type">
                <template slot-scope="scope">
                    {{$t('dict.cdlx.' + scope.row.type)}}
                </template>
            </el-table-column>
            <el-table-column label="标识" prop="code"></el-table-column>
            <el-table-column label="排序" prop="sort"></el-table-column>
            <el-table-column align="center" label="操作" v-if="checkPermission('system:menu:edit')" width="140">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button :title="$t('button.edit')" @click="edit(scope.row)" icon="el-icon-edit"
                                   type="primary"
                                   v-if="checkPermission('system:menu:edit')"></el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog :title="title" :visible.sync="dialogVisible" @closed="closed('form')" width="30%">
            <el-form :model="formData" :rules="rules" label-width="120px" ref="form" status-icon>
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="url">
                    <el-input v-model="formData.url"></el-input>
                </el-form-item>
                <el-form-item label="组件路径" prop="path">
                    <el-input v-model="formData.path"></el-input>
                </el-form-item>
                <el-form-item label="图标" prop="iconClass">
                    <el-input v-model="formData.iconClass"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">
                    <el-select placeholder="请选择" style="width: 100%" v-model="formData.type">
                        <el-option :label="label" :value="value"
                                   v-for="(label,value) of $t('dict.cdlx')"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标识" prop="code">
                    <el-input v-model="formData.code"></el-input>
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input-number v-model="formData.sort"></el-input-number>
                </el-form-item>
                <el-form-item label="创建按钮" prop="isCreate" v-if="formData.type !== '2'">
                    <el-radio-group v-model="formData.isCreate">
                        <el-radio :label="value" v-for="(label,value) of $t('dict.sf')">{{label}}</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <span class="dialog-footer" slot="footer">
                <el-button @click="dialogVisible = false">{{$t('button.cancel')}}</el-button>
                <el-button :loading="saveLoading" @click="save('form')" type="primary">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>

    export default {
        name: 'Group',
        data() {
            return {
                saveLoading: false,
                title: '',
                dialogVisible: false,
                tableSeletedIds: [],
                formData: {
                    id: '',
                    pid: '0',
                    type: '1',
                    sort: 1,
                    path: '',
                    url: '',
                    code: '',
                    iconClass: '',
                    isCreate: '1'
                },
                rules: {
                    name: [
                        {required: true, message: '请输入名称'},
                    ],
                    type: [
                        {required: true, message: '请选择类型'},
                    ],
                    code: [
                        {required: true, message: '请输入标识'},
                    ],
                    sort: [
                        {required: true, message: '请输入排序'},
                        {type: 'number', message: '排序必须为数字'}
                    ]
                },
                tableData: [],
                selectRow: null
            }
        },
        methods: {
            loadTableData() {
                this.$ajax.post('resource/getResources').then((data) => {
                    this.tableData = this.toTree(data);
                })
            },
            save(name) {
                if (this.title === this.$t('button.add') && this.selectRow) {
                    this.formData.pid = this.selectRow.id;
                }
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.saveLoading = true;
                        this.$ajax.post('resource/saveResource', this.formData).then((data) => {
                            this.loadTableData();
                            this.$message({
                                type: data.type,
                                message: this.$t(data.code)
                            });
                            this.close();
                        })
                    }
                });
            },
            edit(row) {
                this.$ajax.post('resource/getResourceById', {id: row.id}).then((data) => {
                    delete data['children'];
                    this.formData = data;
                    this.open(this.$t('button.edit'));
                })
            },
            remove() {
                this.selectRow = null;
                if (this.tableSeletedIds.length > 0) {
                    this.$confirm('确定要删除这些数据？', '提示', {
                        confirmButtonText: this.$t('button.ok'),
                        cancelButtonText: this.$t('button.cancel'),
                        type: 'warning',
                    }).then(() => {
                        this.$ajax.post('resource/deleteResources', {ids: this.tableSeletedIds}).then((data) => {
                            this.loadTableData();
                            this.$message({
                                type: data.type,
                                message: this.$t(data.code)
                            });
                        })
                    }).catch(() => {
                    });
                } else {
                    this.$message.error('请选择一条或多条数据！');
                }
            },
            select(selection, row) {
                let ids = [];
                for (let i = 0; i < selection.length; i++) {
                    ids.push(selection[i].id);
                }
                this.tableSeletedIds = ids;
                this.selectRow = row;
            },
            resetForm(name) {
                this.$refs[name].resetFields();
            },
            open(title) {
                if (title === this.$t('button.add') && this.selectRow && this.selectRow.type === '2') {
                    this.$message.error('按钮不能添加子节点！');
                } else {
                    this.title = title;
                    this.dialogVisible = true;
                }
            },
            closed(name) {
                this.resetForm(name);
                this.saveLoading = false;
            },
        },
        created() {
            this.loadTableData();
        }
    }
</script>