<template>
  <v-app >
    <navbar></navbar>

      <v-main >
        <v-container >
          <router-view />
        </v-container>

      </v-main>


  </v-app>
</template>

<script>
  import Navbar from './components/navigation/Navbar';
  import $ from "jquery";

export default {
  name: 'App',
  data: function() {
    return {
      testing2: null,
      log2: false,
      epauserid : null,

    };

  },

  components: {
    Navbar,
  },
  methods:{
    getData(){
      var site = "https://climateadaptationadminstg.epa.gov/headers/headers.jsp"
      site = "http://localhost:8080/headers.html"
      var _this = this;

      $.get(site, function(response) {
        _this.testing2 = response;
        var text = $($.parseHTML(response)[9])[0];
        var id =  $($(text).find("tbody>tr")[30]).find("td")[1].innerHTML;
        _this.epauserid = id;

      });
    },

  },
  created(){
    this.getData();

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
