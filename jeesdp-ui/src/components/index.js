import "element-plus/lib/theme-chalk/index.css";
import "../assets/css/jeesdp.css";
import "font-awesome/css/font-awesome.css";

import cookie from "js-cookie";
import axios from "./axios";
import locale from "./locale";
import router from "./router";
import ElementPlus from "element-plus";
import dayjs from "dayjs";
import utils from './utlis';
import DataGrid from "./datagrid";
import FormItem from "./formitem";
import CommonGrid from "./commongrid";
import AsideMenu from "./asidemenu";
import JsonEditor from "./jsoneditor";

export default {
    install: (app, options) => {
        app.use(router);
        app.use(locale);
        app.use(ElementPlus, {
            size: 'mini',
            i18n: locale.global.t,
        });
        let methods = {};
        methods['demo1'] = new Function(`
            console.log(this.props)
        `);
        app.mixin({
            methods: methods
        });
        app.config.globalProperties.$axios = axios;
        app.config.globalProperties.$cookie = cookie;
        app.config.globalProperties.$dayjs = dayjs;
        app.config.globalProperties.$utils = utils;
        app.config.globalProperties.user = {};
        app.component(JsonEditor.name, JsonEditor);
        app.component(DataGrid.name, DataGrid);
        app.component(FormItem.name, FormItem);
        app.component(CommonGrid.name, CommonGrid);
        app.component(AsideMenu.name, AsideMenu);
    }
}

