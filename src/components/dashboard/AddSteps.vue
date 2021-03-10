<template>
    <v-container>
        <h1 style="text-align: center; color: #0071bc">ARC-X Dashboard</h1>



            <input type="radio" name="tabs" id="tab1" @click="componentView= 'StepsToHelpPrepare'" checked />
            <label for="tab1">Steps to Help Prepare</label>
            <input type="radio" name="tabs" id="tab2" @click="componentView= 'SortStepToHelpPrepare'" />
            <label for="tab2">Sort Steps to Help Prepare</label>

            <div class="tab content1"><component :is="componentView"></component></div>
            <div class="tab content2"><component :is="componentView"></component></div>
    </v-container>
</template>

<script>

    import StepsToHelpPrepare from './steps/StepsToHelpPrepare'
    import SortStepToHelpPrepare from './steps/SortStepToHelpPrepare'


    export default {
        components : { StepsToHelpPrepare, SortStepToHelpPrepare},
        name: 'Add Steps',
        data: function() {
            return {
                componentView: StepsToHelpPrepare
            }
        },
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


