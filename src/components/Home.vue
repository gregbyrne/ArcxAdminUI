<template>
  <div class="home">
    <h1>Home</h1>
    <p>
      This is the home page for ARC-X Admin
    </p>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Home',
  props: {
    msg: String
  },
  computed: {
    getCurrentUser() {
      return this.$store.getters.getCurrentUser;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
