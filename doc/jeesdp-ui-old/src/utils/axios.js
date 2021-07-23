import axios from 'axios'
import jsCookie from 'js-cookie'
import qs from 'querystring'
import router from '../router'
import {Message} from 'element-ui'

const instance = axios.create();

instance.defaults.timeout = 30000;
instance.defaults.withCredentials = true;
instance.defaults.baseURL = 'http://localhost:9002';
instance.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
instance.defaults.transformRequest = [(data, headers) => {
    if (headers['Content-Type'].indexOf("application/x-www-form-urlencoded") > -1) {
        return qs.stringify(data);
    } else {
        return data;
    }
}];

instance.interceptors.request.use(config => {
    //config.headers['X-XSRF-TOKEN'] = jsCookie.get('XSRF-TOKEN');
    let access_token = jsCookie.get('access_token');
    if (access_token) {
        config.headers['Authorization'] = "Bearer " + access_token;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

instance.interceptors.response.use(response => {
    return response.data;
}, error => {
    if (error.response.status) {
        switch (error.response.status) {
            case 400:
                break;
            case 401:
                jsCookie.remove('access_token');
                router.push('/login');
                break;
            case 403:
                Message.error("您没有权限访问！");
                break;
            default:
                return Promise.reject(error);
        }
    } else {
        router.push('/login');
    }

});

export default instance;