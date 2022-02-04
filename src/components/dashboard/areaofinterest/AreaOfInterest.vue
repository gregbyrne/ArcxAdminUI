<template>


    <v-container class="my-5">
        <v-layout pa-1 row wrap>

          <v-flex md6 pl-3>
                <h2 style="float: left;color: #0071bc">Area of Interests</h2>
                <pop-aoi-new @success="showSuccessResults"
                             @error="showErrorResults"
                             @update="updatePage()"
                             v-bind:epauserid="epauserid"
                             style="float: left; margin-left: 10px;margin-top:5px"></pop-aoi-new>
            </v-flex>

        </v-layout>

        <p id="resultselem" style="display: none"></p>

        <v-card flat class="ma-0 pa-0" v-for="(area, index)  in areaofint" :key="area.id">
            <!-- Area of Interest start -->


            <v-layout class="pa-1" row wrap>
                <v-flex md9 class="pl-3" >
                    <div style="font-size: 20px">{{ area.name }} </div>
                </v-flex>

                <v-flex md1 pt-2>

                    <div >
                        <pop-aoi-edit  @update="updatePage()"
                                       @success="showSuccessResults"
                                       @error="showErrorResults"
                                       v-bind:areaname="area.name"
                                       v-bind:areaid="area.id"
                                       v-bind:epauserid="epauserid"
                                       v-bind="areaofint"
                                       right

                    ></pop-aoi-edit></div>
                </v-flex>
                <v-flex md1  pt-2>
                    <div>
                        <pop-aoi-delete @update="updatePage()"
                                        @success="showSuccessResults"
                                        @error="showErrorResults"
                                        v-bind:areaname="area.name"
                                        v-bind:epauserid="epauserid"
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
                        <pop-item-new @success="showSuccessResults"
                                      @error="showErrorResults"
                                      @update="updatePage()"
                                      v-bind:epauserid="epauserid"
                                      v-bind:area="area" style="float: left; margin-left: 10px;margin-top:5px"></pop-item-new>
                    </v-flex>

                </v-layout>

                <v-divider></v-divider>


                <v-layout class="pa-1" row wrap  v-for="(item, itemIndex)  in aoiitems" :key="item.id" v-show="area.id == item.parentid">

                    <v-flex sm6 md9 pl-15>
                        <div class="caption grey--text">Item Name</div>
                        <div>{{item.name }}</div>

                    </v-flex>


                    <v-flex md1>


                        <div>
                            <pop-item-edit
                                    @success="showSuccessResults"
                                    @error="showErrorResults"
                                    @update="updatePage()"
                                    v-bind:area="area"
                                    v-bind:item="item"
                                    v-bind:epauserid="epauserid"
                            ></pop-item-edit></div>
                    </v-flex>
                    <v-flex md1>
                        <div>
                            <pop-item-delete
                                    @success="showSuccessResults"
                                    @error="showErrorResults"
                                    @update="updatePage()"
                                    v-bind:item="item"
                                    v-bind:epauserid="epauserid"
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
                                    <pop-sub-item-new @success="showSuccessResults"
                                                      @error="showErrorResults"
                                                      @update="updatePage()"
                                                      v-bind:item="item"
                                                      v-bind:epauserid="epauserid"
                                                      style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-new>
                                </v-col>

                            </v-row>

                            <v-row  no-gutters v-for="(subItem, subItemIndex)  in subitems" :key="subItemIndex" v-show="item.id == subItem.parentid" style="padding-bottom:5px">
                                <v-col cols = "1"></v-col>

                                <v-col cols="8">
                                    <div class="caption grey--text">Sub Item Name </div>
                                    <div>{{subItem.name }}</div>
                                </v-col>

                                <v-col cols="1" style="padding-left:6px">
                                    <pop-sub-item-edit @success="showSuccessResults"
                                                       @error="showErrorResults"
                                                       @update="updatePage()"
                                                       v-bind:subItem="subItem"
                                                       v-bind:epauserid="epauserid"
                                                       style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-edit>
                                </v-col>
                                <v-col cols="1">
                                    <pop-sub-item-delete @success="showSuccessResults"
                                                         @error="showErrorResults"
                                                         @update="updatePage()"
                                                         v-bind:subItem="subItem"
                                                         v-bind:epauserid="epauserid"
                                                         style="float: left; margin-left: 10px;margin-top:5px"></pop-sub-item-delete>
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

    const AOI_URL = process.env.VUE_APP_API_AREA_OF_INTEREST;
    const AOI_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_ITEMS;
    const AOI_SUB_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEMS;

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
                maptest: null,
                epauserid : null,



            };

        },
        methods:
                {
                    logOut() {

                    },
                    getNewCode(){


                    },
                  resetResultsElem()
                  {
                    setTimeout(function() {
                      document.getElementById("resultselem").style.display = "none";
                    }, 3000)
                  },

                  showSuccessResults(message)
                  {
                    document.getElementById("resultselem").style.display = "block";
                    document.getElementById("resultselem").textContent= message;
                    document.getElementById("resultselem").style.color = 'darkgreen';
                    this.resetResultsElem()
                  },

                  showErrorResults(message)
                  {
                    document.getElementById("resultselem").style.display = "block";
                    document.getElementById("resultselem").textContent= message;
                    document.getElementById("resultselem").style.color = 'darkred';
                    this.resetResultsElem()
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


                    //New GET request
                    getAreaOfInterest()
                    {

                      jQuery.ajaxSetup({
                            headers : {
                                'Content-Type': 'application/json',
                                'Authorization': 'Bearer ',
                                'userid' : this.epauserid

                            }
                        });

                        var _this = this;

                        var jsonData = jQuery.getJSON(AOI_URL, function (areaofint) {
                            _this.areaofint = areaofint;

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
                                'Authorization': 'Bearer',
                                'userid' : this.epauserid
                            }
                        });

                        var _this = this;

                        jQuery.getJSON(AOI_ITEMS_URL, function (aoiitems) {
                            _this.aoiitems = aoiitems;



                        });

                    },
                    getAreaOfInterestSubItem(){

                        jQuery.ajaxSetup({
                            headers : {
                                'Content-Type': 'application/json',
                                'Authorization': 'Bearer ',
                                'userid' : this.epauserid
                            }
                        });

                        var _this = this;

                        jQuery.getJSON(AOI_SUB_ITEMS_URL, function (subitems) {
                            _this.subitems = subitems;

                        });

                    },

                    updatePage(){
                        this.getAreaOfInterest()
                        this.getAreaOfInterestItem()
                        this.getAreaOfInterestSubItem()
                    }

                },
            created() {
                this.getUserId()

              var that = this;
              setTimeout(function() {
                that.getAreaOfInterest()
                that.getAreaOfInterestItem()
                that.getAreaOfInterestSubItem()
                }, this.$waittime);


            }
        }
</script>
<style>

    .container {
        max-width: 1100px;
    }

</style>
