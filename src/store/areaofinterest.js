import AreaOfInterestService from '../services/areaofinterest.service'

let area_of_interest = null;
if (localStorage.getItem('area_of_interest') != 'undefined') {
    area_of_interest = JSON.parse(localStorage.getItem('area_of_interest'));
}
const initialState = area_of_interest
export const areaofinterest = {
namespaced: true,
state: initialState,
actions: {
    getAreaOfInterest({ commit }, accessToken) {

        return AreaOfInterestService.getAreaOfInterest(accessToken).then(
            area_of_interest => {
                commit('setAreaOfInterest', area_of_interest);
                return Promise.resolve(area_of_interest);
            },
            error => {
                return Promise.reject(error);
            }
        );
    }
},
//to handle mutations
mutations: {
    setAreaOfInterest(state, area_of_interest) {
            state.area_of_interest = area_of_interest;
    }
}
};
