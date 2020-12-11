<template>


    <v-container class="my-5">

        <v-layout pa-1 row wrap>
            <v-flex md6 pl-3>
                <h2 style="float: left;color: #0071bc">Area of Interests </h2>
                <pop-aoi-new  @update="getAreaOfInterest()" style="float: left; margin-left: 10px;margin-top:5px"></pop-aoi-new>
            </v-flex>

        </v-layout>


        <v-card flat class="ma-0 pa-0" v-for="(area, index)  in areaofint" :key="area.id">
            <!-- Area of Interest start -->



            <v-layout class="pa-1" row wrap>
                <v-flex md9 class="pl-3" >
                    <h6 style="color: #0071bc">Area of Interest Name</h6>
                    <div style="font-size: 20px">{{ area.name }} </div>
                </v-flex>

                <v-flex md1 pt-2>

                    <div >
                        <pop-aoi-edit  @update="getAreaOfInterest()" v-bind:areaname="area.name"
                                       v-bind:areaid="area.id"
                                       v-bind="areaofint"
                                       right

                    ></pop-aoi-edit></div>
                </v-flex>
                <v-flex md1  pt-2>
                    <div>
                        <pop-aoi-delete @update="getAreaOfInterest()" v-bind:areaname="area.name"
                                        v-bind:areaid="area.id"
                        ></pop-aoi-delete>
                    </div>
                </v-flex>
                <v-flex md1  pl-5 pt-1>
                    <v-img  v-show="!expandAoiArray.includes(index)"

                            alt="Expand Area Of Interests"
                            width="31"
                            :src="require('@/assets/mdi/expand_more-24px.svg')"
                            @click = "expandAOI(index, expandAoiArray)"

                            >


                    </v-img>
                    <v-img  v-show="expandAoiArray.includes(index)"

                            alt="Hide expansion"
                            width="31"
                            :src="require('@/assets/mdi/expand_less-24px.svg')"
                            @click = "expandAOI(index, expandAoiArray)"

                    >


                    </v-img>

                </v-flex>
            </v-layout>
            <v-divider></v-divider>


            <div v-show="expandAoiArray.includes(index)">
                <v-layout class="pa-1" row wrap >

                    <v-flex md9 pl-15>
                        <h3 style="float: left;color: #0071bc">Items</h3>
                        <pop-item-new  @update="getAreaOfInterest()" v-bind:area="area" style="float: left; margin-left: 10px;margin-top:5px"></pop-item-new>
                    </v-flex>

                </v-layout>

                <v-divider></v-divider>


                <v-layout class="pa-1" row wrap >

                    <v-flex md9 pl-15>
                        <div class="caption grey--text">Item Name</div>
                        <div> item name</div>
                    </v-flex>
                    <v-flex md1>


                        <div>
                            <pop-aoi-edit v-bind:areaname="area.name"
                                          v-bind:areaid="area.id"
                                          v-bind="areaofint"

                            ></pop-aoi-edit></div>
                    </v-flex>
                    <v-flex md1>
                        <div>
                            <pop-aoi-delete v-bind:areaname="area.name"
                                            v-bind:areaid="area.id"
                            ></pop-aoi-delete>
                        </div>
                    </v-flex>
                    <v-flex md1>



                    </v-flex>
                </v-layout>
                <v-divider></v-divider>

            </div>

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
    import addItem from '@/components/dashboard/areaofinterest/items/newItemPopup.vue'




    export default {
        name: 'CreateAreaOfInterest',
        components:{
            'pop-aoi-edit' : editAOI,
            'pop-aoi-delete' : deleteAOI,
            'pop-aoi-new' : addAOI,
            'pop-item-new' : addItem,

        },
        data: function() {
        return {
                area_of_interest: new area_of_interest(''),
                areaofint: null,
                expand: false,
                areatest: new area_of_interest(''),
                expandsauce: '@/assets/mdi/expand_more-24px.svg',
                expandAoiArray: []



            };

        },
        computed:{
          currentImage: function(){
              return this.expandsauce
          }


        },
        methods:
                {
                    expandAOI(index, expandAoiArray){

                        //if already expanded, remove expand
                        if( expandAoiArray.includes(index) ){
                            for( var i = 0; i < expandAoiArray.length; i++){
                                if (expandAoiArray[i] === index){
                                    expandAoiArray = expandAoiArray.splice(i,1)

                                }
                            }

                        }else{
                            expandAoiArray.push(index);

                        }


                    },

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

    .container {
        max-width: 1100px;
    }

</style>
