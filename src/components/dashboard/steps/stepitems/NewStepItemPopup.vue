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
                    <span class="headline">Sub Step {{step.id}}</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="newName"
                                        label="Sub Step Name*"
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
                            @click="addNewItem(newName, step)"
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
    const API_URL_STEPS_ITEMS = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE_ITEMS;

    export default {
        name: 'Popup',
        aoeName: 'default',

        props: ['step', 'epauserid'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,
            newName: '',

        }),
        methods:{

            addNewItem(itemName, step) {

                //let _this = this;
                let _this = this;


                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' ,
                  'userid' : 'gbyrne'
                }


                axios.post(API_URL_STEPS_ITEMS,
                    { name: itemName, parentid: step.id}, { 'headers': headers})
                    .then(function (response) {
                        if (response.status == 201) {

                          _this.$emit('success', 'Step item added successfully')

                          _this.$emit('update')
                        }
                        else
                        {
                          _this.$emit('error', 'Step item was not added. Something went wrong.')
                        }
                    })
                    .catch((error) => {
                        _this.$emit('error', 'ERROR MESSAGE: ' + error)
                    })

                this.dialog = false
            },





        }

    }
</script>
