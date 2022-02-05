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

Vue.prototype.$waittime =  process.env.VUE_APP_API_WAIT_TIME;


Vue.mixin(

    {

  methods:{
    getUserId(){

        var site = AOI_HEADERS

        var _this = this;
      $.get(site, function(response) {
        $($.parseHTML(response)).find("tbody>tr").map(function (index, element) {

            var row = $($(element).find("td"))[0].innerHTML
            if(row == 'uid'){
                _this.epauserid = $($(element).find("td"))[1].innerHTML
            }
        });

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
