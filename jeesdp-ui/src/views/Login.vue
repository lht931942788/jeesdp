<template>
  <div align="center" style="padding-top: 200px">
    <div style="width: 400px">
      <el-form :model="formData" status-icon :rules="rules" label-position="top" ref="from" label-width="100px">
        <el-form-item prop="username">
          <el-input type="text" v-model="formData.username" placeholder="请输入用户名" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="formData.password" placeholder="请输入密码" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" style="width: 100%" @click="login('from')">{{ $t('button.login') }}</el-button>
      </el-form>
    </div>
  </div>
</template>
<script>
import Oauth from "../utils/oauth";

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
  }
}
</script>
