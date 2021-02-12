<template>
    <v-row justify="center">

        <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }" >
                <v-btn
                        color="primary"
                        dark
                        small
                        v-bind="attrs"
                        v-on="on"
                >
                    delete
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Delete Step to Help</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="step.name"
                                        label="Step to help prepare to delete"
                                        required
                                        readonly
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    Are you sure you want to delete {{step.name}}? This will also delete all items and sub-items associated with the area.
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog = false"
                    >
                        Close
                    </v-btn>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="deleteStepToHelpPrepare(step.id)"
                    >


                        Delete
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>


</template>

<script>
    import axios from "axios";
    const STEP_DELETE_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE_DELETE_URL;


    export default {
        name: 'Popup',
        aoeName: 'default',
        props: ['step'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{
            deleteStepToHelpPrepare(stepid)
            {
                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                // eslint-disable-next-line no-console
                console.log("URL: " + STEP_DELETE_URL + stepid)

                axios.delete(STEP_DELETE_URL + stepid,{ 'headers': headers})
                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('success', 'Step deleted successfully')

                            _this.$emit('update')

                        }
                        else
                        {
                          _this.$emit('error', 'Step was not deleted. Something went wrong.')
                        }
                    })
                    .catch((error) => {
                        _this.$emit('error', 'ERROR: with delete ' + error)
                    });

                this.dialog = false;



            },



        }

    }
</script>
