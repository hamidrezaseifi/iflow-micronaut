import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuex from 'vuex'
import { store } from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

Vue.config.productionTip = false
Vue.use(Vuex);

new Vue({
  router,
  store,
  render: h => h(App),
 created: function() {
     document.title = process.env.VUE_APP_title;
 }
}
).$mount('#app')
