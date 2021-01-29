import "element-plus/lib/theme-chalk/index.css";
import "../assets/css/jeesdp.css";

import axios from 'axios';
import Qs from "qs";
import locale from "./locale";
import router from "./router";
import ElementPlus, {ElNotification as notify} from "element-plus";
import dayjs from "dayjs";
import DataGrid from "./datagrid";
import FormItem from "./formitem";
import CommonGrid from "./commongrid";

export default {
    install: (app, options) => {
        app.use(router);
        app.use(locale);
        app.use(ElementPlus, {
            size: 'small',
            i18n: locale.global.t,
        });
        app.config.globalProperties.$axios = instance;
        app.config.globalProperties.$dayjs = dayjs;
        app.component(DataGrid.name, DataGrid);
        app.component(FormItem.name, FormItem);
        app.component(CommonGrid.name, CommonGrid);
        app.config.globalProperties.user = {};
        app.config.globalProperties.clone = (source, target) => {
            target = target || {};
            for (let i in source) {
                if (source.hasOwnProperty(i)) {
                    target[i] = source[i];
                }
            }
            return target;
        }
    }
}
const instance = axios.create();

instance.defaults.timeout = 30000;
instance.defaults.withCredentials = true;
instance.defaults.baseURL = host;
instance.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';
instance.defaults.transformRequest = [(data, headers) => {
    if (headers['Content-Type'].indexOf("application/x-www-form-urlencoded") > -1) {
        return Qs.stringify(data);
    } else if (headers['Content-Type'].indexOf("application/json") > -1) {
        return JSON.stringify(data);
    } else {
        return data;
    }
}];

instance.interceptors.request.use(config => {
    //config.headers['X-XSRF-TOKEN'] = jsCookie.get('XSRF-TOKEN');
    return config;
}, error => {
    return Promise.reject(error);
});

instance.interceptors.response.use(response => {
    return response.data;
}, error => {
    if (error.response) {
        switch (error.response.status) {
            case 400:
                break;
            case 401:
                break;
            case 403:
                break;
            default:
                return Promise.reject(error);
        }
    } else {
        notify.error({
            title: '错误',
            message: '这是一条错误的提示消息'
        });
    }
});
