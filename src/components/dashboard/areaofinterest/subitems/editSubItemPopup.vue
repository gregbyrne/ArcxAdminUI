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
                    Edit
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Edit Sub Item {{  subItem.name }}</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="subItem.name"
                                        label="Sub Item Name*"
                                        required
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    <small>*indicates required field</small> {{newName}}
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
                            @click="editAOE(subItem)"
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
        newName: '',
        props: ['areaname', 'areaid', 'areaofint', 'area', 'subItem'],




        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{


            editAOE(subItem ){

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(API_URL + 'area_of_interest_sub_items/' + subItem.id ,{ name: subItem.name, parentid: subItem.parentid, value: subItem.value}, {'headers': headers} )
                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('update')
                            alert('Sub Item has been edited');
                        }
                        else
                        {
                            alert('Sub Item was not edited');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: with edit ' + error);
                    });

                this.dialog = false


            },




        }

    }
</script>
