import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import vuetify from './plugins/vuetify';
import Popup from './components/dashboard/Popup'
import AreaOfInterestList from "./components/dashboard/areaofinterest/AreaOfInterest";
import Dashboard from "./components/dashboard/Dashboard";
import Region from "./components/dashboard/region/Regions";
import axios from 'axios';


Vue.config.productionTip = false;

Vue.use(VeeValidate, Vuex, vuetify, axios);

Vue.config.silent = true;

Vue.config.productionTip = false;

Vue.component('Popup', Popup);

Vue.component('AreaOfInterestList', AreaOfInterestList);

Vue.component('Dashboard', Dashboard);
Vue.component('Region', Region);

Vue.prototype.$waittime =  process.env.VUE_APP_API_WAIT_TIME;


Vue.mixin(

    {

  methods:{
    getUserId(){


        var _this = this;
         _this.epauserid = "gbyrne"
         _this.userip = "1.1"




    },
  }
})

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app');
