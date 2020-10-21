import Login from "../components/login/Login.vue";
import Home from "../components/Home.vue";
import Profile from "../components/login/Profile"

const routes = [
  {
    path: "/login",
    component: Login
  },
  {
    path: "/",
    component: Home
  },
  {
    path: "/profile",
    component: Profile
  }
];

export default routes;
