<template>


    <v-container class="my-5">
        <v-layout pa-1 row wrap>
            <v-flex md6 pl-3>
                <h2 style="float: left;color: #0071bc">Steps to Help</h2>
                <pop-addSteps @success="showSuccessResults"
                              @error="showErrorResults"
                              @update="updatePage()"
                              v-bind:epauserid="epauserid"
                              v-bind:userip="userip"
                              style="float: left; margin-left: 10px;margin-top:5px"></pop-addSteps>,
            </v-flex>

        </v-layout>

        <p id="resultselem" style="display: none"></p>


        <v-card flat class="ma-0 pa-0" v-for="(step, index)  in steps" :key="step.id">
            <!-- Area of Interest start -->


            <v-row class="pa-1" row wrap>
                <v-col cols="9"  class="pl-3" >
                    <div style="font-size: 20px">{{ step.name }} </div>
                </v-col>

                <v-col cols="1" pt-2>

                    <div >
                        <pop-step-edit @success="showSuccessResults"
                                       @error="showErrorResults"
                                       @update="updatePage()"
                                       v-bind:step="step"
                                       v-bind:epauserid="epauserid"
                                       v-bind:userip="userip"
                                       right

                        ></pop-step-edit></div>
                </v-col>
                <v-col cols="1" pt-2>
                    <div>
                        <pop-step-delete
                            @success="showSuccessResults"
                            @error="showErrorResults"
                            @update="updatePage()"
                            v-bind:step="step"
                            v-bind:epauserid="epauserid"
                            v-bind:userip="userip"
                        ></pop-step-delete>
                    </div>
                </v-col>
                <v-col cols = "1"  pl-5 pt-1  >
                    <v-img  v-show="!expandStepsArray.includes(index)"

                            alt="Expand Area Of Interests"
                            width="31"
                            :src="require('@/assets/mdi/expand_more-24px.svg')"
                            @click = "expandSteps(index, expandStepsArray)"

                    >


                    </v-img>
                    <v-img  v-show="expandStepsArray.includes(index)"

                            alt="Hide expansion"
                            width="31"
                            :src="require('@/assets/mdi/expand_less-24px.svg')"
                            @click = "expandSteps(index, expandStepsArray)"

                    >


                    </v-img>

                </v-col>
            </v-row>

            <div v-if="expandStepsArray.includes(index)">
                <v-divider></v-divider>

                <v-row class="pa-1" row wrap >

                    <v-col cols="6" pt-2 pl-15>
                        <h3 style="float: left;color: #0071bc">Step to Help Prepare Items</h3>
                        <pop-add-step-item @success="showSuccessResults"
                                           @error="showErrorResults"
                                           @update="updatePage()"
                                           v-bind:step="step"
                                           v-bind:epauserid="epauserid"
                                           v-bind:userip="userip"
                                           style="float: left; margin-left: 10px;margin-top:5px"></pop-add-step-item>
                    </v-col>


                </v-row>
                <v-row  v-for="(item)  in stepitems" :key="item.id" v-show="step.id == item.parentid">
                    <v-col cols="1"> </v-col>
                    <v-col cols="8">
                        <div class="caption grey--text">Item Name & Subtitle</div>
                        <div>{{item.name }}<span v-if="item.subTitle != null && item.subTitle != '' "> - {{ item.subTitle }}</span></div>
                    </v-col>
                    <v-col cols="1" ><pop-edit-step-item
                            @success="showSuccessResults"
                            @error="showErrorResults"
                            @update="updatePage()"
                            v-bind:item="item"
                            v-bind:step="step"
                            v-bind:epauserid="epauserid"
                            v-bind:userip="userip"

                    ></pop-edit-step-item>
                    </v-col>

                    <v-col cols="1" ><pop-delete-step-item
                            @success="showSuccessResults"
                            @error="showErrorResults"
                            @update="updatePage()"
                            v-bind:item="item"
                            v-bind:step="step"
                            v-bind:epauserid="epauserid"
                            v-bind:userip="userip"

                    ></pop-delete-step-item>
                    </v-col>

                </v-row>


            </div>

            <v-divider></v-divider>


        </v-card>

    </v-container>



</template>

<script>

    const STEPS_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE;
    const STEPS_ITEMS_URL = process.env.VUE_APP_API_STEPS_TO_HELP_PREPARE_ITEMS;
    const AOI_SUB_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEMS;

    import jQuery from "jquery";
    import area_of_interest from "@/models/area_of_interest";
    import area_of_interest_item from "@/models/area_of_interest_item";
    import area_of_interest_sub_item from "@/models/area_of_interest_sub_item";

    import deleteStep from '@/components/dashboard/steps/DeleteStepsPopup.vue'
    import addSteps from '@/components/dashboard/steps/NewStepsPopup.vue'
    import editSteps from '@/components/dashboard/steps/EditStepsPopup.vue'

    import addStepItem from '@/components/dashboard/steps/stepitems/NewStepItemPopup.vue'
    import editStepItem from '@/components/dashboard/steps/stepitems/EditStepItemPopup.vue'
    import DeleteStepItem from '@/components/dashboard/steps/stepitems/DeleteStepItemPopup.vue'

    export default {
        name: 'CreateAreaOfInterest',
        components:{
            'pop-step-edit' : editSteps,
            'pop-step-delete' : deleteStep,
            'pop-addSteps' : addSteps,

            'pop-add-step-item' : addStepItem,
            'pop-edit-step-item' : editStepItem,
            'pop-delete-step-item' : DeleteStepItem,


        },
        data: function() {
            return {
                area_of_interest: new area_of_interest(''),
                area_of_interest_item: new area_of_interest_item(''),
                area_of_interest_sub_item: new area_of_interest_sub_item(''),
                steps: null,
                stepitems: null,
                subitems: null,
                expand: false,
                expandStepsArray: [],
                expandItemArray: [],
               epauserid: null,
                userip: null,


            };

        },
        computed:{
            currentImage: function(){
                return this.expandsauce
            }


        },
        methods:
            {
                expandSteps(index, expandStepsArray){
                    //if already expanded, remove expand
                    if( expandStepsArray.includes(index) ){
                        for( var i = 0; i < expandStepsArray.length; i++){
                            if (expandStepsArray[i] === index){
                                expandStepsArray = expandStepsArray.splice(i,1)
                            }
                        }

                    }else{
                        expandStepsArray.push(index);

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

                //New GET request
                getStepsToHelp()
                {
                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ',
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
                getStepsToHelpPrepareItem(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ',
                          'userid' : this.epauserid,
                          'userip' : this.userip
                        }
                    });

                    var _this = this;

                    jQuery.getJSON(STEPS_ITEMS_URL, function (stepitems) {
                        _this.stepitems = stepitems;



                    });

                },
                getAreaOfInterestSubItem(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ',
                          'userid' : 'gbyrne'
                        }
                    });

                    var _this = this;

                    jQuery.getJSON(AOI_SUB_ITEMS_URL, function (subitems) {
                        _this.subitems = subitems;



                    });

                },
                updatePage(){
                    this.getStepsToHelp()
                    this.getStepsToHelpPrepareItem()
                    //this.getAreaOfInterestSubItem()
                }





            },
        created() {
          this.getUserId()

          var that = this;
          setTimeout(function() {
            that.getStepsToHelp()
            that.getStepsToHelpPrepareItem()
          },  this.$waittime);




        }
    }
</script>
<style>

    .container {
        max-width: 1100px;
    }

</style>
