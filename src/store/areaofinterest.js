// import dependency to handle HTTP request to our back end
import axios from 'axios'

const area_of_interest = JSON.parse(localStorage.getItem('area_of_interest'));
const areaofint = area_of_interest
export const areaofinterest = {

namespaced: true,
//to handle state
state: {
    areaofint,
    newareaofint: []
},

//to handle actions
actions: {
    getAreaOfInterest({ commit }) {

        axios.get('http://localhost:7100/api/area_of_interest', { 'headers': { 'Authorization': 'Bearer ' +
                    this.state.auth.user.accessToken} }).then((area_of_interest => {
            localStorage.setItem('area_of_interest', JSON.stringify(area_of_interest.data._embedded.area_of_interest));
            // eslint-disable-next-line no-console
            commit('SET_AREA_OF_INTEREST', JSON.stringify(area_of_interest.data._embedded.area_of_interest))
        }))
    },
    addAreaOfInterest(){

        // eslint-disable-next-line no-console
        console.log("START: " + JSON.stringify(this.newareaofint))

        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.auth.user.accessToken
        }

        axios.post('http://localhost:7100/api/area_of_interest',
            this.newareaofint, { 'headers': headers})
            .then(function (response) {
                // eslint-disable-next-line no-console
                console.log(response);
            })

    }
},

//to handle mutations
mutations: {
    SET_AREA_OF_INTEREST(state, area_of_interest) {
        state.areaofint = area_of_interest;
    }
}
};
