import Login from "../components/login/Login.vue";
import Dashboard from "../components/dashboard/Dashboard.vue";
import Profile from "../components/login/Profile"
import addSteps from "../components/dashboard/AddSteps.vue";

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
  },{
    path: "/addsteps",
    name: "AddSteps",
    component: addSteps
  }

];

export default routes;
