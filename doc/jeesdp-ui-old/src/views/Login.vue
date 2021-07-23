<template>
  <div contenteditable="true">
    <el-tag text="sss"></el-tag>
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
