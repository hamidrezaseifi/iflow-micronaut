<template>
  <div class="login">
    <div class="login-container-parent" layout="row" layout-align="center center">
      <div class="login-container">
        <div class="login-photo"></div>

        <div class="login-form-container">
          <form action="/login" method="POST" style="margin: 0;" @submit.prevent="handleSubmit">

            <div class="form-group">
              <label class="username-label" for="username">{{ $t('common.username') }}</label>
              <input class="form-control" id="username"  type="text" name="username" v-model="username" >
            </div>

            <div class="form-group">
              <label class="password-label" for="password">{{ $t('common.password') }}</label>
              <input class="form-control" id="password"  type="password" name="password" v-model="password">
            </div>

            <div class="form-group">
              <input class="btn btn-primary login-button" type="button" v-bind:value="$t('common.login')" @click="handleSubmit" />
            </div>

            <div class="" v-if="isFailed">
              <div class="alert alert-danger" role="alert">{{ logginMessage }}</div>
            </div>

            <input type="hidden" name="returnUrl" v-model="returnUrl">

            <li v-if="isFailed" id="errors">
              <span style="color: red;">Login Failed</span>
            </li>

          </form>

        </div>



      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      username: 'admin@iflow.de',
      password: 'admin',
      returnUrl: '',
      submitted: false,
      logginMessage: '',
      isFailed: false
    }
  },
  computed: {
    loggingIn () {
      return this.$store.state.authentication.status.loggingIn;
    },
    message: {
      get () {
        return this.$store.state.alert.message
      }
    },
    hasMessage: {
      get () {
        return this.$store.state.alert.message != null
      }
    }

  },
  created () {
  // reset login status
  //this.$store.dispatch('authentication/logout');
  },
  methods: {
    handleSubmit () {
      this.submitted = true;
      const { username, password } = this;
      const { dispatch } = this.$store;
      if (username && password) {
        dispatch('authentication/login', { username, password });
      }
    }
  }
};
</script>
<style>
body
{
  position: relative !important;
  width: 100% !important;
  top: 0px !important;
  background-color: #c7c7c7  !important;
}

body:not(.overdialog)
{
  overflow: auto !important;
}

body.overdialog
{
  overflow: hidden !important;
}

.clear
{
  clear: both;
}

footer.footer
{
  background-color: rgb(63,81,181);
  color: rgba(255,255,255,0.87);
  position: fixed;
  width: 100vw;
  bottom: 0;
  height: 30px;
  line-height: 30px;
  padding-left: 50px;
}

.login-container-parent
{
  height: calc(100vh - 140px);
  display: flex;
}

.login-container
{
  min-height: 490px;
  width: 400px;
  background: #dadada;
  border: 1px solid gray;
  box-shadow: 0px 0px 30px #807e7e;
  margin: auto auto;
}

.login-form-container{
	padding: 20px;
}

.login-container .login-photo
{
  background-image: url(../../assets/images/login-photo.jpg);
  background-repeat: no-repeat;
  background-size: 100%;
  background-position: left top;
  height: 253px;
  width: 100%;
}

.login-container label
{
  font-size: 14px;
  font-weight: normal;
  color: #706e6e;
  padding-left: 7px;
}

.login-container .message
{
  height: 40px;
  line-height: 30px;
  color: red;
  margin-top: 10px;
}

.login-container .submit
{
  width: 90px;
  height: 30px;
  line-height: 20px;
  margin-top: 10px;
}


.login-container .passwordreset
{
  font-size: 10px;
  float: right;
}

.login-container input.form-control {
    border: none;
    background: transparent;
    box-shadow: none;
    border-bottom: #aaaaaa solid 2px;
    border-radius: 0;
    padding: 0 5px;
    height: 25px;
}

.login-container .alert {
    padding: 5px 15px;
    margin-bottom: 0;
    border: 1px solid transparent;
    border-radius: 4px;
}

.form-group label.username-label {
  margin-bottom: 5px;
  margin-top: 5px;
}

.form-group label.password-label {
  margin-bottom: 5px;
  margin-top: 20px;
}

.form-group input {
  margin-bottom: .5rem;
  margin-top: 5px;
}


.form-group input.login-button {
  margin-top: 20px;
}


</style>