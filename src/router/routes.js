import Login from "../components/login/Login.vue";
import Dashboard from "../components/dashboard/Dashboard.vue";
import Profile from "../components/login/Profile"
import addSteps from "../components/dashboard/AddSteps.vue";
import additionalInfo from "../components/dashboard/AdditionalInformation.vue";

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
    name: "AreaOfInterest",
    component: Dashboard
  },
  {
    path: "/areaofinterest",
    name: "AreaOfInterest",
    component: Dashboard
  },
  {
    path: "/stepstohelpprepare",
    name: "AddSteps",
    component: addSteps
  },
  {
    path: "/additionalinformation",
    name: "AdditionalInfo",
    component: additionalInfo
  }



];

export default routes;
