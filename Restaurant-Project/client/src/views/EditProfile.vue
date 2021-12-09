<template>
  <div class="container">
    <h1>Edit : <span> Profile </span></h1>
    <form @submit.prevent="editProfile">
      <label for="name"><b>First Name</b></label>
      <input v-model="newProfile.fName" class="input" type="text" :placeholder="profile.fName">
       <label for="name"><b>Last Name</b></label>
      <input v-model="newProfile.lName" class="input" type="text" :placeholder="profile.lName">
       <label for="name"><b>Email Address</b></label>
      <input v-model="newProfile.email" class="input" type="email" :placeholder="profile.email">
      <label for="name"><b>Mobile Number</b></label>
      <input v-model="newProfile.mobileNumber" class="input" type="number" :placeholder="profile.mobileNumber">
      <hr>
      <b-button class="editButton" type="submit" >UPDATE PROFILE</b-button>
    </form>
    <form @submit.prevent="updatePassword">
      <label for="name" ><b>Old password</b></label>
      <input v-model="newProfile.fName" class="input" type="text" :placeholder="profile.password">
      <label for="name"><b>New password</b></label>
      <input v-model="newProfile.password" class="input" type="password" :placeholder="profile.password">
      <label for="name"><b>Retype new password</b></label>
      <input v-model="newProfile.password" class="input" type="password" :placeholder="profile.password">
      <hr>
      <b-button class="editButton" type="submit" >Save PASSWORD</b-button>
    </form>
  </div>
</template>

<script>
    import { Api } from '@/Api'
    export default {
        name: 'EditProfile',
        data() {
            return {
                profile: {},
                newProfile: {}
            }
        },
        created() {
            this.userId = this.$route.params.userId

        },
      
        methods: {
            getUser() {
                Api.get('/users/' + this.userId)
                    .then(response => {
                        this.profile = response.data
                    })
                    .catch(error => {
                        this.profile = null;
                        console.log(error)
                    })
                    .then(() => {
                        // This code is always executed (after success or error).
                    })
            },
            editProfile() {
                if(this.newProfile.fName && this.newProfile.lName && this.newProfile.email && this.newProfile.mobileNumber){
                   Api.put('/users/' + this.userId , this.newProfile)
                    .then(response => {
                        console.log(response.data)
                        alert("Your profile has been updated successfully")
                        this.$router.push({name: 'userPortal'})
                    })
                    .catch(error => {
                        console.log(error)
                    })

                }else{
                   Api.patch('/users/' + this.userId, this.newProfile)
                    .then(response => {
                        console.log(response.data)
                        alert("Your account has been updated successfully")
                        this.$router.push({name: 'userPortal'})
                    })
                    .catch(error => {
                        console.log(error)
                    })
                }
            },
            updatePassword(){
                  Api.patch('/users/' + this.userId + '/password', this.newProfile)
                    .then(response => {
                        console.log(response.data)
                        alert("Your password has been updated successfully")
                        this.$router.push({name: 'userPortal'})
                    })
                    .catch(error => {
                        console.log(error)
                    })

            }
        }
    }
</script>

<style scoped>

  /* Add padding to containers */
  .container {
    padding: 16px;
  }
  /* Full-width input fields */
  input[type=text], input[type=number]{
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
  }
  input[type=text]:focus, input[type=number]:focus{
    background-color: #ddd;
    outline: none;
  }
  /* Overwrite default styles of hr */
  hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
  }
  /* Set a style for the submit/register button */
  .editButton {
    background-color: #dc3545;
    color: white;
    padding: 16px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
  }
  .registerbtn:hover {
    opacity:1;
  }
</style>
