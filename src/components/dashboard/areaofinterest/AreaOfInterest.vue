<template>

    <v-container class="my-5">

        <v-layout pa-1 row wrap>
            <v-flex md6 pl-3>
                <h2 style="float: left;color: #0071bc">Area of Interests  </h2>
                <pop-aoi-new  @update="updatePage()" style="float: left; margin-left: 10px;margin-top:5px"></pop-aoi-new>
            </v-flex>

        </v-layout>

        <v-card flat class="ma-0 pa-0" v-for="(area, index)  in areaofint" :key="area.id" v-sortable>
            <!-- Area of Interest start -->

            <v-layout class="pa-1" row wrap>
                <v-flex md9 class="pl-3" >
                    <div style="font-size: 20px">{{ area.name }} </div>
                </v-flex>

                <v-flex md1 pt-2>

                    <div >
                        <pop-aoi-edit  @update="updatePage()" v-bind:areaname="area.name"
                                       v-bind:areaid="area.id"
                                       v-bind="areaofint"
                                       right

                    ></pop-aoi-edit></div>
                </v-flex>
                <v-flex md1  pt-2>
                    <div>
                        <pop-aoi-delete @update="updatePage()" v-bind:areaname="area.name"
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

            <!-- ITEMS -->

            <div v-if="expandAoiArray.includes(index)">
                <v-layout class="pa-1" row wrap >

                    <v-flex md9 pl-15>
                        <h3 style="float: left;color: #0071bc">Items</h3>
                        <pop-item-new  @update="updatePage()" v-bind:area="area" style="float: left; margin-left: 10px;margin-top:5px"></pop-item-new>
                    </v-flex>

                </v-layout>

                <v-divider></v-divider>


                <v-layout class="pa-1" row wrap  v-for="(item, itemIndex)  in aoiitems" :key="item.id" v-show="area.id == item.parentid">

                    <v-flex sm6 md9 pl-15>
                        <div class="caption grey--text">Item Name </div>
                        <div>{{item.name }}</div>
                    </v-flex>
                    <v-flex md1>


                        <div>
                            <pop-item-edit
                                    @update="updatePage()"
                                    v-bind:area="area"
                                    v-bind:item="item"
                            ></pop-item-edit></div>
                    </v-flex>
                    <v-flex md1>
                        <div>
                            <pop-item-delete
                                    @update="updatePage()"
                                    v-bind:item="item"
                            ></pop-item-delete>
                        </div>
                    </v-flex>
                    <v-flex md1>
                        <v-img  v-show="!expandItemArray.includes(itemIndex)"

                                alt="Expand Items"
                                width="31"
                                :src="require('@/assets/mdi/expand_more-24px.svg')"
                                @click = "expandAOI(itemIndex, expandItemArray)"

                        >


                        </v-img>
                        <v-img  v-show="expandItemArray.includes(itemIndex)"

                                alt="Hide Item expansion"
                                width="31"
                                :src="require('@/assets/mdi/expand_less-24px.svg')"
                                @click = "expandAOI(itemIndex, expandItemArray)"

                        ></v-img>


                    </v-flex>


                    <!-- SUB ITEMS start -->

                    <v-row v-if="expandItemArray.includes(itemIndex)">
                        <v-col cols="12">

                            <v-row  no-gutters style="padding-bottom:10px">
                                <v-col cols="1"></v-col>

                                <v-col cols="10">
                                    <h4 style="float: left;color: #0071bc">Sub-Items</h4>
                                    <pop-sub-item-new  @update="updatePage()" v-bind:item="item" style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-new>
                                </v-col>

                            </v-row>

                            <v-row  no-gutters v-for="(subItem, subItemIndex)  in subitems" :key="subItemIndex" v-show="item.id == subItem.parentid" style="padding-bottom:5px">
                                <v-col cols = "1"></v-col>

                                <v-col cols="8">
                                    <div class="caption grey--text">Sub Item Name </div>
                                    <div>{{subItem.name }}</div>
                                </v-col>

                                <v-col cols="1" style="padding-left:6px">
                                    <pop-sub-item-edit  @update="updatePage()" v-bind:subItem="subItem" style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-edit>
                                </v-col>
                                <v-col cols="1">
                                    <pop-sub-item-delete  @update="updatePage()" v-bind:subItem="subItem" style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-delete>
                                </v-col>

                            </v-row>



                        </v-col>
                    </v-row>

                </v-layout>
                <v-divider></v-divider>






                </div>

        </v-card>

     </v-container>

</template>

<script>

    const API_URL = process.env.VUE_APP_API_URL;
    const AOI_URL = process.env.VUE_APP_API_AREA_OF_INTEREST;
    const AOI_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_ITEMS;
    const AOI_SUB_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEMS;

    import axios from "axios";
    import jQuery from "jquery";
    import area_of_interest from "@/models/area_of_interest";
    import area_of_interest_item from "@/models/area_of_interest_item";
    import area_of_interest_sub_item from "@/models/area_of_interest_sub_item";
    import editAOI from '@/components/dashboard/areaofinterest/editAoiPopup.vue'
    import deleteAOI from '@/components/dashboard/areaofinterest/deleteAoiPopup.vue'
    import addAOI from '@/components/dashboard/areaofinterest/newAoiPopup.vue'
    import addItem from '@/components/dashboard/areaofinterest/items/newItemPopup.vue'
    import editItem from '@/components/dashboard/areaofinterest/items/editItemPopup.vue'
    import deleteItem from '@/components/dashboard/areaofinterest/items/deleteItemPopup.vue'
    import addSubItem from '@/components/dashboard/areaofinterest/subitems/newSubItemPopup.vue'
    import editSubItem from '@/components/dashboard/areaofinterest/subitems/editSubItemPopup.vue'
    import deleteSubItem from '@/components/dashboard/areaofinterest/subitems/deleteSubItemPopup.vue'
    import draggable from 'vuedraggable'

    export default {
        name: 'CreateAreaOfInterest',
        components:{
            'pop-aoi-edit' : editAOI,
            'pop-aoi-delete' : deleteAOI,
            'pop-aoi-new' : addAOI,

            'pop-item-new' : addItem,
            'pop-item-edit' : editItem,
            'pop-item-delete' : deleteItem,

            'pop-sub-item-new' : addSubItem,
            'pop-sub-item-edit' : editSubItem,
            'pop-sub-item-delete' : deleteSubItem,
            draggable,

        },
        data: function() {
        return {
                area_of_interest: new area_of_interest(''),
                area_of_interest_item: new area_of_interest_item(''),
                area_of_interest_sub_item: new area_of_interest_sub_item(''),
                areaofint: null,
                aoiitems: null,
                subitems: null,
                expand: false,
                expandAoiArray: [],
                expandItemArray: [],
                oldIndex: '',
                newIndex: ''
            };

        },
        computed:{
          currentImage: function(){
              return this.expandsauce
          }


        },
        methods:
                {
                    logOut() {

                        this.$store.dispatch('auth/logout');
                        this.$router.push('/login');

                    },
                    checkStatusOfAccessToken() {

                        if (this.$store.state.auth.user == '' || this.$store.state.auth.user == null)
                        {
                            this.$store.dispatch('auth/logout');
                            this.$router.push('/login');
                        }

                    },
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


                    },expandItem(itemIndex, expandItemArray){

                        //if already expanded, remove expand
                        if( expandItemArray.includes(itemIndex) ){
                            for( var i = 0; i < expandItemArray.length; i++){
                                if (expandItemArray[i] === itemIndex){
                                    expandItemArray = expandItemArray.splice(i,1)

                                }
                            }

                        }else{
                            expandItemArray.push(itemIndex);

                        }


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
                                if (response.status.toString().includes("20")) {
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

                        var jsonData = jQuery.getJSON(AOI_URL, function (areaofint) {
                            _this.areaofint = areaofint._embedded.area_of_interest;

                         });

                        jsonData.fail(function(data) {
                            if (data.status == '401')
                            {
                                _this.logOut()
                            }
                        })
                    },
                    getAreaOfInterestItem(){

                        jQuery.ajaxSetup({
                            headers : {
                                'Content-Type': 'application/json',
                                'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                            }
                        });

                        var _this = this;

                        jQuery.getJSON(AOI_ITEMS_URL, function (aoiitems) {
                            _this.aoiitems = aoiitems._embedded.area_of_interest_items;



                        });

                    },
                    getAreaOfInterestSubItem(){

                        jQuery.ajaxSetup({
                            headers : {
                                'Content-Type': 'application/json',
                                'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                            }
                        });

                        var _this = this;

                        jQuery.getJSON(AOI_SUB_ITEMS_URL, function (subitems) {
                            _this.subitems = subitems._embedded.area_of_interest_sub_items;



                        });

                    },
                    updatePage(){
                        this.checkStatusOfAccessToken()
                        this.getAreaOfInterest()
                        this.getAreaOfInterestItem()
                        this.getAreaOfInterestSubItem()
                    }





                },
            created() {
                this.checkStatusOfAccessToken()
                this.getAreaOfInterest()
                this.getAreaOfInterestItem()
                this.getAreaOfInterestSubItem()

            }
        }
</script>
<style>

    .container {
        max-width: 1100px;
    }
    .sortable {
      width: 100%;
      background: white;
      padding: 1em;
      cursor: move;
      margin-bottom: 2px;
    }

</style>
