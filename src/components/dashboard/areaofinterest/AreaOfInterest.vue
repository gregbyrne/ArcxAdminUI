<template>

    <v-container class="mx-6 my-10">

        <div>
            <li v-for="(area, index) in areaofint" :key="index">{{area.name}}</li>
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
    import area_of_interest from "@/models/area_of_interest";

    export default {
        name: 'CreateAreaOfInterest',
        data() {

            return {

                area_of_interest: new area_of_interest('')

            }

        },
        computed: {
            areaofint()
            {
                let data;
                try {
                    data  = this.$store.state.areaofinterest.area_of_interest
                } catch(e) {
                    data += data
                }

                return data
            }
        },
            methods:
                {
                    loadAreaOfInterest() {

                        this.$store.dispatch("areaofinterest/getAreaOfInterest", this.$store.state.auth.user.accessToken)
                    },

                    //New POST request
                    addNewAreaOfInterest()
                    {

                        let context = this;

                        const headers = {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                        }

                        axios.post('http://localhost:7100/api/area_of_interest',
                            this.area_of_interest, { 'headers': headers})
                            .then(function (response) {
                                if (response.status == 201) {
                                    alert('Area of Interest has been created');
                                    context.loadAreaOfInterest()
                                }
                                else
                                {
                                    alert('Area of Interest was not created');
                                }
                            })
                            .catch((error) => {
                                alert('ERROR: ' + error);
                            })
                    }

                },
            created() {
                this.loadAreaOfInterest()
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
