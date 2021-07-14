<template>
  <div id="app">
    <AppTopBar />
    <div class="container">
      <router-view/>
    </div>

    <AppMessageBar [currentUser]="generalData.user.currentUser" [isLogged]="generalData.isLogged" *ngIf="generalData.isLogged"></AppMessageBar>

    <AppErrorDialog />

    <AppLoadingDialog />

    <AppFooter />

  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
import AppTopBar from './components/layout/AppTopBar.vue'
import AppMessageBar from './components/layout/AppMessageBar.vue'
import AppErrorDialog from './components/layout/AppErrorDialog.vue'
import AppLoadingDialog from './components/layout/AppLoadingDialog.vue'
import AppFooter from './components/layout/AppFooter.vue'

export default {
  name: 'App',
  components: {
    AppTopBar,
    AppMessageBar,
    AppErrorDialog,
    AppLoadingDialog,
    AppFooter

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
