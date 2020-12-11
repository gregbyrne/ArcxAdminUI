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
            <AreaOfInterestList />
        </v-container>
    </v-container>
</template>0

<script>

    import AreaOfInterestList from './areaofinterest/AreaOfInterest'

    export default {
        components : { 'AreaOfInterestList': AreaOfInterestList},
        name: 'Dashboard',
        computed: {
            date : function () {
                return Date.now()
            },
            currentUser() {
                return this.$store.state.auth.user
            }
        }
    }
</script>


<script>
    export default {
        name: 'Dashboard',
        props: {
            msg: String
        },

    }
</script>
