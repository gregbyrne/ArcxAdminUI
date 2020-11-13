<template>
  <v-app >
    <navbar></navbar>
    <popup></popup>


      <v-main >
        <v-container >
          <router-view/>
        </v-container>

      </v-main>


  </v-app>
</template>

<script>
  import Navbar from './components/navigation/Navbar';
  import Popup from './components/dashboard/Popup';
  import axios from "axios";

export default {
  name: 'App',

  components: {
    Navbar,
    Popup
  },
  mounted() {
    this.getAreasOfInterest();
  },
  methods: {
    getAreasOfInterest()
    {
      axios.get('http://localhost:7100/api/area_of_interest', { 'headers': { 'Authorization': 'Bearer ' +
                  this.$store.state.auth.user.accessToken} }).then((response => {
        // eslint-disable-next-line no-console
        console.log(JSON.stringify(response.data))
      })).catch((error) => {
        // eslint-disable-next-line no-console
        console.log(error)
      });
    }
  }
};
</script>

<style scoped>
.theme--light.v-application {
  background : #F5F5F5;
}

</style>
