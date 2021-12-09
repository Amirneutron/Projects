<template>
  <div class="login">
    <b-alert class="cl" id="p1" show variant="danger"></b-alert>
    <form class="login-form" @submit.prevent="loginSubmit">
      <div class="imgcontainer">
      </div>

      <div class="container">
        <label for="email">
          <b>Username</b>
        </label>
        <input type="email" placeholder="Enter email address" v-model="email" />

        <label for="password">
          <b>Password</b>
        </label>
        <input type="password" placeholder="Enter password" v-model="password" /> 
        <button type="submit" class="clicker" >
        <span class="spinner-grow spinner-grow-sm"></span>
          Log in
        </button>         
      </div>

      <div class="container" style="background-color:#f1f1f1">
        <span class="psw">
          New User?
          <a href="/register">Register</a>
        </span>
      </div>
    </form>
  </div>
</template>

<script>
import { Api } from "@/Api";

export default {
  name: "login",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    loginSubmit() {
      Api.post("/users/login", {
        email: this.email,
        password: this.password
      })
        .then(response => {
          if (this.email === response.data.email) {
            alert(response.data._id);
            let userId = response.data._id
            this.$router.push(`/users/${userId}`);
          }
        })
        .catch(error => {
          console.log(error)
         document.getElementById("p1").innerHTML = "Invaid username or password !";
                var element = document.getElementById("p1");
                element.classList.remove("cl");
                this.error =true;
        });

    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  border: 3px solid #f1f1f1;
  border-radius: 5px;
  padding: 1.5rem;
  width: 360px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  overflow: hidden;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 1;
}
.container-loading {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.3);
}
img {
  width: 2rem;
  height: 2rem;
}
.form {
  position: relative;
  z-index: 1;
  max-width: 360px;
  margin: 0 auto 100px;
  text-align: center;
  padding: 45px;
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 1;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
/* Add padding to containers */
.container {
  padding: 16px;
}
/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}
/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
  .cancelbtn {
    width: 100%;
  }
}

/* Set a style for all buttons */
button {
  font-family: "Roboto", sans-serif;
  background-color: #dc3545;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  outline: 0;
  text-transform: uppercase;
}
/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}
/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #dc3545;
}

/* Full-width inputs */
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
.form button:hover,
.form button:active {
  background: #f2f2f2;
}
.form .message {
  margin: 15px 0 9;
  color: #dc3545;
  font-size: 12px;
}
.form .message a {
  color: #dc3545;
  text-decoration: none;
}

.clicker {
  background-color: #dc3545;
}

.orLine {
  align-items: center;
  display: flex;
  margin: 16px 47px;
  text-align: center;
}
form {
  display: flex;
  flex-flow: column;
}
*:not(:last-child) {
  margin-bottom: 1rem;
}
input {
  padding: 0.5rem;
}

.cl {
  visibility: hidden;
}
</style>
