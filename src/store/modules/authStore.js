const state = {
  isAuthenticated: false,
  currentUser: {
    userId: "",
    email: ""
  },
  intervalName: ""
};

const getters = {
  getIsAuthenticated(state) {
    return state.isAuthenticated;
  },
  getCurrentUser(state) {
    return state.currentUser;
  },
  getIntervalName(state) {
    return state.intervalName;
  }
};

const mutations = {
  setIsAuthenticated(state, isAuthenticated) {
    state.isAuthenticated = isAuthenticated;
  },
  setCurrentUser(state, currentUser) {
    state.currentUser.userId = currentUser.userId;
    state.currentUser.email = currentUser.email;
  },
  setIntervalName(state, intervalName) {
    state.intervalName = intervalName;
  },
  clearCurrentUser(state) {
    state.currentUser.userId = "";
    state.currentUser.email = "";
  }
};

const actions = {
  // eslint-disable-next-line no-unused-vars
  async login({ commit, dispatch }, loginData) {},
  // eslint-disable-next-line no-unused-vars
  async getCurrentUser({ commit, dispatch }) {},
  // eslint-disable-next-line no-unused-vars
  async refresh({ commit, dispatch, getters }) {},
  // eslint-disable-next-line no-unused-vars
  async logout({ commit, dispatch, getters }) {}
};

export default {
  namespaced: false,
  state,
  getters,
  mutations,
  actions
};
