<template>
    <div>
        <el-form ref="search" :model="params" :inline="true">
            <el-form-item label="名称" prop="name">
                <el-input v-model="params.name"></el-input>
            </el-form-item>
            <el-form-item style="text-align: right">
                <el-button-group>
                    <el-button type="primary" :title="$t('button.search')" @click="loadTableData()">
                        {{$t('button.search')}}
                    </el-button>
                    <el-button type="primary" :title="$t('button.reset')" @click="searchClear">
                        {{$t('button.reset')}}
                    </el-button>
                </el-button-group>
            </el-form-item>
        </el-form>
        <div style="padding-bottom: 10px">
            <el-button-group>
                <el-button type="primary" :title="$t('button.add')" icon="el-icon-plus"
                           @click="open($t('button.add'))"
                           v-if="checkPermission('system:role:add')"></el-button>
                <el-button :title="$t('button.remove')" @click="remove" icon="el-icon-delete" type="danger"
                           v-if="checkPermission('system:role:remove')"></el-button>
            </el-button-group>
        </div>
        <el-table :data="tableData" :height="height" @selection-change="selectionChange" @sort-change="sortChange"
                  border row-key="id" tooltip-effect="light">
            <el-table-column prop="id" type="selection" width="40" align="center"></el-table-column>
            <el-table-column label="名称" prop="name" sortable></el-table-column>
            <el-table-column label="说明" prop="description" sortable width="140"></el-table-column>
            <el-table-column v-if="checkPermission('system:role:edit')" label="操作" align="center" width="140">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button :title="$t('button.edit')" type="primary" icon="el-icon-edit"
                                   v-if="checkPermission('system:role:edit')"
                                   @click="edit(scope.row)"></el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="sizeChange" @current-change="currentChange"
                       :current-page="page.pageNum" :page-size="page.pageSize" :page-sizes="[10, 20, 30, 40, 50, 100]"
                       layout="jumper, prev, pager, next, sizes,  total" :total="page.total">
        </el-pagination>
        <el-dialog :title="title" :visible.sync="dialogVisible" @closed="closed" width="30%"
                   :close-on-click-modal="false">
            <el-form ref="form" :model="formData" :rules="rules" status-icon label-width="120px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="description">
                    <el-input v-model="formData.description"></el-input>
                </el-form-item>
                <el-form-item label="菜单" prop="resources">
                    <el-cascader ref="cascader" v-model="formData.resources" :options="options" :collapse-tags="true"
                                 :show-all-levels="false"
                                 :props="{ multiple: true, emitPath: true, value: 'id', label: 'name', checkStrictly: false}" @change="change()"
                                 clearable></el-cascader>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">{{$t('button.cancel')}}</el-button>
                <el-button type="primary" @click="save('form')" :loading="saveLoading">{{$t('button.ok')}}</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>

    export default {
        name: 'Role',
        data() {
            return {
                height: 0,
                options: [],
                order: null,
                page: {
                    pageNum: 1,
                    pageSize: 5,
                    total: 0,
                },
                params: {},
                formData: {name: '', description: '', resources: []},
                rules: {
                    name: [
                        {required: true, message: '请输入名称'},
                    ],
                },
                title: '',
                saveLoading: false,
                dialogVisible: false,
                tableSeletedIds: [],
                tableData: []
            }
        },
        methods: {
            loadParams() {
                let params = this.params;
                params['pageNum'] = this.page.pageNum;
                params['pageSize'] = this.page.pageSize;
                params['order'] = this.order;
                return params;
            },
            loadTableData() {
                this.$ajax.post('role/getRolesByPage', this.loadParams()).then((data) => {
                    this.tableData = data.list;
                    this.page.total = data.total;
                })
            },
            save(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.saveLoading = true;
                        this.$ajax.post('role/saveRole', this.formData).then((data) => {
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
                this.$ajax.post('role/getRoleById', {id: row.id}).then((data) => {
                    this.formData = data.role;
                    this.formData.resources = data.resources;
                    this.open(this.$t('button.edit'));
                })
            },
            remove() {
                if (this.tableSeletedIds.length > 0) {
                    this.$confirm('确定要删除这些数据？', '提示', {
                        type: 'warning',
                    }).then(() => {
                        this.$ajax.post('role/deleteRoles', {ids: this.tableSeletedIds}).then((data) => {
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
            sortChange(column) {
                if (column.order) {
                    this.order = column.prop + ' ' + column.order.replace('ending', '');
                } else {
                    this.order = null;
                }
                this.loadTableData();
            },
            searchClear() {
                this.resetForm('search');
                this.loadTableData();
            },
            resetForm(name) {
                this.$refs[name].resetFields();
            },
            open(title) {
                this.title = title;
                this.dialogVisible = true;
            },
            close() {
                this.dialogVisible = false;
            },
            closed() {
                this.saveLoading = false;
                this.resetForm('form');
            },
            sizeChange(size) {
                this.page.pageSize = size;
                this.loadTableData();
            },
            currentChange(current) {
                this.page.pageNum = current;
                this.loadTableData();
            },
            selectionChange(selection) {
                let ids = [];
                for (let selected of selection) {
                    ids.push(selected["id"]);
                }
                this.tableSeletedIds = ids;
            },
            resize() {
                this.height = document.body.offsetHeight - 256;
            },
            change(){
                console.log(this.formData.resources)
            }
        },
        created() {
            this.loadTableData();
            this.$ajax.post('resource/getResources').then((data) => {
                this.options = this.toTree(data);
            })
        },
        mounted() {
            this.$nextTick(() => {
                this.resize();
                window.onresize = () => {
                    this.resize();
                }
            })
        }
    }
</script>