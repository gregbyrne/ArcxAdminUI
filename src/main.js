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
import axios from 'axios';
import $ from "jquery";

const AOI_HEADERS = process.env.VUE_APP_API_ARCX_HEADERS_URL;


Vue.config.productionTip = false;

Vue.use(VeeValidate, Vuex, vuetify, axios);

Vue.config.silent = true;

Vue.config.productionTip = false;

Vue.component('Popup', Popup);

Vue.component('AreaOfInterestList', AreaOfInterestList);

Vue.component('Dashboard', Dashboard);



Vue.mixin(

    {


  methods:{
    getUserId(){
      var site = AOI_HEADERS
      var _this = this;
      $.get(site, function(response) {
        _this.testing2 = response;
        var text = $($.parseHTML(response)[9])[0];
        var id =  $($(text).find("tbody>tr")[30]).find("td")[1].innerHTML;
        _this.epauserid = id;

      });


    },
  }
})

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app');
