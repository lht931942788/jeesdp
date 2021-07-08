import "../assets/css/jeesdp.css"
import "font-awesome/css/font-awesome.css"

import naive from 'naive-ui'
import cookie from "js-cookie"
import axios from "./axios"
import locale from "./locale"
import router from "./router"

export default {
    install: (app, options) => {
        app.use(router);
        app.use(locale);
        app.use(naive);
        let methods = {};
        methods['demo1'] = new Function(`
            console.log(this.props)
        `);
        app.mixin({
            methods: methods
        });
        app.config.globalProperties.$axios = axios;
        app.config.globalProperties.$cookie = cookie;
        app.config.globalProperties.user = {};
    }
}

