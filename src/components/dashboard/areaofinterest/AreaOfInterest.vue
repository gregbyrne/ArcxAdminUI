<template>

    <v-container class="mx-6 my-10">

        <div>
            <li v-for="area in areaofint" :key="area.id">{{area.name}}</li>
        </div>

        <form>
            <p>Add Area of Interest</p>
            <input
                    v-model="area_of_interest.name"
                    v-validate="'required'"
                    type="text"
                    class="form-control"
                    name="name"
            />
            <button class="styled" type="button" @click="addNewAreaOfInterest">Add</button>
        </form>


     </v-container>

</template>

<script>

    import axios from "axios";
    import jQuery from "jquery";
    import area_of_interest from "@/models/area_of_interest";

    export default {
        name: 'CreateAreaOfInterest',
        data: function() {
        return {
                area_of_interest: new area_of_interest(''),
                areaofint: null
            };
        },
        methods:
                {
                    //New POST request
                    addNewAreaOfInterest()
                    {

                        let _this = this;

                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }

                        axios.post('http://localhost:7100/api/area_of_interest',
                            this.area_of_interest, { 'headers': headers})
                            .then(function (response) {
                                if (response.status == 201) {
                                    _this.getAreaOfInterest()
                                    alert('Area of Interest has been created');
                                }
                                else
                                {
                                    alert('Area of Interest was not created');
                                }
                            })
                            .catch((error) => {
                                alert('ERROR: ' + error);
                            })
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

                        jQuery.getJSON('http://localhost:7100/api/area_of_interest', function (areaofint) {
                            _this.areaofint = areaofint._embedded.area_of_interest;
                        });
                    }


                },
            created() {
                this.getAreaOfInterest()
            }
        }
</script>
<style>

    .styled {

        border: 0;
        line-height: 2.5;
        padding: 0 20px;
        margin-top: 10px;
        font-size: 1rem;
        text-align: center;
        color: #fff;
        text-shadow: 1px 1px 1px #000;
        border-radius: 10px;
        background-color: #0071bc;
        background-image: linear-gradient(to top left,
        rgba(0, 0, 0, .2),
        rgba(0, 0, 0, .2) 30%,
        rgba(0, 0, 0, 0));
        box-shadow: inset 2px 2px 3px rgba(255, 255, 255, .6),
        inset -2px -2px 3px rgba(0, 0, 0, .6);
    }

    .styled:hover {
        background-color: #2d9be3;
    }

    .styled:active {
        box-shadow: inset -2px -2px 3px rgba(255, 255, 255, .6),
        inset 2px 2px 3px rgba(0, 0, 0, .6);
    }

</style>
