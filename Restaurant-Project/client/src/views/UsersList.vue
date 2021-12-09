<template>
  <div class="container">
    <div>
        <h2>List Of All Users: {{users.length}}</h2>
        <hr>
        <button type="button" @click="deleteAllUsers" class="btn btn-danger">Delete All Users</button>     
         <user-item v-for="user in users" :key="user._id" :user="user" @delete-user="deleteUser"></user-item>
    </div>
    <hr>
     <div>
        <h2>List Of All Admin:  {{admins.length}}</h2>
        <hr>

        <button type="button" @click="deleteAllAdmins" class="btn btn-danger">Delete All Admins</button>   
        <hr>  
        <admin-item v-for="admin in admins" :key="admin._id" :admin="admin" @delete-admin="deleteAdmin"></admin-item>
    </div>
  </div>
</template>
<script>
import { Api } from "@/Api";
import UserItem from "@/components/UserItem";
import AdminItem from "@/components/AdminItem";

export default {
		name:'Users',
		data (){
			return{
          users:[],
          admins: []
			}
    },
    mounted(){
        this.getAllUsers();
        this.getAllAdmins();
       // this.logInCheck();
    },
    methods: {
      getAllUsers(){
        Api.get('/admins/users')
        .then(response => {
          this.users = response.data.users
        })
        .catch(error => {
          this.users = []
          console.log(error)
        })
      },
       getAllAdmins(){
        Api.get('/admins/')
        .then(response => {
          this.admins = response.data.admins
        })
        .catch(error => {
          this.admins = []
          console.log(error)
        })
      },
    deleteUser(id) {
      Api.delete(`/users/${id}`)
        .then(response => {
          var index = this.users.findIndex(user => user._id === id)
          this.users.splice(index, 1)
          alert("User has been deleted");
        })
        .catch(error => {
          console.log(error)
        })

    },
    deleteAdmin(id) {
      Api.delete(`/admins/${id}`)
        .then(response => {
          var index = this.admins.findIndex(admin => admin._id === id)
          this.admins.splice(index, 1)
          alert("Admin has been deleted");
        })
        .catch(error => {
          console.log(error)
        })

    },
    deleteAllUsers(){
        Api.delete('/users')
        .then(response => {
          alert("Are you sure you want to delete all users ?")
          this.users = []
        })
        .catch(error => {
          console.log(error)
        })

      },
      deleteAllAdmins(){
        Api.delete('/admins')
        .then(response => {
          alert("Are you sure you want to delete all admins ?")
          this.admins = []
        })
        .catch(error => {
          console.log(error)
        })

      }
    },
    components:{
      UserItem,
      AdminItem
    }
}
</script>
<style scoped>
table, th, td{
    border: 1px solid black;
    padding: 5px;
}
table{
    border-spacing: 5px;
}

</style>
