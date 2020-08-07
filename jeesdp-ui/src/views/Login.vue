<template>
    <div align="center" style="padding-top: 200px">
        <div style="width: 400px">
            <el-form :model="formData" status-icon :rules="rules" ref="from" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input type="text" v-model="formData.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="formData.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login('from')">{{$t('button.login')}}</el-button>
                    <el-button @click="reset('from')">{{$t('button.reset')}}</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
    import Oauth from "../utils/oauth";
    import CryptoJS from 'crypto-js';

    export default {
        name: 'Login',
        data() {
            return {
                formData: {
                    username: 'sys',
                    password: 'admin',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名'},
                    ],
                    password: [
                        {required: true, message: '请输入密码'},
                    ]
                }
            };
        },
        methods: {
            login(formName) {
                let username = this.formData.username;
                let password = this.formData.password;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        Oauth.login(username, password)
                    } else {
                        return false;
                    }
                });
            },
            reset(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            console.log(CryptoJS.AES.encrypt('my message', 'secret key 123').toString());
        }
    }
</script>
