import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from './auth.module';

import { areaofinterest } from './areaofinterest';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    areaofinterest
  }
});
