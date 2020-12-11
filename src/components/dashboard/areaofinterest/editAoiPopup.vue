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
                    <span class="headline">Edit Area of Interest</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="areaname"
                                        label="Area Of Interest Name*"
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
                            @click="editAOE(areaname, areaid)"
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
    const AOI_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_URL;
    import jQuery from "jquery";



    export default {
        name: 'Popup',
        aoeName: 'default',
        props: ['areaname', 'areaid', 'areaofint'],




        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{


            editAOE(newname, areaid ){

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(API_URL + AOI_URL + areaid,{ name: newname}, {'headers': headers} )
                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('update')
                            alert('Area of Interest has been edited');
                        }
                        else
                        {
                            alert('Area of Interest was not edited');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: with edit ' + error);
                    });

                this.dialog = false
                this.getAreaOfInterest()


            },
            //New GET request
            getAreaOfInterest()
            {
                jQuery.ajaxSetup({
                    headers : {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                    }
                });

                var _this = this;

                jQuery.getJSON('http://localhost:7100/api/area_of_interest', function (areaofint) {
                    _this.areaofint = areaofint._embedded.area_of_interest;
                });
                alert('ahere')

            }



        }

    }
</script>
