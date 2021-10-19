<template>
    <v-container class="mx-6 my-10">
          <h1 style="text-align: center; color: #0071bc">ARC-X Dashboard</h1>

          <input type="radio" name="tabs" id="tab1" @click="componentView= 'AreaOfInterestList'" checked />
          <label for="tab1">Area of Interest</label>
          <input type="radio" name="tabs" id="tab2" @click="componentView= 'SortAreaOfInterest'" />
          <label for="tab2">Sort Area of Interest</label>

          <div class="tab content1"><component :is="componentView"></component></div>
          <div class="tab content2"><component :is="componentView"></component></div>

        </v-container>
</template>

<script>

    import AreaOfInterestList from './areaofinterest/AreaOfInterest'
    import SortAreaOfInterest from './areaofinterest/SortAreaOfInterest'

    export default {
        components : { AreaOfInterestList, SortAreaOfInterest},
        name: 'Dashboard',
      data: function() {
        return {
          componentView: AreaOfInterestList
        }
        },
        computed: {
            date : function () {
                return Date.now()
            },
            currentUser() {
                return this.$store.state.auth.user
            }
        }
    }
</script>
<style>

input[type="radio"] { display: none; }                /* hide radio buttons */
input + label { display: inline-block } /* show labels in line */
input ~ .tab { display: none }          /* hide contents */
/* show contents only for selected tab */
#tab1:checked ~ .tab.content1,
#tab2:checked ~ .tab.content2 { display: block; }

input + label {             /* box with rounded corner */
  border: 1px solid #999;
  background: #0071bc;
  padding: 4px 12px;
  border-radius: 4px 4px 0 0;
  position: relative;
  top: 1px;
  color: white;
}
input:checked + label {     /* white background for selected tab */
  background: #FFF;
  border-bottom: 1px solid transparent;
  color: #0071bc;
}
input ~ .tab {          /* grey line between tab and contents */
  border-top: 1px solid #999;
  padding: 12px;
}

</style>
