
<template>
  <div id="app">
    <Navbar></Navbar>
    <router-view/>
  </div>
</template>

<script>
/* eslint-disable*/

import Navbar from './components/Navbar.vue'
export default {
  name: 'app',
  components:{Navbar}
}
</script>

<style>

</style>
