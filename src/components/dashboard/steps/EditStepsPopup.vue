<template>
    <v-row justify="center" style="padding-left:10px;">

        <v-dialog
                v-model="dialog"

                fullscreen
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
                <v-toolbar
                        dark
                        color="primary"
                >
                    <v-btn
                            icon
                            dark
                            @click="dialog = false"
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Steps to Help Prepare Settings</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn
                                dark
                                text
                                @click="editStep(step)"
                        >
                            Save
                        </v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-list
                        three-line
                        subheader
                >
                    <v-subheader>Step to Help Prepare Settings</v-subheader>


                    <v-list-item>
                        <v-text-field
                                v-model="step.name"
                                label="Step To Help Prepare Name*"
                                required
                        ></v-text-field>

                    </v-list-item>

                    <v-list-item>
                        <v-text-field
                                v-model="step.subTitle"
                                label="Step To Help Prepare Subtitle"
                                required
                        ></v-text-field>

                    </v-list-item>


                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>Description</v-list-item-title>
                            <v-list-item-subtitle>Set the text that will show with each step. This will show at the top of the Step to Help Prepare on the results page once, at the top. This text allows for HTML to be used. Such as for making lists and links</v-list-item-subtitle>

                            <v-btn
                                    color="primary"
                                    dark
                                    small
                                    v-bind="attrs"
                                    v-on:click="addLinkHTML()"
                                    class="descButton"
                            >
                                Add Link
                            </v-btn>
                            <v-btn
                                    color="primary"
                                    dark
                                    small
                                    v-bind="attrs"
                                    v-on:click="addListItem()"
                                    class="descButton"

                            >
                                Add List
                            </v-btn>
                            <v-btn
                                    color="primary"
                                    class="descButton"

                                    dark
                                    small
                                    v-bind="attrs"
                                    v-on:click="addImage()"

                            >
                                Add Image
                            </v-btn>
                            <v-btn
                                    color="primary"
                                    dark
                                    small
                                    v-bind="attrs"
                                    v-on:click="addTable()"
                                    class="descButton"

                            >
                                Add Table
                            </v-btn>

                            <v-textarea
                                    v-model="step.description"
                                    clearable
                                    outlined
                            ></v-textarea>

                            <p>HTML Preview. The HTML code can contain styling, but the preview does not include the styling included on the search results page.
                                <br/>
                                <br/><span v-html="step.description"></span></p>


                        </v-list-item-content>
                    </v-list-item>






                </v-list>
                <v-divider></v-divider>
                <v-list
                        three-line1
                        subheader
                >
                    <v-subheader></v-subheader>

                </v-list>
            </v-card>
        </v-dialog>
    </v-row>


</template>

<style>
    .descButton{
        max-width: 95px;
        margin-right: 25px;
    }


</style>

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


            editStep( step ){
                let _this = this;


                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(STEPS_URL + step.id,{ name: step.name, id: step.id, subTitle: step.subTitle, description: step.description}, {'headers': headers} )
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


            },addLinkHTML(){
                let precontent = this.step.description
                if(precontent == null){
                    precontent = ''
                }
                let link = "<a href = 'https://www.google.com' > defaulttest url </a>"

                this.step.description = precontent + link





            },
            addListItem(){
                let precontent = this.step.description

                if(precontent == null){
                    precontent = ''
                }

                let list = "<ul>" +
                    "\n<li>item 1</li>" +
                    "\n<li>item 2</li>" +
                    "\n<li>item 3</li>" +
                    "\n</ul>"

                this.step.description = precontent + list


            },addImage(){

                let precontent = this.step.description
                if(precontent == null){
                    precontent = ''
                }
                let image = "<span class='figure image file file-image file-image-png right view-mode-full' " +
                    "style='width:300px;'><img alt='CREAT Climate Scenarios Projection Map' " +
                    "height='169' width='300' class='right media-element file-full' " +
                    "src='https:/www.epa.gov//sites/production/files/styles/large/public/2016-08/final_climate_projection_map_snapshot_-_2080_warm_scenarios_lower_48.png'></span>"



                this.step.description = precontent + image


            },addTable(){

                let precontent = this.step.description
                if(precontent == null){
                    precontent = ''
                }
                let table = '<table><thead>' +
                    '\n<tr><th scope="col">State</th>' +
                    '\n<th scope="col">Climate Change Adaptation Website, if available</th>' +
                    '\n<th scope="col" style="width:20%">EPA Region</th>' +
                    '\n</tr></thead>' +
                    '\n<tbody><tr>' +
                    '\n<td>Alabama</td>' +
                    '\n<td>No State Level Climate Adaptation Website Currently Identified</td>' +
                    '\n<td>Region 4</td>' +
                    '\n</tr>' +
                    '\n<tr><td>Arkansas</td>' +
                    '\n<td>No State Level Climate Adaptation Website Currently Identified</td>' +
                    '\n<td>Region 6</td>' +
                    '\n</tr>'





                this.step.description = precontent + table


            },




        },
        computed:{

        }

    }
</script>
