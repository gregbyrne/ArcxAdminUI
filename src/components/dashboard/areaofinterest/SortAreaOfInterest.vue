<template>

    <v-container class="my-5">

      <v-layout pa-1 row wrap>
        <v-flex md6 pl-3>
          <h2 style="float: left;color: #0071bc">Area of Interests Sorting</h2>
        </v-flex>
        <v-flex md6 pl-3>
        <v-btn
            color="primary"
            dark
            small
            v-bind="attrs"
            v-on="on"
            class="saveButton"
            @click="saveOrder"
        >
          Save
        </v-btn>
        </v-flex>

      </v-layout>

      <draggable v-model="areaofint" ghost-class="ghost" @end="onEnd">
        <transition-group type="transition" name="flip-list">
          <div class="sortable" v-for="aoi in areaofint" :key="aoi.id">
            {{aoi.name}}
            <draggable v-model="aoiitems" ghost-class="ghost" @end="onEnd">
              <transition-group type="transition" name="flip-list">
                <div class="sortable" v-for="aoiitem in aoiitems" v-if="aoi.id == aoiitem.parentid" :key="aoiitem.id">
                  {{aoiitem.name}}
                  <draggable v-model="subitems" ghost-class="ghost" @end="onEnd">
                    <transition-group type="transition" name="flip-list">
                      <div class="sortable" v-for="subitem in subitems" v-if="aoiitem.id == subitem.parentid" :key="subitem.id">
                        {{subitem.name}}
                      </div>
                    </transition-group>
                  </draggable>
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

              //New GET request
              getAreaOfInterest() {
                jQuery.ajaxSetup({
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                  }
                });

                var _this = this;

                var jsonData = jQuery.getJSON(AOI_URL, function (areaofint) {
                  _this.areaofint = areaofint._embedded.area_of_interest;

                });

                jsonData.fail(function (data) {
                  if (data.status == '401') {
                    _this.logOut()
                  }
                })
              },
              getAreaOfInterestItem() {

                jQuery.ajaxSetup({
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                  }
                });

                var _this = this;

                jQuery.getJSON(AOI_ITEMS_URL, function (aoiitems) {
                  _this.aoiitems = aoiitems._embedded.area_of_interest_items;


                });

              },
              getAreaOfInterestSubItem() {

                jQuery.ajaxSetup({
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                  }
                });

                var _this = this;

                jQuery.getJSON(AOI_SUB_ITEMS_URL, function (subitems) {
                  _this.subitems = subitems._embedded.area_of_interest_sub_items;


                });

              },
              updatePage() {
                this.checkStatusOfAccessToken()
                this.getAreaOfInterest()
                this.getAreaOfInterestItem()
                this.getAreaOfInterestSubItem()
              },
              saveOrder() {
                let _this = this;

                const headers = {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(API_URL + AOI_URL + areaid,{ name: changedName}, {'headers': headers} )
                    .then(function (response) {
                      if (response.status.toString().includes("20")) {
                        alert('Area of Interest has been edited');
                      }
                      else
                      {
                        alert('Area of Interest was NOT edited');
                      }
                    })
                    .catch((error) => {
                      alert('ERROR: with edit ' + error);
                    });

                this.dialog = false
                this.updatePage()
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
    .saveButton
    {
      float: right;
      margin-right: 10px;
    }

</style>
