import * as VueRouter from 'vue-router';

const routes = [
    {
        name: 'login',
        path: '/login',
        component: () => import('../views/Login.vue'),
    },
    {
        name: 'home',
        path: '/',
        component: () => import('../views/Home.vue'),
        children: [{
            name: 'index',
            path: '/index',
            component: () => import('../views/Index.vue'),
        },]
    },
]
const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
});
/*
router.beforeEach((to, from, next) => {
    let path = to.path;
    if (!jsCookie.get("access_token")) {
        if (path === '/login') {
            next();
        } else {
            next({path: '/login'});
        }
    } else {
        next();
    }
});*/


export default router;
