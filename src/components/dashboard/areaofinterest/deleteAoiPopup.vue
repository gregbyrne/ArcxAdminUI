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
                    <span class="headline">Delete Area of Interest</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="areaname"
                                        label="Area Of Interest to delete"
                                        required
                                        readonly
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    Are you sure you want to delete {{areaname}}? This will also delete all items and sub-items associated with the area.
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
                            @click="deleteAreaOfInterest(areaid)"
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
    const AOE_DELETE_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_DELETE_URL;


    export default {
        name: 'Popup',
        aoeName: 'default',
        props: ['areaname', 'areaid', 'epauserid','userip'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{

            //DELETE
            deleteAreaOfInterest(areaid)
            {
                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ',
                    'userid' : this.epauserid,
                    'userip' : this.userip
                }

                axios.delete(AOE_DELETE_URL + areaid,{ 'headers': headers})
                    .then(function (response) {
                        if (response.status.toString().includes("20")) {
                            _this.$emit('success', 'Area of Interest has been deleted')
                            _this.$emit('update')
                        }
                        else
                        {
                            _this.$emit('error', 'Area of Interest was NOT deleted')
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
