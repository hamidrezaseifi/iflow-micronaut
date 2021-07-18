<template>

  <div>

    <nav id="nav" class="iflow-navbar">

      <div class="navbar-header">
        <router-link class="navbar-brand" to="/">
          <img class="navbar-logo" src="@/assets/images/logo2.png">
          <span class="logo-title">Business Edition</span>
          <span class="clear"></span>
        </router-link>
      </div>

      <div class="iflow-navbar-bar">
        <ul class="navbar-nav" v-if="isLogged">
          <li class="nav-item" v-for="menu in menus" :key="menu.id" v-bind:class="{'dropdown' : menu.children.length > 0}">
            <router-link class="nav-link" to="[menu.url]" v-if="menu.children.length == 0">
              <span class="navbar-item-text">{{menu.label}}</span>
              <img v-bind:src="menu.image" class="navbar-image" />
            </router-link>
            <router-link class="nav-link dropdown-toggle" v-if="menu.children.length > 0" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <span class="navbar-item-text">{{menu.label}}</span>
              <img v-bind:src="menu.image" class="navbar-image" />
              <span class="navbar-item-text caret"></span>
            </router-link>
            <div class="dropdown-menu" v-if="menu.children.length > 0">
              <router-link to="[submenu.url]" v-for="submenu in menu.children" :key="submenu.id" class="dropdown-item">
                <span class="navbar-item-text">{{submenu.label}}</span>
                <img v-bind:src="submenu.image" class="navbar-image" />
                <div class="clear"></div>
              </router-link>
            </div>
          </li>
        </ul>

        <div class="navbar-item btn-group" v-if="isLogged" >
          <button type="button" class="btn btn-primary dropdown-toggle user-button" style="padding: 3px 10px;" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="material-icons">account_circle</i>
            <span>{{currentUser.fullName}}</span>
          </button>
          <div class="dropdown-menu">
            <router-link @click="showProfile" class="dropdown-item" href="#">Profile</router-link>
            <div class="dropdown-divider"></div>
            <router-link @click="logout" class="dropdown-item" href="#">Logout</router-link>

          </div>
        </div>

        <div class="navbar-item padding-top-7" v-if="isLogged" >
          <label class="iflow-switch">
            <input type="checkbox" v-model="isPresent">
            <span class="iflow-slider iflow-round"></span>

          </label>
          <label>{{ 'common.present' | translate }}</label>
        </div>


        <div class="clear"></div>
      </div>


    </nav>

    

  </div>
</template>

<script>
export default {
  name: 'AppTopBar',
  props: {
    isLogged: Boolean,
    menus: Array,
    currentUser: Object,
    _isPresent: Boolean
  },
  computed: {
    isPresent: {
      get() {
        return this._isPresent
      },
      set(val) {
        this._isPresent = val
      }
    }
  },
  methods: {
        logout () {

            const { dispatch } = this.$store;
            dispatch('authentication/logout');

        },
        showProfile(){

        }
    }
}
</script>
<style>

.navbar-header{
  padding: 5px;
}
.navbar-item {
    float: right;
    margin-right: 30px;
}

button.user-toggle-button {
    border: none;
    width: 170px;
    text-align: left;
    height: 34px;
    color: white;
    position: relative;
    background-color: #0169be;
}


button.user-toggle-button i.material-icons {
    font-size: 22px;
    margin-right: 5px;
    float: left;
}

.navbar-nav a.nav-link{
	color: white;
	font-size: 14px;
}

.iflow-navbar .dropdown-menu{
	background: #0169be;
  color: white;
  margin: 0;
  padding: 0;
  padding-top: 5px;
}

.iflow-navbar .dropdown-menu:hover{
	background: #0169be;
  color: #ffffd7;
}

.iflow-navbar .dropdown-item{
	color: white;
	width: 220px !important;
	background: #0169be;
  padding: 2px 5px;
  display: inline-block;
}

.iflow-navbar .navbar-item .dropdown-item{
  padding: 5px 10px 5px;
  margin-bottom: 2px;
  display: block;
  text-decoration: none;
}

.iflow-navbar .dropdown-item:hover, .iflow-navbar li.nav-item:hover{
	background: #285f8f;
	color: #ffffd7;
	text-decoration: none;
}

.navbar-nav a.nav-link:hover{
	color: #ffffd7;
}

.navbar {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

ul.navbar-nav{
	display: block !important;
	float: left;
	width: calc(100% - 380px);
	padding-left: 10px;
}

ul.navbar-nav li.nav-item {
    display: block !important;
    float: left;
    margin: 0 5px;
    padding: 3px 5px;
}

.iflow-navbar{
	background: #0169be;
	color: white;
}

.navbar-logo
{
  height: 36px;
  float: left;
  margin-left: 20px;
  background-color: white;
}

a.navbar-brand{
	color: black;
}

a.navbar-brand:hover{
	color: black;
}

span.logo-title{
	font-size: 22px;
	float: left;
	margin-left: 20px;
	color: white;
}

.navbar-image{
	height: 20px;
	margin-left: 5px;
	float: right;
}

.navbar-item-text{
	float: left;
}

.user-button i, .user-button span{
  float: left;
  margin: 0 3px;
}


</style>