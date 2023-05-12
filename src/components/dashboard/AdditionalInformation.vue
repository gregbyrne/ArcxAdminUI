<template>
    <v-container>
        <h1 style="text-align: center; color: #0071bc">ARC-X     </br>  Dashboard</h1>

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
                        <h2 style="float: left;color: #0071bc">Additional Information</h2>
                        <v-btn
                                style="margin-left:14px; margin-top:4px"
                                color="primary"
                                dark
                                small
                                @click="saveAI(description, regionSelect )"
                        >
                            Save
                        </v-btn>
                    </v-flex>

                </v-layout>

              <p id="resultselem" style="display: none"></p>

                <v-select
                        v-model="regionSelect"
                        :items="regions"
                        item-text="name"
                        item-value="code"
                        label="Region"
                >

                </v-select>
                    <v-btn
                            color="primary"
                            dark
                            small
                            v-bind="attrs"
                            v-on:click="addLinkHTML()"
                            class="descButton"
                    >
                        Add Link
                    </v-btn>
                    <v-btn
                            color="primary"
                            dark
                            small
                            v-bind="attrs"
                            v-on:click="addListItem()"
                            class="descButton"

                    >
                        Add List
                    </v-btn>
                    <v-btn
                            color="primary"
                            class="descButton"

                            dark
                            small
                            v-bind="attrs"
                            v-on:click="addImage()"

                    >
                        Add Image
                    </v-btn>
                    <v-btn
                            color="primary"
                            dark
                            small
                            v-bind="attrs"
                            v-on:click="addTable()"
                            class="descButton"

                    >
                        Add Table
                    </v-btn>

                    <v-textarea
                            v-model="description"
                            clearable
                            outlined
                            background-color="white"
                    ></v-textarea>

                    <p>HTML Preview. The HTML code can contain styling, but the preview does not include the styling included on the search results page.
                        <br/><br/>
                        <span v-html="description"></span></p>




            </v-container>



        </v-container>
    </v-container>
</template>

<script>
    import axios from "axios";
    import jQuery from "jquery";
    const REGIONS_URL = process.env.VUE_APP_API_REGIONS_URL;
    const ADDITIONAL_INFO_URL = process.env.VUE_APP_API_ADDITIONAL_INFORMATION_URL;

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
              checkStatusOfAccessToken() {



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
                getAdditionalInfo(){

                    jQuery.ajaxSetup({
                        headers : {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer '  ,
                             'userid' : this.epauserid,
                          'userip' : this.userip
                         }
                    });

                    var _this = this;

                    jQuery.getJSON(ADDITIONAL_INFO_URL, function (additionalInformation) {
                        _this.additionalInfo = additionalInformation;

                    });

                },addLinkHTML(){
                    let precontent = this.description
                    if(precontent == null){
                        precontent = ''
                    }
                    let link = "<a href = 'https://www.epa.gov' > Default URL </a>"

                    this.description = precontent + link

                },
                addListItem(){
                    let precontent = this.description

                    if(precontent == null){
                        precontent = ''
                    }

                    let list = "<ul>" +
                        "\n<li>item 1</li>" +
                        "\n<li>item 2</li>" +
                        "\n<li>item 3</li>" +
                        "\n</ul>"

                    this.description = precontent + list


                },addImage(){

                    let precontent = this.description
                    if(precontent == null){
                        precontent = ''
                    }
                    let list = "<span class='figure image file file-image file-image-png right view-mode-full' " +
                        "style='width:300px;'><img alt='CREAT Climate Scenarios Projection Map' " +
                        "height='169' width='300' class='right media-element file-full' " +
                        "src='https:/www.epa.gov//sites/production/files/styles/large/public/2016-08/final_climate_projection_map_snapshot_-_2080_warm_scenarios_lower_48.png'></span>"



                    this.description = precontent + list


                },addTable(){

                    let precontent = this.description
                    if(precontent == null){
                        precontent = ''
                    }
                    let table = '<table><thead>' +
                        '\n<tr><th scope="col">State</th>' +
                        '\n<th scope="col">Climate Change Adaptation Website, if available</th>' +
                        '\n<th scope="col" style="width:20%">EPA Region</th>' +
                        '\n</tr></thead>' +
                        '\n<tbody><tr>' +
                        '\n<td>Alabama</td>' +
                        '\n<td>No State Level Climate Adaptation Website Currently Identified</td>' +
                        '\n<td>Region 4</td>' +
                        '\n</tr>' +
                        '\n<tr><td>Arkansas</td>' +
                        '\n<td>No State Level Climate Adaptation Website Currently Identified</td>' +
                        '\n<td>Region 6</td>' +
                        '\n</tr>'





                    this.description = precontent + table


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
                ,saveAI(description, regionSelect){

                    if (regionSelect != null && regionSelect != "") {
                      let _this = this;
                      //_this.description = ''

                      //get ID
                      let AI = this.additionalInfo
                      let id = null
                      let region = null
                      for (let i = 0; i < AI.length; i++) {
                        if (regionSelect == AI[i].regioncode) {
                          id = AI[i].id
                          region = regionSelect
                        }

                      }


                      const headers = {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' ,
                        'userid' : this.epauserid,
                        'userip' : this.userip
                      }

                      // eslint-disable-next-line no-console
                      axios.put(ADDITIONAL_INFO_URL, { id: id,
                        description: description,
                        regioncode: regionSelect
                      }, {'headers': headers})

                          .then(function (response) {
                            if (response.status == 200) {
                              _this.showSuccessResults("Additional Information has been edited")

                              _this.getAdditionalInfo()

                            } else {
                              _this.showErrorResults("Additional Information was not edited.")
                            }
                          })
                          .catch((error) => {
                            _this.showErrorResults("An error has occurred! Error message: " + error)
                          });
                    }
                    else
                    {
                      alert("No region selected! Please select a region and try again.")
                    }



                },updatePage(){
                    this.getRegions()
                    this.getAdditionalInfo()
                }


            },
        created(){
          this.getUserId()

          var that = this;
          setTimeout(function() {
            that.getAdditionalInfo()
            that.getRegions()
          },  this.$waittime);



        }
    }
</script>


