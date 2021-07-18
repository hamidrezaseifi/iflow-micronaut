<template>
  <div id="app">
    <AppTopBar v-bind:isLogged="isLogged" v-bind:menus="menus" v-bind:currentUser="currentUser" />
    <div class="container">
      <router-view/>
    </div>

    <AppMessageBar :isAppLogged="isAppLogged"></AppMessageBar>

    <AppErrorDialog />

    <AppLoadingDialog />

    <AppFooter />

  </div>
</template>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
<script>
//import HelloWorld from './components/HelloWorld.vue'
import AppTopBar from './components/layout/AppTopBar.vue'
import AppMessageBar from './components/layout/AppMessageBar.vue'
import AppErrorDialog from './components/layout/AppErrorDialog.vue'
import AppLoadingDialog from './components/layout/AppLoadingDialog.vue'
import AppFooter from './components/layout/AppFooter.vue'

require('@/assets/styles/app.css')

export default {
  name: 'App',
  components: {
    AppTopBar,
    AppMessageBar,
    AppErrorDialog,
    AppLoadingDialog,
    AppFooter

  },
  data: function () {
  return {
    isLogged: false,
    menus: [],
    currentUser: null,
    isAppLogged: false

  }
}
}
</script>
