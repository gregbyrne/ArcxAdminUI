<template>
    <v-row justify="center" style="padding-left:10px;">

        <v-dialog
                v-model="dialog"

                fullscreen
        >
            <template v-slot:activator="{ on, attrs }" >
                <v-btn
                        color="primary"
                        dark
                        small
                        v-bind="attrs"
                        v-on="on"
                >
                    Edit
                </v-btn>
            </template>
            <v-card>
                <v-toolbar
                        dark
                        color="primary"
                >
                    <v-btn
                            icon
                            dark
                            @click="dialog = false"
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Steps to Help Prepare Items Settings</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn
                                dark
                                text
                                @click="saveStepItem(item)"
                        >
                            Save
                        </v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-list
                        three-line
                        subheader
                >
                    <v-subheader>Step to Help Prepare Items Settings</v-subheader>

                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>Choose Item or SubItem to apply this Step To Help </v-list-item-title>
                            <v-list-item-subtitle>Filter through the drop down boxes to select the correct item</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-list-item><v-row class="pa-1" row wrap>
                        <v-col cols="3">
                            <!--<div class="caption grey--text">Area of Interest</div>
                            <div>{{item.name }}</div>-->

                            <v-select
                                    v-model="item.aoiId"
                                    :items="areaofint"
                                    item-text="name"
                                    item-value="id"
                                    label = "Area of Interest">

                            </v-select>
                        </v-col>
                        <v-col cols="3">
                            <v-select
                                    v-model="item.aoiItemsId"
                                    :items="filteredItems"
                                    item-text="name"
                                    item-value="id"
                                    label = "Area of Interest Item">

                            </v-select>
                        </v-col>

                        <v-col cols="3">
                            <v-select
                                    v-model="item.aoiSubItemsId"
                                    :items="filteredSubItems"
                                    item-text="name"
                                    item-value="id"
                                    :label="subItemLabel"
                                        >

                            </v-select>
                        </v-col>
                    </v-row></v-list-item>




                    <v-list-item>
                                    <v-text-field
                                            v-model="item.name"
                                            label="Step To Help Name*"
                                            required
                                    ></v-text-field>

                    </v-list-item>
                    <v-list-item>
                                    <v-text-field
                                            v-model="item.subTitle"
                                            label="Subtitle"

                                    ></v-text-field>
                    </v-list-item>

                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>Description</v-list-item-title>
                            <v-list-item-subtitle>Set the text that will show with each step item. This text allows for HTML to be used. Such as for making lists and links</v-list-item-subtitle>

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
                                    v-model="item.content"
                                    clearable
                                    outlined
                            ></v-textarea>

                            <p>HTML Preview. Styling does not include eoa
                            <br/><span v-html="item.content"></span></p>


                        </v-list-item-content>
                    </v-list-item>






                </v-list>
                <v-divider></v-divider>
                <v-list
                        three-line1
                        subheader
                >
                    <v-subheader></v-subheader>

                </v-list>
            </v-card>
        </v-dialog>
    </v-row>


</template>

<style>
    .descButton{
        max-width: 95px;
        margin-right: 25px;
    }


</style>

<script>
    import axios from "axios";
    import jQuery from "jquery";

    const API_URL = process.env.VUE_APP_API_URL;
    const AOI_URL = process.env.VUE_APP_API_AREA_OF_INTEREST;
    const AOI_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_ITEMS;
    const AOI_SUB_ITEMS_URL = process.env.VUE_APP_API_AREA_OF_INTEREST_SUB_ITEMS;



    export default {
        name: 'Popup',
        aoeName: 'default',
        newName: '',
        props: ['step', 'item'],




        data: () => ({

            dialog: false,
            notifications: false,
            sound: true,
            widgets: false,
            areaofint: null,
            aoiitems: null,
            subitems: null,
            midpoint : 5,
            areaselect: null,
            itemselect: null,
            subitemselect: null,
            nullItem: { name: 'All Items', id: null  },
            nullSubItem: { name: 'All Sub Items', id: null  },
            subItemStatus: false,



        }),
        computed: {
            filteredItems(){
                let options = this.aoiitems
                let newOptions = []


                if( (this.aoiitems == null) || (this.item.aoiId == null)){
                    newOptions = ''
                }else{
                    newOptions.push(this.nullItem)
                    for(let i = 0; i < options.length; i++){
                        //console.log( 'options[i].parentid: ' + options[i].parentid  + '==  this.item.aoiId: ' + this.item.aoiId)

                        if(options[i].parentid == this.item.aoiId){
                            newOptions.push(options[i])
                        }
                    }

                }

                return newOptions
            },filteredSubItems(){
                let options = this.subitems
                let newOptions = []


                if( (this.subitems == null) || (this.item.aoiItemsId == null)|| (this.item.aoiItemsId == '')) {
                    newOptions = ''
                }else{

                    newOptions.push(this.nullSubItem)
                    for(let i = 0; i < options.length; i++){
                        if(options[i].parentid == this.item.aoiItemsId){
                            newOptions.push(options[i])
                        }
                    }

                }



                return newOptions
            },subItemLabel(){
                let label = 'Area Of Interest Sub Items'


                if(this.filteredSubItems.length == 0 ){
                    label = 'N/A'
                }
                return label
            },subItemStatus(){

                //for later
                let status = false
                if((this.item.aoiItemsId == null) || (this.item.aoiItemsId == '')){
                    status = true
                }else{
                    status = false
                }
                return status

            },
        },
        methods:{
            saveStepItem(item ){

                alert(this.areaselect)

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(API_URL + 'steps_to_help_prepare_items/' + item.id ,{ name: item.name, parentid: item.parentid, content: item.content, subTitle : item.subTitle,
                    aoiId: item.aoiId, aoiItemsId: item.aoiItemsId, aoiSubItemsId : item.aoiSubItemsId }, {'headers': headers} )

                    .then(function (response) {
                        if (response.status == 200) {
                            _this.$emit('update')
                            alert('Sub Item has been edited');
                        }
                        else
                        {
                            alert('Sub Item was not edited');
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: with edit ' + error);
                    });

                this.dialog = false


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

        },addLinkHTML(){
                let precontent = this.item.content
                if(precontent == null){
                    precontent = ''
                }
                let link = "<a href = 'https://www.google.com' > defaulttest url </a>"

                this.item.content = precontent + link




            },
            addListItem(){
                let precontent = this.item.content

                if(precontent == null){
                    precontent = ''
                }

                let list = "<ul>" +
                    "\n<li>item 1</li>" +
                    "\n<li>item 2</li>" +
                    "\n<li>item 3</li>" +
                    "\n</ul>"

                this.item.content = precontent + list


            },addImage(){

                let precontent = this.item.content
                if(precontent == null){
                    precontent = ''
                }
                let list = "<span class='figure image file file-image file-image-png right view-mode-full' " +
                    "style='width:300px;'><img alt='CREAT Climate Scenarios Projection Map' " +
                "height='169' width='300' class='right media-element file-full' " +
                    "src='https:/www.epa.gov//sites/production/files/styles/large/public/2016-08/final_climate_projection_map_snapshot_-_2080_warm_scenarios_lower_48.png'></span>"



                this.item.content = precontent + list


            },addTable(){

                let precontent = this.item.content
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





                this.item.content = precontent + table


            },




        },
        created() {
            //this.checkStatusOfAccessToken()
            this.getAreaOfInterest()
            this.getAreaOfInterestItem()
            this.getAreaOfInterestSubItem()

        },


    }
</script>
