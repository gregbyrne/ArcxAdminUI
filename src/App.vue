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

export default {
  name: 'App',

  components: {
    Navbar,
    Popup
  },
};
</script>

<style scoped>
.theme--light.v-application {
  background : #F5F5F5;
}

</style>
