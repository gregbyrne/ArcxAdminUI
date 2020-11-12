import Vue from 'vue';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';

Vue.use(Vuetify,);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#0071bc',
                secondary: '#2e8540',
            },
            dark:{

            }
        }

    }
});
