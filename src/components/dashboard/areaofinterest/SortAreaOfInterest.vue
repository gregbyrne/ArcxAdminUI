
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
            class="saveButton"
            @click="saveOrder"
        >
          Save
        </v-btn>
      </v-flex>

    </v-layout>

    <div id="resultselem"></div>
    <draggable v-model="areaofint" id="startelement" class="mainDraggable" ghost-class="ghost">
      <transition-group type="transition" name="flip-list">
        <div class="sortable aoi sortitem" name="aois" :id="aoi.id" v-for="aoi in areaofint" :key="aoi.id">
          {{aoi.name}}
          <img id="itemimagedown" @click="displayHideItems($event.target)" :src="expandMoreIcon" style="float: right; cursor: pointer">
          <draggable v-model="aoiitems" ghost-class="ghost">
            <transition-group type="transition" name="flip-list">
              <div class="sortable item" name="items" :id="aoiitem.id" v-for="aoiitem in aoiitems" :key="aoiitem.id" style="display: none">
                <div class ="sortitem itemsort divitem" :id="aoiitem.id" v-bind:class="aoiitem.id" v-if="aoi.id == aoiitem.parentid" >
                  {{aoiitem.name}}
                  <img id="subimagedown" @click="displayHideSubItems($event.target)" :src="expandMoreIcon" style="float: right; cursor: pointer">
                  <draggable v-model="subitems" ghost-class="ghost">
                    <transition-group type="transition" name="flip-list">

                      <div class="sortable  sub " name="subs"  :id="subitem.id" v-for="subitem in subitems" :key="subitem.id" style="display: none">
                        <div class ="sortitem subitemsorting" :id="subitem.id" v-if="aoiitem.id == subitem.parentid">
                          {{subitem.name}}
                        </div>

                      </div>
                    </transition-group>
                  </draggable>
                </div>
              </div>
            </transition-group>
          </draggable>
        </div>
      </transition-group>
    </draggable>


  </v-container>

</template>

<script>

const AOI_URL = process.env.VUE_APP_API_AREA_OF_INTEREST;
const AOI_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_ITEMS;
const AOI_SUB_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEMS;

import expandMore from "../../../assets/mdi/expand_more-24px.svg";
import expandLess from "../../../assets/mdi/expand_less-24px.svg";

import axios from "axios";
import jQuery from "jquery";
import area_of_interest from "@/models/area_of_interest";
import area_of_interest_item from "@/models/area_of_interest_item";
import area_of_interest_sub_item from "@/models/area_of_interest_sub_item";
import draggable from 'vuedraggable'

export default {
  name: 'CreateAreaOfInterest',
  components:{
    // eslint-disable-next-line vue/no-unused-components
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
      expandMoreIcon: expandMore,
      expandLessIcon: expandLess,
      epauserid: null,
      userip: null

    };

  },
  methods:
      {
        logOut() {

        },

        //New GET request
        getAreaOfInterest() {
          jQuery.ajaxSetup({
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' ,
              'userid' : this.epauserid,
              'userip' : this.userip

            }
          });

          var _this = this;

          var jsonData = jQuery.getJSON(AOI_URL, function (areaofint) {
            _this.areaofint = areaofint;

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
              'Authorization': 'Bearer ',
              'userid' : this.epauserid,
              'userip' : this.userip
            }
          });

          var _this = this;

          jQuery.getJSON(AOI_ITEMS_URL, function (aoiitems) {
            _this.aoiitems = aoiitems;


          });

        },
        getAreaOfInterestSubItem() {

          jQuery.ajaxSetup({
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' ,
              'userid' : this.epauserid,
              'userip' : this.userip

            }
          });

          var _this = this;

          jQuery.getJSON(AOI_SUB_ITEMS_URL, function (subitems) {
            _this.subitems = subitems;
          });

        },
        updatePage() {
          this.checkStatusOfAccessToken()
          this.getAreaOfInterest()
          this.getAreaOfInterestItem()
          this.getAreaOfInterestSubItem()
        },
        savePositionRequest(name, url, id, position, parentid) {

          var foundError = false;

                   const headers = {
                     'Content-Type': 'application/json',
                     'Authorization': 'Bearer ' ,
                     'userid' : this.epauserid,
                     'userip' : this.userip

                   }

                   if (parentid == null)
                   {
                     axios.put(url, {id: id, name: name.trim(), position: position}, {'headers': headers})
                         .then(function (response) {
                           // eslint-disable-next-line no-empty
                           if (response.status.toString().includes("20")) {
                           } else {
                             alert('Something went wrong saving the positions');
                           }
                         })
                         .catch((error) => {
                           alert('ERROR: with edit ' + error);
                           foundError = true
                         });
                   }
                   else
                   {
                     axios.put(url, {id: id, name: name, position: position, parentid: parentid}, {'headers': headers})
                         .then(function (response) {
                           // eslint-disable-next-line no-empty
                           if (response.status.toString().includes("20")) {

                           } else {
                             alert('Something went wrong saving the positions');
                           }
                         })
                         .catch((error) => {
                           alert('ERROR: with edit ' + error);
                           foundError = true
                         });
                   }

         return !foundError ? false : true

       },
       saveOrder() {

         var _this = this;

         var foundError = false;

         jQuery( ".mainDraggable" ).last().find(".aoi").toArray().map(function (el, index) {

           //save Area of Interest
           foundError = _this.savePositionRequest(jQuery(el).contents().not(jQuery(el).children()).text(), AOI_URL, el.id, index, null)

           jQuery(el).find(".divitem").toArray().map(function (elem, index) {
             //divitem

             var aoiid = el.id

             //save Item
             foundError = _this.savePositionRequest(jQuery(elem).contents().not(jQuery(elem).children()).text(), AOI_ITEMS_URL, elem.id, index, aoiid)

             jQuery(elem).find(".subitemsorting").toArray().map(function (element, index) {
               var itemid = elem.id
               //save Sub-item

               foundError = _this.savePositionRequest(jQuery(element).contents().not(jQuery(element).children()).text(), AOI_SUB_ITEMS_URL, element.id, index, itemid)

             });

           });

         });

         if (foundError)
         {
           alert("There was an issue saving the area of interest positions. Please try again later.")
         }
         else
         {
           alert("Positions have been saved successfully")
         }
       },
       displayHideItems(elem)
       {

         if (jQuery(elem).attr('id') == "itemimagedown")
         {
           jQuery(elem).attr("id","itemimageup");
           jQuery(elem).attr("src",this.expandLessIcon);
           jQuery(elem).parent().find(".item").css({"display": "block"});
         }
         else
         {
           jQuery(elem).attr("id","itemimagedown");
           jQuery(elem).attr("src",this.expandMoreIcon);
           jQuery(elem).parent().find(".item").css({"display": "none"});
         }

       },
       displayHideSubItems(elem) {

         if (jQuery(elem).attr('id') == "subimagedown")
         {
           jQuery(elem).attr("id","subimageup");
           jQuery(elem).attr("src",this.expandLessIcon);
           jQuery(elem).parent().find(".sub").css({"display": "block"});
         }
         else
         {
           jQuery(elem).attr("id","subimagedown");
           jQuery(elem).attr("src",this.expandMoreIcon);
           jQuery(elem).parent().find(".sub").css({"display": "none"});
         }
       }
     },
 computed: {
   filteredAOIItems: function () {
     var itemsList = this.aoiitems

     for( let i = 0 ; i < this.aoiitems; i++){
       var item = itemsList[i]

       if (item.parentid == item.parentid){
         filtered.push(item)
       }

     }

     var filtered


     return filtered
   }
 },
 created() {
   this.getUserId()

   var that = this;
   setTimeout(function() {
     that.getAreaOfInterest()
     that.getAreaOfInterestItem()
     that.getAreaOfInterestSubItem()
   },  this.$waittime);


 }
}
</script>
<style>


.sortitem{
  padding: 1em;
}

.container {
  max-width: 1100px;
}
.sortable {
  width: 100%;
  background: white;
  cursor: move;
  margin-bottom: 2px;
}
.saveButton
{
  float: right;
  margin-right: 10px;
}

</style>
