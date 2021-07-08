import * as VueRouter from "vue-router";
import cookie from "js-cookie";

const routes = [{
    name: 'login',
    path: '/login',
    component: () => import('../../views/Login.vue'),
}, {
    name: 'index',
    path: '/',
    component: () => import('../../views/Index.vue'),
    children: [{
        name: 'home',
        path: '/home',
        component: () => import('../../views/Home.vue'),
    },]
},]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
});

let flag = true;

router.beforeEach((to, from, next) => {
    let path = to.path;
    cookie.set("access_token", "ss")
    if (cookie.get("access_token")) {
        if (flag) {
            let routeList = [{
                name: 'system',
                path: '/system',
                component: () => import('../../views/Router.vue'),
            }]

            routeList.forEach(value => {
                router.addRoute('index', value);
            })
            flag = false;
            router.push({path: '/home'}).then(r => {
                next();
            })
        } else {
            next();
        }
    } else {
        if (path === '/login') {
            next();
        } else {
            next({path: '/login'});
        }
    }
});

export default router;
