<template>
  <div>
    <h1>Admin : {{active}}</h1>
    <hr>  
    <button @click="getAllUsers"> Get all users & Admins</button>
    <hr>
    <button @click="getAllUsers"> Delete a specific user</button>
    <hr>
    <button @click="createProduct"> Create a new product</button>
    <hr>
    <button @click="getAllProducts"> Get all products</button>
    <hr>
    <button @click="getAllProducts"> Edit product</button>
    <hr>
    <button @click="getAllProducts">Delete a product</button>   
    <hr>
    <button @click="deleteAdmin"> Delete admin my account</button>
    <hr>
    <button @click="logOut"> Log Out of my account</button>
    <hr>
  </div>
</template>

<script>
import { Api } from '@/Api'
export default{
    name: 'Master',
		data (){
			return{
        title:'Master',
        active: "Active",
        status: this.$route.params.id
      }
    }
    ,created(){
        var adminId = this.$route.params.id
        this.id = this.$route.params.id
    },mounted(){
        this.logInCheck();
    }
    ,methods: {
      logOut(){
        document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        this.$router.push("/");
        },
      logInCheck(){
        if(document.cookie === ""){
        this.$router.push('/adminLogin');
     }
      },
      getAllUsers(){
        this.$router.push({name: 'usersList'});
      },
      createProduct(adminId){
        this.$router.push({name: 'createProduct'});
      },
      deleteAdmin(){
        Api.delete('/admins/' + this.id)
        .then(response => {
          console.log(response.data.message)
          alert("Account has been deleted");
          this.$router.push({name: 'adminLogin'});
        })
        .catch(error => {
          console.log(error)
        })
      },
      getAllProducts(){
        var adminId = this.$route.params.id
        this.$router.push(`/admins/${adminId}/menus`)
      }
    }
	}
</script>

<style scoped>
button{
  position: relative;
  top: 20%;
  background-color: #dc3545;
  border: 2px solid white;
  font-size: 1rem;
  padding:0.5rem 1.5rem;
  color: aliceblue
}
</style>