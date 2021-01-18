<template>

    <v-container class="my-5">

      <draggable v-model="areaofint" ghost-class="ghost" @end="onEnd">
        <transition-group type="transition" name="flip-list">
          <div class="sortable" v-for="aoi in areaofint" :key="aoi.id">
            {{aoi.name}}
            <draggable v-model="aoiitems" ghost-class="ghost" @end="onEnd">
              <transition-group type="transition" name="flip-list">
                <div class="sortable" v-for="aoiitem in aoiitems" v-if="aoi.id == aoiitem.parentid" :key="aoiitem.id">
                  {{aoiitem.name}}
                </div>
              </transition-group>
            </draggable>
          </div>
        </transition-group>
        </draggable>

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

                  onEnd(evt) {
                    console.log(evt)
                    this.oldIndex = evt.oldIndex;
                    this.newIndex = evt.newIndex;
                  },
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
