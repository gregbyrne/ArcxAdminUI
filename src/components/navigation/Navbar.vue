<template>
    <nav >
        <v-app-bar app>
            <v-app-bar-nav-icon class="grey--text--lighten4" @click="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title class="text-uppercase grey--text--lighten4">
                <span class="font-weight-light ">ARC-X admin console</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>

            <v-btn href="/additionalinformation" depressed color="grey--lighten4">
                <span>Additional Information</span>
                <v-icon>mdi-view-dashboard-outline</v-icon>
            </v-btn>

            <v-btn href="/areaofinterest" depressed color="grey--lighten4">
              <span>Area of Interest</span>
              <v-icon>mdi-view-dashboard-outline</v-icon>
            </v-btn>

            <v-btn href="/stepstohelpprepare" depressed color="grey--lighten4">
              <span>Steps To Help Prepare</span>
              <v-icon>mdi-view-dashboard-outline</v-icon>
            </v-btn>



        </v-app-bar>

        <v-navigation-drawer v-if="currentUser" v-model="drawer" app class="primary">
            <v-list>
                <v-list-item v-for="link in links" :key="link.text" :to="link.route">
                    <v-list-item-action>
                        <v-icon class="white--text">{{link.icon }}
                        </v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class = "white--text">
                            {{ link.text }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-navigation-drawer v-if="!currentUser" v-model="drawer" app class="primary">
            <v-list>
                <v-list-item v-for="link in links" :key="link.text" href="/login">
                    <v-list-item-action>
                        <v-icon class="white--text">{{link.icon }}
                        </v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class = "white--text">
                            {{ link.text }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-navigation-drawer v-if="!currentUser" v-model="drawer" app class="primary">
            <v-list>
                <v-list-item v-for="link in links" :key="link.text" href="/login">
                    <v-list-item-action>
                        <v-icon class="white--text">{{link.icon }}
                        </v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class = "white--text">
                            {{ link.text }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
    </nav>
</template>


<script>

    export default {

    data(){

        return{
            drawer : false,
            links: [
                { icon: 'mdi-view-dashboard-outline', text: 'Additional Information', route: '/additionalinformation' },
                { icon: 'mdi-view-dashboard-outline', text: 'Area Of Interest', route: '/areaofinterest' },
                { icon: 'mdi-view-dashboard-outline', text: 'Steps To Help Prepare', route: '/stepstohelpprepare' },

            ]
        }

        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        methods: {
            logOut() {

                this.$store.dispatch('auth/logout');
                this.$router.push('/login');

            }
        }

}


</script>

