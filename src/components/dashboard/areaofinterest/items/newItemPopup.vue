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
                    <span class="headline">Item</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="itemName"
                                        label="Item Name*"
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
                            @click="addNewItem(itemName, area)"
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
    const API_URL = process.env.VUE_APP_API_URL;

    export default {
        name: 'Popup',
        aoeName: 'default',

        props: ['areaname', 'areaid', 'area'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{

            addNewItem(itemName, area) {

                let _this = this;


                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' 
                }

                axios.post(API_URL + 'area_of_interest_items/' ,
                    { name: itemName, parentid: area.id, value: 'testvalue'}, { 'headers': headers})
                    .then(function (response) {
                        if (response.status == 201) {

                          _this.$emit('success', 'Area of Interest item has been created')
                          _this.$emit('update')
                        }
                        else
                        {
                          _this.$emit('error', 'Area of Interest item was NOT created')
                        }
                    })
                    .catch((error) => {
                      _this.$emit('error', 'ERROR: ' + error)
                    })

                this.dialog = false
            },





        }

    }
</script>
