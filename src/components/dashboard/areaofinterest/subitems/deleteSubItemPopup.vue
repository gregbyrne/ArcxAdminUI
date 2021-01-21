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
                    <span class="headline">Delete Sub Item</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="subItem.name"
                                        label="Sub Item to delete"
                                        required
                                        readonly
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    Are you sure you want to delete {{subItem.name}}? This will also delete all items and sub-items associated with the area.
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
                            @click="deleteSubItem(subItem.id)"
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
    const AOE_SUB_ITEM_DELETE_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEM_DELETE_URL;


    export default {
        name: 'Popup',
        aoeName: 'default',
        props: [ 'subItem'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{

            //DELETE
            deleteSubItem(subItemId)
            {
                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.delete(AOE_SUB_ITEM_DELETE_URL + subItemId,{ 'headers': headers})
                    .then(function (response) {
                        if (response.status == 204 || response.status == 202) {
                            _this.$emit('update')
                            alert('Sub Item has been deleted');
                        }
                        else
                        {
                            alert('Sub Item was not deleted');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: with delete ' + error);
                    });

                this.dialog = false;



            },



        }

    }
</script>
