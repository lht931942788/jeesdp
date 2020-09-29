import Vue from 'vue';
import Antd from 'ant-design-vue';
import App from './App';
import router from 'router'
import 'ant-design-vue/dist/antd.css';

Vue.config.productionTip = false;

Vue.use(Antd);

new Vue({
    el: '#app', router: router, render: h => h(App)
});
