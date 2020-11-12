<template>
    <nav >
        <v-app-bar app>
            <v-app-bar-nav-icon class="grey--text--lighten4" @click="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title class="text-uppercase grey--text--lighten4">
                <span class="font-weight-light ">ARC-X admin console</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>

            <v-btn href="/profile" depressed color="grey--lighten4">
                <span>Profile</span>
                <v-icon>mdi-account</v-icon>
            </v-btn>
            <v-btn v-if="currentUser" @click.prevent="logOut" depressed color="grey--lighten4">
                <span>Sign Out</span>
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>

            <v-btn v-if="!currentUser" to="/login" depressed color="grey--lighten4">
                <span>Sign In</span>
                <v-icon>mdi-login</v-icon>
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
    </nav>
</template>


<script>

    export default {

    data(){

        return{
            drawer : false,
            links: [
                { icon: 'mdi-information-outline', text: 'Profile', route: '/profile' },
                { icon: 'mdi-view-dashboard-outline', text: 'Dashboard', route: '/' }

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

