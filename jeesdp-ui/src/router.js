import Vue from 'vue';
import VueRouter from 'vue-router';
import jsCookie from "js-cookie";
import Oauth from "./utils/oauth";
import {loadPermissions, toTree} from "./utils/utils";

Vue.use(VueRouter);

const originalPush = VueRouter.prototype.push;

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err);
};

const router = new VueRouter({
    //mode:'history',
    routes: [{
        path: '/login',
        component: () => import('./views/Login.vue')
    }]
});

const routes = [{
    path: '/',
    component: () => import('./views/Index.vue'),
    redirect: '/dashboard',
    children: []
}];

router.beforeEach((to, from, next) => {
    let path = to.path;
    if (!jsCookie.get("access_token")) {
        if (path === '/login') {
            next();
        } else {
            next({path: '/login'});
        }
    } else {
        if (Vue.prototype.$user === undefined) {
            Oauth.loadUser().then(user => {
                user.resources = toTree(user.resources);
                let permissions = [];
                Vue.prototype.$user = user;
                loadRoutes(routes[0].children, user.resources);
                router.addRoutes(routes);
                loadPermissions(permissions, user.resources, '');
                user.permissions = permissions;
                next({...to})
            });
        } else {
            next();
        }
    }
});

function loadRoutes(routes, resources) {
    if (resources != null) {
        for (let i = 0; i < resources.length; i++) {
            let route;
            if (resources[i].type !== '2' && resources[i].path != null && resources[i].path !== '') {
                route = {
                    name: resources[i].code,
                    path: resources[i].url,
                    component: () => import('./views/' + resources[i].path + '.vue'),
                    children: []
                };
                if (resources[i].children) {
                    loadRoutes(route.children, resources[i].children)
                }
                routes.push(route);
            }
        }
    }
    return routes;
}

export default router;
