<template>


    <v-container class="my-5">
        <div><h1>Area of Interests</h1> <span><pop-aoi-new @update="getAreaOfInterest()"></pop-aoi-new></span> </div>

        <v-card flat class="pa-1" v-for="area in areaofint" :key="area.id">
            <!-- Area of Interest start -->


            <v-layout class="pa-1" row wrap>
                <v-flex xs18 md8 class="pl-3">
                    <div class="caption grey--text">Area of Interest Name</div>
                    <div>{{ area.name }}</div>
                </v-flex>
                <vflex xs1 md1> <v-divider vertical></v-divider></vflex>
                <v-flex xs3 sm4 md1>


                    <div>
                        <pop-aoi-edit  @update="getAreaOfInterest()" v-bind:areaname="area.name"
                                       v-bind:areaid="area.id"
                                       v-bind="areaofint"

                    ></pop-aoi-edit></div>
                </v-flex>
                <v-flex xs3 sm4 md1>
                    <div>
                        <pop-aoi-delete v-bind:areaname="area.name"
                                        v-bind:areaid="area.id"
                        ></pop-aoi-delete>
                    </div>
                </v-flex>
                <v-flex xs2 sm4 md1>
                    <v-btn
                            @click="expand = !expand"
                            small
                    >
                        expand

                    </v-btn>




                </v-flex>
            </v-layout>

            <v-divider></v-divider>

            <div v-show="expand">
                <v-layout class="pa-1" row wrap >

                    <v-flex md7 pl-5>
                        <div class="caption grey--text">Item Name</div>
                        <div> item name</div>
                    </v-flex>
                    <vflex xs1 md1> <v-divider vertical></v-divider></vflex>
                    <v-flex xs3 sm4 md1>


                        <div>
                            <pop-aoi-edit v-bind:areaname="area.name"
                                          v-bind:areaid="area.id"
                                          v-bind="areaofint"

                            ></pop-aoi-edit></div>
                    </v-flex>
                    <v-flex xs3 sm4 md1>
                        <div>
                            <pop-aoi-delete v-bind:areaname="area.name"
                                            v-bind:areaid="area.id"
                            ></pop-aoi-delete>
                        </div>
                    </v-flex>
                    <v-flex xs2 sm4 md1>



                    </v-flex>
                </v-layout>
            </div >

            <!-- ITEM end -->
        </v-card>


     </v-container>

</template>

<script>

    const API_URL = process.env.VUE_APP_API_URL;

    import axios from "axios";
    import jQuery from "jquery";
    import area_of_interest from "@/models/area_of_interest";
    import editAOI from '@/components/dashboard/areaofinterest/editAoiPopup.vue'
    import deleteAOI from '@/components/dashboard/areaofinterest/deleteAoiPopup.vue'
    import addAOI from '@/components/dashboard/areaofinterest/newAoiPopup.vue'
    import expandmore from '@/assets/mdi/expand_more-24px.svg'




    export default {
        name: 'CreateAreaOfInterest',
        components:{
            'pop-aoi-edit' : editAOI,
            'pop-aoi-delete' : deleteAOI,
            'pop-aoi-new' : addAOI,

        },
        data: function() {
        return {
                area_of_interest: new area_of_interest(''),
                areaofint: null,
                expand: false,
                icons: {
                    expandmore,

            },

            };

        },
        methods:
                {

                    //PUT

                    putAreaOfInterest(area){
                        alert("edit started id =  " + area.id)

                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }

                        axios.put(API_URL +'area_of_interest/' + area.id,{ name: "edited"}, {'headers': headers} )
                            .then(function (response) {
                                if (response.status == 204) {
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



                    },

                    //DELETE
                    deleteAreaOfInterest(area)
                    {
                        alert("delete started id =  " + area.id)

                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }

                        axios.delete(API_URL +'area_of_interest/' + area.id,{ 'headers': headers})
                            .then(function (response) {
                                if (response.status == 204) {
                                    alert('Area of Interest has been deleted');
                                }
                                else
                                {
                                    alert('Area of Interest was not deleted');
                                }
                            })
                                .catch((error) => {
                                    alert('ERROR: with delete ' + error);
                                });


                    },
                    //New POST request
                    addNewAreaOfInterest()
                    {

                        let _this = this;


                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }

                        axios.post('http://localhost:7100/api/area_of_interest',
                            this.area_of_interest, { 'headers': headers})
                            .then(function (response) {
                                if (response.status == 201) {
                                    _this.getAreaOfInterest()
                                    alert('Area of Interest has been created');
                                }
                                else
                                {
                                    alert('Area of Interest was not created');
                                }
                            })
                            .catch((error) => {
                                alert('ERROR: ' + error);
                            })
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
                    }





                },
            created() {
                this.getAreaOfInterest()
            }
        }
</script>
<style>

    .styled {

        border: 0;
        line-height: 2.5;
        padding: 0 20px;
        margin-top: 10px;
        font-size: 1rem;
        text-align: center;
        color: #fff;
        text-shadow: 1px 1px 1px #000;
        border-radius: 10px;
        background-color: #0071bc;
        background-image: linear-gradient(to top left,
        rgba(0, 0, 0, .2),
        rgba(0, 0, 0, .2) 30%,
        rgba(0, 0, 0, 0));
        box-shadow: inset 2px 2px 3px rgba(255, 255, 255, .6),
        inset -2px -2px 3px rgba(0, 0, 0, .6);
    }

    .styled:hover {
        background-color: #2d9be3;
    }

    .styled:active {
        box-shadow: inset -2px -2px 3px rgba(255, 255, 255, .6),
        inset 2px 2px 3px rgba(0, 0, 0, .6);
    }

</style>
