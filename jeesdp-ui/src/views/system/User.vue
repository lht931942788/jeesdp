<template>
    <div>
        <el-form ref="search" :model="params" :inline="true">
            <el-form-item label="名称" prop="username">
                <el-input v-model="params.username"></el-input>
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
                           v-if="checkPermission('system:user:add')"></el-button>
                <el-button :title="$t('button.remove')" @click="del" icon="el-icon-delete" type="danger"
                           v-if="checkPermission('system:user:remove')"></el-button>
            </el-button-group>
        </div>
        <el-table :data="tableData" :height="height" @selection-change="selectionChange" border row-key="id">
            <el-table-column prop="id" type="selection" width="40" align="center"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="userType" label="用户类型">
                <template slot-scope="scope">
                    {{$t("dict.yhlx." + scope.row.userType)}}
                </template>
            </el-table-column>
            <el-table-column v-if="checkPermission('system:role:edit')" label="操作" align="center" width="140">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button :title="$t('button.edit')" type="primary" icon="el-icon-edit"
                                   v-if="checkPermission('system:user:edit')"
                                   @click="edit(scope.row)"></el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="sizeChange" @current-change="currentChange"
                       :current-page="page.pageNum" :page-size="page.pageSize" :page-sizes="[10, 20, 30, 40, 50]"
                       layout="jumper, prev, pager, next, sizes,  total" :total="page.total">
        </el-pagination>
        <el-dialog :title="title" :visible.sync="dialogVisible" @closed="closed('form')" width="30%">
            <el-form ref="form" :model="formData" :rules="rules" status-icon label-width="120px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="formData.username"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="formData.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="formData.email"></el-input>
                </el-form-item>
                <el-form-item label="用户类型" prop="userType">
                    <el-select v-model="formData.userType" placeholder="请选择" style="width: 100%">
                        <el-option :label="label" v-for="(label,value) of $t('dict.yhlx')" v-if="value !== '1'"
                                   :value="value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色" prop="roles">
                    <el-select v-model="formData.roles" placeholder="请选择" style="width: 100%" multiple collapse-tags>
                        <el-option v-for="item in roles" :value="item.id" :label="item.name">
                        </el-option>
                    </el-select>
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
        name: 'User',
        data() {
            return {
                height: 0,
                page: {
                    pageNum: 1,
                    pageSize: 10,
                    total: 0,
                },
                params: {},
                roles: [],
                formData: {
                    username: '',
                    name: '',
                    phone: '',
                    email: '',
                    userType: '3',
                    roles: [],
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名'},
                        {min: 5, max: 10, message: '长度在5-10个字符'},
                    ],
                    name: [
                        {required: true, message: '请输入姓名'},
                        {min: 2, max: 5, message: '长度在2-5个字符'},
                    ],
                    phone: [
                        {required: true, message: '请输入手机号'},
                        {min: 11, max: 11, message: '请输入正确手机号'}
                    ],
                    email: [
                        {required: true, message: '请输入姓名'},
                        {type: 'email', message: '邮箱格式错误'}
                    ],
                    userType: [
                        {required: true, message: '请选择用户类型'},
                    ],
                    roles: [
                        {required: true, message: '请选择用户角色'},
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
                return params;
            },
            loadTableData() {
                this.$ajax.post('user/getUsersByPage', this.loadParams()).then((data) => {
                    this.tableData = data.list;
                    this.page.total = data.total;
                })
            },
            save(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.saveLoading = true;
                        delete this.formData['authorities'];
                        delete this.formData['resources'];
                        this.formData.roles = this.formData.roles.join(",");
                        this.$ajax.post('user/saveUser', this.formData).then((data) => {
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
                this.$ajax.post('user/getUserById', {id: row.id}).then((data) => {
                    this.formData = data;
                    this.open(this.$t('button.edit'));
                })
            },
            del() {
                if (this.tableSeletedIds.length > 0) {
                    this.$confirm('确定要删除这些数据？', '提示', {
                        type: 'warning'
                    }).then(() => {
                        this.$ajax.post('user/deleteUsers', {ids: this.tableSeletedIds}).then((data) => {
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
            selectionChange(selection) {
                let ids = [];
                for (let selected of selection) {
                    ids.push(selected["id"]);
                }
                this.tableSeletedIds = ids;
            },
            searchClear() {
                this.resetForm('search');
                this.loadTableData();
            },
            open(title) {
                this.title = title;
                this.dialogVisible = true;
            },
            close() {
                this.dialogVisible = false;
            },
            closed(name) {
                this.saveLoading = false;
                this.resetForm(name);
            },
            resetForm(name) {
                this.$refs[name].resetFields();
            },
            sizeChange(size) {
                this.page.pageSize = size;
                this.loadTableData();
            },
            currentChange(current) {
                this.page.pageNum = current;
                this.loadTableData();
            },
            resize() {
                this.height = document.body.offsetHeight - 256;
            }
        },
        created() {
            this.loadTableData();
            this.$ajax.post('role/getRoles').then((data) => {
                this.roles = data;
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