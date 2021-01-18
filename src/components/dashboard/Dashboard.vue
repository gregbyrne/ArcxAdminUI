<template>
    <v-container>
        <h1 style="text-align: center; color: #0071bc">ARC-X Dashboard</h1>

        <v-container class="mx-6 my-10">
            <v-row>
                <v-col>

                </v-col>
                <v-col>

                </v-col>
            </v-row>
          <button @click="this.swapComponent('AreaOfInterestList')">Area of Interest</button>
          <button @click="this.swapComponent('SortAreaOfInterest')">Sort Area of Interest</button>
          <component :is="currentComponent"></component>
        </v-container>
    </v-container>
</template>

<script>

    import AreaOfInterestList from './areaofinterest/AreaOfInterest'
    import SortAreaOfInterest from './areaofinterest/SortAreaOfInterest'

    export default {
        components : { 'AreaOfInterestList': AreaOfInterestList, 'SortAreaOfInterest': SortAreaOfInterest},
        name: 'Dashboard',
      data: function() {
        return {
          currentComponent: 'AreaOfInterestList'
        }
        },
        computed: {
            date : function () {
                return Date.now()
            },
            currentUser() {
                return this.$store.state.auth.user
            }
        },
      methods: {
        swapComponent(component) {
          this.currentComponent = component;
        }
      }
    }
</script>


<script>
    export default {
        name: 'Dashboard',
        aoeName: 'asdasd',
        props: {
            msg: String
        },

    }
</script>
