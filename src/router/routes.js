import Login from "../components/login/Login.vue";
import Home from "../components/Home.vue";

const routes = [
  {
    path: "/login",
    component: Login
  },
  {
    path: "/",
    component: Home
  }
];

export default routes;
