import Vue from 'vue';
import VueI18n from 'vue-i18n'

Vue.use(VueI18n);

const messages = {
    "zh-CN": {
        message: {
            success: '操作成功！',
            error: '操作失败！',
            loginError: "用户名或密码错误"
        },
        dict: {
            sf: {
                '1': '是',
                '2': '否'
            },
            cdlx: {
                '1': '菜单',
                '2': '按钮',
                '3': '路由',
            },
            yhlx: {
                '1': '超级管理员',
                '2': '管理员',
                '3': '普通用户',
            }
        },
        button: {
            add: '新增',
            edit: '修改',
            remove: '删除',
            save: '保存',
            cancel: '取消',
            ok: '确定',
            search: '查询',
            reset: '重置',
            login: '登录',
        }
    },
    en: {
        message: {
            loginError: "用户名或密码错误"
        }
    }
};

export default new VueI18n({
    locale: 'zh-CN',
    messages: messages,
});