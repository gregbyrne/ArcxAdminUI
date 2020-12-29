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
                    <span class="headline">Delete Item</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        v-model="item.name"
                                        label="Item to delete"
                                        required
                                        readonly
                                ></v-text-field>
                            </v-col>


                        </v-row>
                    </v-container>
                    Are you sure you want to delete {{item.name}}? This will also delete all sub-items associated with the area.
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
                            @click="deleteItem(item.id)"
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


    export default {
        name: 'Popup',
        aoeName: 'default',
        props: ['item'],


        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,

        }),
        methods:{

            //DELETE
            deleteItem(itemid)
            {
                let _this = this;

                let newURL = 'http://localhost:7100/api/delete_item?item_id=';

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                // eslint-disable-next-line no-console
                console.log("URL: " + newURL + itemid)

                axios.delete(newURL + itemid,{ 'headers': headers})
                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('update')
                            alert('Item has been deleted');
                        }
                        else
                        {
                            alert('Item was not deleted');
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
