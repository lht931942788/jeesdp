import Cookie from 'js-cookie';
import axios from './axios';
import router from '../router';
import {Message} from "element-ui";

let base_uri = "http://localhost:9002";
const client = {
    client_id: '1',
    client_secret: 'demo',
    scope: 'user',
    grant_type: 'password',
    authorize_uri: base_uri + '/oauth/authorize',
    token_uri: base_uri + '/oauth/token',
    redirect_uri: 'http://localhost',
    user_uri: base_uri + '/oauth/user',
    user_info_uri: base_uri + '/oauth/userinfo',
    logout_uri: base_uri + '/oauth/logout',
    check_token_uri: base_uri + '/oauth/check_token'
};

const Oauth = {
    client: client,
    login: function (username, password) {
        axios.post(client.token_uri, {
            client_id: client.client_id,
            client_secret: client.client_secret,
            scope: client.scope,
            grant_type: client.grant_type,
            username: username,
            password: password
        }).then(token => {
            Cookie.set('access_token', token.access_token);
            router.push('/dashboard');
        }).catch(() => {
            Message.warning('用户名或密码错误');
        });
    },
    loadUser: function () {
        return axios.post(client.user_uri);
    },
    getToken: function () {
        return Cookie.get('access_token');
    },
    removeToken: function () {
        Cookie.remove('openKeys');
        Cookie.remove('menuId');
        Cookie.remove('access_token');
    },
    logout: function () {
        axios.get(client.logout_uri).then(() => {
            this.removeToken();
            window.flag = false;
            router.push('/login');
        });
    }
};

export default Oauth;
