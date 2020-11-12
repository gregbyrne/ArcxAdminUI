<template>
    <v-container >
        <v-header >ARC-X Dashboard {{ date }}</v-header>

        <v-container class="mx-6 my-10">
            <v-row>
                <v-col>
                    <app-pop></app-pop>
                </v-col>
                <v-col>
asdasdasd

                </v-col>

            </v-row>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam quis mi sapien. Fusce vitae mi sodales mi fermentum ullamcorper. Suspendisse laoreet vitae eros ac posuere. Proin congue eu est et imperdiet. Curabitur feugiat scelerisque turpis, et efficitur tortor. Praesent ut lacinia neque. Donec nec aliquet justo. Curabitur blandit justo est, sed rhoncus odio volutpat in. Sed mollis purus ex, ut viverra purus convallis in. Curabitur tortor est, hendrerit pulvinar euismod ullamcorper, gravida sit amet ex. Donec nec urna imperdiet, convallis quam luctus, iaculis turpis. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas imperdiet feugiat eros eu aliquam. Vivamus ornare mi vitae luctus tincidunt.
            </p>

            <p>Sed aliquet risus eget neque blandit tristique. Nulla aliquam urna libero, eu lacinia dolor fringilla eu. Proin ac ex sodales, consectetur diam non, luctus lorem. Phasellus eu varius arcu. Praesent semper iaculis facilisis. Donec ligula justo, facilisis eget maximus id, fermentum non est. Suspendisse dapibus pharetra metus sit amet dictum. Nullam vel nunc luctus, elementum mauris ut, lacinia enim. Fusce sollicitudin ultricies diam nec vestibulum. Sed urna libero, molestie in diam eget, aliquet pulvinar purus. Nam egestas, magna et cursus mollis, ipsum enim imperdiet lorem, ac interdum quam neque at lectus. Aenean placerat dignissim porta. Sed scelerisque congue commodo. Aenean eu arcu eu purus ornare tempor. Aenean ornare porttitor velit sit amet tincidunt. Vivamus cursus ex sed tempor aliquet.

            </p>
            <p>Suspendisse suscipit facilisis congue. Nam aliquet sit amet dui quis bibendum. Fusce maximus risus sit amet est ullamcorper, et euismod sapien tempus. Donec id sem at quam accumsan semper rhoncus eget augue. Cras malesuada sollicitudin erat quis sollicitudin. Suspendisse potenti. Praesent ut dapibus dolor, quis pellentesque ligula. Etiam eget ipsum odio. Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris eget turpis metus.

            </p>
        </v-container>
    </v-container>
</template>

<script>
    import pop from './Popup.vue'

    export default {
        components : {       'app-pop' : pop },
        name: 'Dashboard',
        computed: {
            date : function () {
                return Date.now();
            },
            getCurrentUser() {
                return this.$store.getters.getCurrentUser;
            }
        },
        data: () => ({
            linkshere: [
                {
                    text: 'vuetify-loader',
                    href: 'https://github.com/vuetifyjs/vuetify-loader',
                },
                {
                    text: 'github',
                    href: 'https://github.com/vuetifyjs/vuetify',
                },
                {
                    text: 'awesome-vuetify',
                    href: 'https://github.com/vuetifyjs/awesome-vuetify',
                },
            ]
        }),
    }
</script>


<script>
    export default {
        name: 'Home',
        props: {
            msg: String
        },

    }
</script>