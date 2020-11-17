<template>

    <v-container class="mx-6 my-10">

    <div class="testClass" v-for='areaofinterest in areaofint' :key='areaofinterest.id'>
        <p>{{areaofinterest.name}}</p>
    </div>
     </v-container>

</template>

<script>

    export default {
        name: 'AreaOfInterestList',
        data (){

            return {
                areaofint: []
            }

        },
        methods:
            {
                loadAreaOfInterest() {

                    this.$store.state.areaofinterest.newareaofint = {name: "TESTNINE"}

                    this.$store.dispatch("areaofinterest/addAreaOfInterest")

                    this.$store.dispatch("areaofinterest/getAreaOfInterest")

                    this.areaofint = this.$store.state.areaofinterest.areaofint;
                }
            },
        mounted() {
            window.addEventListener('load', () => {
                this.loadAreaOfInterest()
            })

        }
    }
</script>
