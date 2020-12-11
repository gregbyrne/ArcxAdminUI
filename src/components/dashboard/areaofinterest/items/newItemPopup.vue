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
                                        v-model="aoeName"
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
                            @click="addNewItem(aoeName)"
                    >




                        Save
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>


</template>

<script>

    export default {
        name: 'Popup',
        props: ['areaname', 'areaid', 'area'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{

            addNewItem(newname) {

                //let _this = this;

                alert('Add New Item' + newname)



                this.dialog = false

            },





        }

    }
</script>
