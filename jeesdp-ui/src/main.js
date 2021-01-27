import 'element-plus/lib/theme-chalk/index.css';
import './assets/css/jeesdp.css';

import {createApp} from 'vue';
import ElementPlus from 'element-plus';
import lang from 'element-plus/lib/locale/lang/zh-cn';
import router from './router';
import components from "./components";
import App from './App.vue';

const app = createApp(App);

app.use(ElementPlus, {size: 'small', lang});
app.use(components);
app.use(router);
app.mount('#app');
