import "element-plus/lib/theme-chalk/index.css";
import "../assets/css/jeesdp.css";

import cookie from "js-cookie";
import axios from "./axios";
import locale from "./locale";
import router from "./router";
import ElementPlus from "element-plus";
import utils from './utlis';
import dayjs from "dayjs";
import DataGrid from "./datagrid";
import FormItem from "./formitem";
import CommonGrid from "./commongrid";
import AsideMenu from "./asidemenu";
import JsonEditor from "./jsoneditor";
import WangEditor from "./wangeditor";
import MonacoEditor from "./monacoeditor";

export default {
    install: (app, options) => {
        app.use(router);
        app.use(locale);
        app.use(ElementPlus, {
            size: 'small',
            i18n: locale.global.t,
        });
        app.config.globalProperties.$axios = axios;
        app.config.globalProperties.$cookie = cookie;
        app.config.globalProperties.$dayjs = dayjs;
        app.config.globalProperties.$utils = utils;
        app.config.globalProperties.user = {};
        app.component(JsonEditor.name, JsonEditor);
        app.component(MonacoEditor.name, MonacoEditor);
        app.component(WangEditor.name, WangEditor);
        app.component(DataGrid.name, DataGrid);
        app.component(FormItem.name, FormItem);
        app.component(CommonGrid.name, CommonGrid);
        app.component(AsideMenu.name, AsideMenu);
    }
}

