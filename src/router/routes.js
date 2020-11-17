import Login from "../components/login/Login.vue";
import Dashboard from "../components/dashboard/Dashboard.vue";
import Profile from "../components/login/Profile"

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile
  },
  {
    path: "/",
    name: "Dashboard",
    component: Dashboard
  }

];

export default routes;
