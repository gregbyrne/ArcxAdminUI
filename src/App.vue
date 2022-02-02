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
  import axios from "axios";
  import $ from "jquery";

export default {
  name: 'App',
  data: function() {
    return {

    };

  },

  components: {
    Navbar,
  },
  methods:{
    getData(){
      var site = "https://climateadaptationadminstg.epa.gov/headers/headers.jsp"
      //site = "http://localhost:8080/headers.html"
      // eslint-disable-next-line no-console
      //console.log(response.headers["content-type"])

      var name = "codemzy";$.get(site, function(response) {  console.log(response);});


    },
  },
  created(){
    this.getData()

  }
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
