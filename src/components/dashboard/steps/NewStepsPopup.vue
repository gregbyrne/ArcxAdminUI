<template>
    <v-row mt-n12 justify="center" >

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
                    Add
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Step to Help</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="stepName"
                                        label="Step Name*"
                                        required
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    <small>*indicates required field</small>
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
                            @click="addNewStep(stepName)"
                    >




                        Save
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>


</template>

<script>
    import axios from "axios";
    const STEP_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE;

    export default {


        data: () => ({
            stepName: '',
            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,
            newName: '',
        }),
        methods:{

            addNewStep(newname) {

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.post(STEP_URL ,
                    { name: newname}, { 'headers': headers})
                    .then(function (response) {
                        if (response.status == 201) {

                            _this.$emit('update')
                            alert('Step to Help has been created');
                        }
                        else
                        {
                            alert('Step to Help was not created');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: ' + error);
                    })

                this.dialog = false

            },





        }

    }
</script>
