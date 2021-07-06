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
                    <span class="headline">Area of Interest</span>
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
                            @click="addNewAoe(areaname)"
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
    const AOI_URL2 = process.env.VUE_APP_API_AREA_OF_INTEREST;

    export default {
        name: 'Popup',
        aoeName: 'default',
        props: ['areaname', 'areaid'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,
            newName: '',
        }),
        methods:{

            addNewAoe(newname) {

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.post(AOI_URL2 + "/" ,
                    { name: newname}, { 'headers': headers})
                    .then(function (response) {
                        if (response.status.toString().includes("20")) {

                            _this.$emit('success', 'Area of Interest has been created')
                            _this.$emit('update')

                        }
                        else
                        {
                            _this.$emit('error', 'Area of Interest was NOT created')
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
