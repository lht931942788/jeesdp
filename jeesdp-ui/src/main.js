import 'element-plus/lib/theme-chalk/index.css';
import './assets/css/jeesdp.css'
import {createApp} from 'vue'
import ElementPlus from 'element-plus';
import locale from 'element-plus/lib/locale';
import lang from 'element-plus/lib/locale/lang/zh-cn';
import dayjs from "dayjs";
import axios from "./utils/axios.js";
import App from './App.vue';

locale.use(lang);

const app = createApp(App);

app.config.globalProperties.$dayjs = dayjs;
app.config.globalProperties.$axios = axios;

app.use(ElementPlus, {
    size: 'small'
});

app.mount('#app');
