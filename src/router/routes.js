import Login from "../components/login/Login.vue";
import Dashboard from "../components/dashboard/Dashboard.vue";
import Profile from "../components/login/Profile"
import addSteps from "../components/dashboard/AddSteps.vue";
import additionalInfo from "../components/dashboard/AdditionalInformation.vue";
import region from "../components/dashboard/region/Regions.vue";
import regionReport from "../components/dashboard/region/RegionReport.vue";

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
  },
  {
    path: "/region",
    name: "Region",
    component: region
  },
  {
    path: "/regionreport",
    name: "RegionReport",
    component: regionReport
  }


];

export default routes;
