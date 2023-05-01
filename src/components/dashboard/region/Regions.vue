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
                        <h2 style="float: left;color: #0071bc">Regions</h2>

                    </v-flex>

                </v-layout>

              <p id="resultselem" style="display: none"></p>

              <v-select
                  v-model="regionSelect"
                  :items="regions"
                  item-text="name"
                  item-value="id"
                  label="Region"
              >

              </v-select>


              <v-container>
                  <v-col cols="12">
                    <v-text-field
                        v-model="newRegionName"
                        label="New Region Name*"
                        required
                    ></v-text-field>
                    <v-text-field
                        v-model="newRegionCode"
                        label="New Region Code*"
                        required
                    ></v-text-field>
                  </v-col>
                <v-btn
                    color="blue darken-4"
                    text
                    @click="newRegionSave(newRegionName, newRegionCode )"
                >
                  Add New Region
                </v-btn>
                <v-btn
                    color="blue darken-4"
                    text
                    @click="UpdateRegion(regionSelect,newRegionName, newRegionCode )"
                >
                  Update Region
                </v-btn>


              </v-container>


            </v-container>



        </v-container>
    </v-container>
</template>

<script>
    import axios from "axios";
    import jQuery from "jquery";
    const REGIONS_URL = process.env.VUE_APP_API_REGIONS_URL;

    export default {
        components : { },
        name: 'Add Steps',
        data: function() {
            return {

                regions: null,
                additionalInfo: [],
                regionSelect: null,
                description: null,
                epauserid : null,



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
            }
        },
        methods:
            {
              logOut() {



              },
              newRegionSave(newRegionName, newRegionCode) {

                let _this = this;

                const headers = {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' ,
                  'userid' : this.epauserid,
                  'name' : newRegionName,
                  'code' : newRegionCode,
                  'userip' : this.userip

                }
                axios.post(REGIONS_URL + "/" ,
                    { name: newRegionName, code: newRegionCode}, { 'headers': headers})
                    .then(function (response) {
                      if (response.status.toString().includes("20")) {

                        _this.$emit('success', 'Region has been created')
                        _this.$emit('update')

                      }
                      else
                      {
                        _this.$emit('error', 'Region was NOT created')
                      }
                    })
                    .catch((error) => {
                      _this.$emit('error', 'ERROR: ' + error)
                    })

                this.dialog = false

              },

              UpdateRegion(regionId,newRegionName, newRegionCode ){
                let _this = this;
                const headers = {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' ,
                  'userid' : this.epauserid,
                  'name' : newRegionName,
                  'code' : newRegionCode,
                  'id'   : regionId,
                  'userip' : this.userip

                }
                axios.put(REGIONS_URL + "/" ,
                    { id: regionId, name: newRegionName, code: newRegionCode}, { 'headers': headers})
                    .then(function (response) {
                      if (response.status.toString().includes("20")) {

                        _this.$emit('success', 'Region has been created')
                        _this.$emit('update')

                      }
                      else
                      {
                        _this.$emit('error', 'Region was NOT created')
                      }
                    })
                    .catch((error) => {
                      _this.$emit('error', 'ERROR: ' + error)
                    })

                this.dialog = false

              },


                getRegions(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' ,
                            'userid' : this.epauserid,
                            'userip' : this.userip
                        }
                    });

                    var _this = this;

                    var jsonData = jQuery.getJSON(REGIONS_URL, function (regions) {
                        _this.regions = regions;

                    });

                  jsonData.fail(function(data) {
                    if (data.status == '401')
                    {
                      _this.logOut()
                    }
                  })

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
                }
                ,updatePage(){
                    this.getRegions()
                }


            },
        created(){
          this.getUserId()

          var that = this;
          setTimeout(function() {
            that.getRegions()
          },  this.$waittime);



        }
    }
</script>


