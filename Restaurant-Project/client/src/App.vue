<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="danger">
      <b-navbar-brand href="#">
        Molly's
        <span class="bold">Kitchen</span>
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-navbar-nav>
            <b-nav-item href="#">
              <router-link class="white" to="/">Home</router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link class="white" to="/adminLogin">Admin Portal</router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link class="white" to="/products">Menu</router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link class="white" to="/contact">Contact</router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link class="white" to="/login">Log out</router-link>
            </b-nav-item>

          
          



          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

   <div class="container">
      <transition name="moveInUp">
        <router-view />
      </transition>
    </div>
  </div>
</template>

<script>
export default {
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.moveInUp-enter-active {
  animation: fadeIn 2s ease-in;
}
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}

.moveInUp-leave-active {
  animation: moveInUp 0.3s ease-in;
}
@keyframes moveInUp {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-400px);
  }
}
.white {
  color: white;
  font-weight: bold;
}
.white:hover {
  color: #45454d;
  text-decoration: none;
}
.bold {
  font-weight: bold;
}
button{
  background-color: white;
  padding: 0.25em 0.5em;
}
</style>
