<template>
    <v-container>
        <h1 style="text-align: center; color: #0071bc">ARC-X Dashboard</h1>

        <v-container class="mx-6 my-10">
            <v-row>
                <v-col>

                </v-col>
                <v-col>

                </v-col>
            </v-row>
            <v-container class="my-5">

                <v-layout pa-1 row wrap>
                    <v-flex md6 pl-3>
                        <h2 style="float: left;color: #0071bc">Additional Information</h2>
                        <v-btn
                                style="margin-left:14px; margin-top:4px"
                                color="primary"
                                dark
                                small
                                @click="saveAI(description, regionSelect )"
                        >
                            Save
                        </v-btn>
                    </v-flex>


                </v-layout>

                <v-select
                        v-model="regionSelect"
                        :items="regions"
                        item-text="name"
                        item-value="code"
                        label="Region"
                >

                </v-select>
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
                            v-model="description"
                            clearable
                            outlined
                            background-color="white"
                    ></v-textarea>

                    <p>HTML Preview. The HTML code can contain styling, but the preview does not include the styling included on the search results page.
                        <br/><br/>
                        <span v-html="description"></span></p>




            </v-container>



        </v-container>
    </v-container>
</template>

<script>
    import axios from "axios";
    import jQuery from "jquery";
    const REGIONS_URL = process.env.VUE_APP_API_REGIONS_URL;
    const ADDITIONAL_INFO_URL = process.env.VUE_APP_API_ADDITIONAL_INFORMATION_URL;

    export default {
        components : { },
        name: 'Add Steps',
        data: function() {
            return {

                regions: null,
                additionalInfo: [],
                regionSelect: null,
                description: null,


            };

        }, watch: {
            regionSelect: function () {

                //region code -> code

                let AI = this.additionalInfo
                for(let i = 0; i < AI.length; i++){
                    if(this.regionSelect == AI[i].regioncode){
                        this.description = AI[i].description
                    }

                }

            },
        },

        computed: {
            date : function () {
                return Date.now()
            },
            currentUser() {
                return this.$store.state.auth.user
            }
        },
        methods:
            {
                getRegions(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }
                    });

                    var _this = this;

                    jQuery.getJSON(REGIONS_URL, function (regions) {
                        _this.regions = regions._embedded.regions;



                    });

                },
                getAdditionalInfo(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }
                    });

                    var _this = this;

                    jQuery.getJSON(ADDITIONAL_INFO_URL, function (additionalInformation) {
                        _this.additionalInfo = additionalInformation._embedded.additional_information;


                    });

                },addLinkHTML(){
                    let precontent = this.description
                    if(precontent == null){
                        precontent = ''
                    }
                    let link = "<a href = 'https://www.google.com' > defaulttest url </a>"

                    this.description = precontent + link

                },
                addListItem(){
                    let precontent = this.description

                    if(precontent == null){
                        precontent = ''
                    }

                    let list = "<ul>" +
                        "\n<li>item 1</li>" +
                        "\n<li>item 2</li>" +
                        "\n<li>item 3</li>" +
                        "\n</ul>"

                    this.description = precontent + list


                },addImage(){

                    let precontent = this.description
                    if(precontent == null){
                        precontent = ''
                    }
                    let list = "<span class='figure image file file-image file-image-png right view-mode-full' " +
                        "style='width:300px;'><img alt='CREAT Climate Scenarios Projection Map' " +
                        "height='169' width='300' class='right media-element file-full' " +
                        "src='https:/www.epa.gov//sites/production/files/styles/large/public/2016-08/final_climate_projection_map_snapshot_-_2080_warm_scenarios_lower_48.png'></span>"



                    this.description = precontent + list


                },addTable(){

                    let precontent = this.description
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





                    this.description = precontent + table


                },saveAI(description, regionSelect ){
                    let _this = this;

                    //get ID
                    let AI = this.additionalInfo
                    let id = null
                    let region = null
                    for(let i = 0; i < AI.length; i++){
                        if(regionSelect == AI[i].regioncode){
                            this.description = AI[i].description
                            id = AI[i].id
                            region = AI[i].regioncode
                        }

                    }


                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }


                        axios.put(ADDITIONAL_INFO_URL + id ,{ description: description, regioncode:region}, {'headers': headers} )

                            .then(function (response) {
                                if (response.status == 200) {
                                    alert('Additional Information has been edited');

                                }
                                else
                                {
                                    alert('Additional Information was not edited');
                                }
                            })
                            .catch((error) => {
                                alert('ERROR: with edit ' + error);
                            });



                },updatePage(){
                    this.getAdditionalInfo()
                }


            },
        created(){
            this.getRegions()
            this.getAdditionalInfo()


        }
    }
</script>


