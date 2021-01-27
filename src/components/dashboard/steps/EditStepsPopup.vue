<template>
    <v-row justify="center" >

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
                    Edit
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Edit Step To Help</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="step.name"
                                        label="Step To Help Name*"
                                        required
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="step.subTitle"
                                        label="Subtitle"

                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-textarea
                                        v-model="step.description"
                                        label="Description"
                                        clearable
                                        outlined
                                ></v-textarea>
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
                            @click="editStep(step.name, step)"
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
    const STEPS_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE;



    export default {
        name: 'Popup',
        stepName: 'default',
        props: ['step'],




        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{


            editStep(changedName, step ){
                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(STEPS_URL + step.id,{ name: changedName, id: step.id, subTitle: step.subTitle, description: step.description}, {'headers': headers} )
                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('update')
                            alert('Steps to Help has been edited');
                        }
                        else
                        {
                            alert('Steps to Help was not edited');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: with edit ' + error);
                    });

                this.dialog = false


            },




        },
        computed:{

        }

    }
</script>
