import 'font-awesome/css/font-awesome.min.css';
import 'element-ui/lib/theme-chalk/index.css';
import 'form-making/dist/FormMaking.css';
import './assets/css/jeesdp.css';
import Vue from 'vue';
import ElementUI from 'element-ui';
import jsCookie from 'js-cookie';
import axios from './utils/axios';
import router from './router';
import i18n from './lang/languages';
import FormMaking from 'form-making';
import App from './App';
import DynamicForm from './components/DynamicForm';
import DataGrid from './components/DataGrid';
import CommonGrid from './components/CommonGrid';
import {toTree} from './utils/utils'

Vue.use(ElementUI, {size: 'mini', zIndex: 3000});
Vue.use(FormMaking, {lang: 'zh-CN', i18n})
Vue.component("DynamicForm", DynamicForm);
Vue.component("DataGrid", DataGrid);
Vue.component("CommonGrid", CommonGrid);
Vue.prototype.$cookie = jsCookie;
Vue.prototype.$ajax = axios;
Vue.prototype.toTree = toTree;

Vue.prototype.checkPermission = function (code) {
    let user = Vue.prototype.$user;
    let permissions = user.permissions;
    let permission = code.split(",");

    for (let i = 0; i < permissions.length; i++) {
        for (let j = 0; j < permission.length; j++) {
            if (permissions[i] === permission[j]) {
                return true
            }
        }
    }
    return false;
};
Vue.prototype.checkUserType = function (userType) {
    return Vue.prototype.$user.userType === userType;
};

Vue.prototype.hasRole = function (role) {
    let user = Vue.prototype.$user;
    let roles = user.roles;
    for (let i = 0; i < roles.length; i++) {
        if (roles[i] === role) {
            return true
        }
    }
    return false;
};


new Vue({
    el: '#app', i18n, router, render: h => h(App)
});
