import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false;

Vue.use(VeeValidate);

Vue.use(Vuex);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app');
