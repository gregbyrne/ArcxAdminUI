<template>

    <v-container class="my-5">

      <v-layout pa-1 row wrap>
        <v-flex md6 pl-3>
          <h2 style="float: left;color: #0071bc">Step to Help Prepare Sorting</h2>
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

      <draggable v-model="steps" id="startelement" class="mainDraggable" ghost-class="ghost">
        <transition-group type="transition" name="flip-list">
          <div class="sortable step sortstep" name="aois" :id="step.id" v-for="step in steps" :key="step.id">
            {{step.name}}
          </div>
        </transition-group>
        </draggable>

     </v-container>

</template>

<script>

    const STEPS_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE;


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
            draggable,

        },
        data: function() {
        return {
                area_of_interest: new area_of_interest(''),
                area_of_interest_item: new area_of_interest_item(''),
                area_of_interest_sub_item: new area_of_interest_sub_item(''),
                areaofint: null,
                steps: null,
                 epauserid : null,
                userip : null,
                 aoiitems: null,
                subitems: null,
                expand: false,
                expandAoiArray: [],
                expandItemArray: [],
                expandMoreIcon: expandMore,
                expandLessIcon: expandLess
            };

        },
        methods:
            {
              logOut() {


              },
              checkStatusOfAccessToken() {

              },

              //New GET request
              getStepsToHelp()
              {
                jQuery.ajaxSetup({
                  headers : {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' ,
                    'userid' : this.epauserid,
                    'userip' : this.userip

                  }
                });

                var _this = this;

                var jsonData = jQuery.getJSON(STEPS_URL, function (steps) {
                  _this.steps = steps;

                });

                jsonData.fail(function(data) {
                  if (data.status == '401')
                  {
                    _this.logOut()
                  }
                })
              },

              updatePage() {
                this.getStepsToHelp()

              },
              savePositionRequest(name, url, id, position) {
                const headers = {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' ,
                  'userid' : this.epauserid,
                  'userip' : this.userip
                }
                var steps = this.steps

                var desc = "";
                var subTitle = "";
                var step;

                for(var i = 0; i < steps.length; i++){
                  step = steps[i];
                  if( step.id == id){
                    desc = step.description
                    subTitle = step.subTitle
                  }
                }

                var foundError = false;

                  axios.put(url, {id: id, name: name.trim(), position: position, description: desc, subTitle: subTitle}, {'headers': headers})
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


              return !foundError ? false : true

              },
              saveOrder() {

                var _this = this;

                var foundError = false;

                  jQuery( ".mainDraggable" ).last().find(".step").toArray().map(function (el, index) {

                    foundError = _this.savePositionRequest(jQuery(el).contents().not(jQuery(el).children()).text(), STEPS_URL, el.id, index, null)

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

            },
            created() {
              this.getUserId()

              var that = this;
              setTimeout(function() {
                that.getStepsToHelp()
              },  this.$waittime);

            }
        }
</script>
<style>

    .container {
        max-width: 1100px;
    }
    .sortstep {
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
