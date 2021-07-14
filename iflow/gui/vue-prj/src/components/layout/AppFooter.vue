<template>
  <div class="hello">
    <footer class="footer">
      <strong><span>{{ footer_text }}</span></strong>
      <span class="footer-title"></span>
    </footer>

  </div>
</template>

<script>
export default {
  name: 'AppFooter',
  props: {
    msg: String
  },
  created: function() {
     this.footer_text = process.env.VUE_APP_site_footer;
 }
}
</script>
<style>
.footer-title
{
	margin-left: 50px;
	font-weight: normal;
}

.footer
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
</style>