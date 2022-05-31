import axios from 'axios';

const API_URL = process.env.VUE_APP_API_URL;

class AreaOfInterestService {
  getAreaOfInterest(accessToken) {

    // eslint-disable-next-line no-console
    console.log("API_URL: " + API_URL + 'area_of_interest')

    return axios.get(API_URL + 'area_of_interest', { 'headers': { 'Authorization': 'Bearer ' +
            accessToken} }).then((response => {
      localStorage.setItem('area_of_interest', JSON.stringify(response.data._embedded.area_of_interest));
      // eslint-disable-next-line no-console
      //console.log("DATA: " + JSON.stringify(response.data._embedded));
      return response.data._embedded.area_of_interest;
    }))
  }
}

export default new AreaOfInterestService();
