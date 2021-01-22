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
                                @click="dialog = false"
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

                    <v-row class="pa-1" row wrap>
                        <v-col cols="3">
                            <!--<div class="caption grey--text">Area of Interest</div>
                            <div>{{item.name }}</div>-->

                            <v-select
                                    v-model="areaselect"
                                :items="areaofint"
                                item-text="name"
                                item-value="id"
                                label = "Area of Interest">

                            </v-select>
                        </v-col>
                        <v-col cols="3">
                            <v-select
                                    v-model="itemselect"
                                    :items="filteredItems"
                                    item-text="name"
                                    item-value="id"
                                    label = "Area of Interest Item">

                            </v-select>
                        </v-col>

                        <v-col cols="3">
                            <v-select
                                    v-model="subitemselect"
                                    :items="filteredSubItems"
                                    item-text="name"
                                    item-value="id"
                                    :label="subItemLabel">

                            </v-select>
                        </v-col>
                    </v-row>

                    <v-list-item>
                        <v-container>
                                    <v-text-field
                                            v-model="item.name"
                                            label="Step To Help Name*"
                                            required
                                    ></v-text-field>

                        </v-container>
                    </v-list-item>
                    <v-list-item>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field
                                            v-model="item.subTitle"
                                            label="Subtitle"

                                    ></v-text-field>
                                </v-col>


                            </v-row>
                        </v-container>
                    </v-list-item>

                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>Description</v-list-item-title>
                            <v-list-item-subtitle>Set the text that will show with each step item. This text allows for HTML to be used. Such as for making lists and links</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-textarea
                            v-model="item.description"
                            clearable
                            outlined
                    ></v-textarea>



                </v-list>
                <v-divider></v-divider>
                <v-list
                        three-line
                        subheader
                >
                    <v-subheader>test</v-subheader>

                </v-list>
            </v-card>
        </v-dialog>
    </v-row>


</template>

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


        }),
        computed: {
            filteredItems(){
                let options = this.aoiitems
                let newOptions = []

                if( this.areaselect == null){
                    newOptions = ''
                }else{
                    for(let i = 0; i < options.length; i++){
                        if(options[i].parentid == this.areaselect){
                            newOptions.push(options[i])
                        }
                    }

                }

                return newOptions
            },filteredSubItems(){
                let options = this.subitems
                let newOptions = []

                if( this.itemselect == null){
                    newOptions = ''
                }else{
                    for(let i = 0; i < options.length; i++){
                        if(options[i].parentid == this.itemselect){
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
            },
        },
        methods:{
            editAOE(item ){

                let _this = this;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                }

                axios.put(API_URL + 'area_of_interest_sub_items/' + item.id ,{ name: item.name, parentid: item.parentid, value: item.value}, {'headers': headers} )
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
