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

router.beforeEach((to, from, next) => {
    let path = to.path;
    if (cookie.get("access_token")) {
        if (path === '/login') {
            next();
        } else {
            next({path: '/login'});
        }
    } else {
        next();
    }
});


export default router;
