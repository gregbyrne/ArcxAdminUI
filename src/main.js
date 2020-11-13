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
import axios from 'axios';

Vue.config.productionTip = false;

Vue.use(VeeValidate, Vuex, vuetify, axios);

Vue.component('Popup', Popup);

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app');
