import axios from "axios";
import Qs from "qs";
import {ElNotification as notify} from "element-plus";

const instance = axios.create();

instance.defaults.timeout = 30000;
instance.defaults.withCredentials = true;
instance.defaults.baseURL = 'http://localhost:9002';
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
    console.log(response)
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
        notify.error({message: '服务器异常'});
        return Promise.reject(error);
    }
});

export default instance;
