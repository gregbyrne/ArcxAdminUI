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
            <steps></steps>
        </v-container>
    </v-container>
</template>

<script>

    import steps from './steps/StepsToHelpPrepare'

    export default {
        components : { 'steps': steps},
        name: 'Add Steps',
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


