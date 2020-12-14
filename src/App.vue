<template>
  <v-app >
    <navbar></navbar>


      <v-main >
        <v-container >
          <router-view/>
        </v-container>

      </v-main>


  </v-app>
</template>

<script>
  import Navbar from './components/navigation/Navbar';

export default {
  name: 'App',

  components: {
    Navbar,
  },
};
</script>

<style scoped>
.theme--light.v-application {
  background: #F5F5F5;
}

</style>

<style>

  .v-main__wrap
  {
    background-color: #d9d9d9
  }

</style>
