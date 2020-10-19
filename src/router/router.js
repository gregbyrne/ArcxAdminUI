import Vue from "vue";
import Router from "vue-router";
import Home from "@/components/Home";
import Login from "@/components/Login";

Vue.use(Router);

const routes = [
    { path: '/', name: 'home', component: Home },
    { path: '/login', name: 'login', component: Login }
]

export default new Router({
// get rid of the hash (#) in Url
// the hash (#) helps the page not to be reloaded when the URL changes
mode: "history",
routes
});
