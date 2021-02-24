import "element-plus/lib/theme-chalk/index.css";
import "../assets/css/jeesdp.css";

import cookie from "js-cookie";
import axios from "./axios";
import locale from "./locale";
import router from "./router";
import dayjs from "dayjs";
import DataGrid from "./datagrid";
import FormItem from "./formitem";
import CommonGrid from "./commongrid";
import AsideMenu from "./asidemenu";
import JsonEditor from "./jsoneditor";
import WangEditor from "./wangeditor";
import MonacoEditor from "./monacoeditor";
import ElementPlus from "element-plus";

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
        app.component(JsonEditor.name, JsonEditor);
        app.component(MonacoEditor.name, MonacoEditor);
        app.component(WangEditor.name, WangEditor);
        app.component(DataGrid.name, DataGrid);
        app.component(FormItem.name, FormItem);
        app.component(CommonGrid.name, CommonGrid);
        app.component(AsideMenu.name, AsideMenu);
    }
}

