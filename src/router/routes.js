import Login from "../components/login/Login.vue";
import Dashboard from "../components/Dashboard.vue";
import Profile from "../components/login/Profile"

const routes = [
  {
    path: "/login",
    component: Login
  },
  {
    path: "/profile",
    component: Profile
  },
  {
    path: "/",
    component: Dashboard
  }

];

export default routes;
