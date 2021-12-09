<template>
  <div>
    <h1>User : {{active}}</h1>
    <hr>
    <button @click="getReviews"> Get my Reviews</button>
    <hr>
    <button @click="createReview"> Create a new review</button>
    <hr>
    <button @click="getReviews"> Delete my review(s)</button>
    <hr>
    <button @click="getAllProducts"> Get all products</button>
    <hr>
    <button @click="getReviews"> Edit my posted review</button>
    <hr>
    <button @click="getReviews">Delete my review(s)</button>
    <hr>
    <button @click="updateProfile"> Update my profile info</button>
    <hr>
    <button @click="deleteUser"> Delete my account</button>
    <hr>
    <button @click="logOut"> Log Out of my account</button>
    <hr>

  </div>
</template>

<script>
    import { Api } from '@/Api'

    export default{
        name: 'User-Profile',
        data (){
            return{
                title:'User',
                active: "Active",
                status: this.$route.params.id,
                Reviews:[]
            }
        }
        ,created(){
            this.userId = this.$route.params.userId
        }
        ,methods: {
            logOut(){
                document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                this.$router.push("/");
                },
            getReviews(){
                var userId = this.$route.params.userId;
                this.$router.push(`/users/${userId}/reviews/myReviews`);
            },
            getAllUsers(){
                this.$router.push({name: 'usersList'});
            },
            deleteUser(){
                Api.delete('/users/' + this.userId)
                    .then(response => {
                        alert("Account has been deleted");
                        this.$router.push({name: 'login'});
                    })
                    .catch(error => {
                        console.log(error)
                    })
            },
            getAllProducts(){
                 this.$router.push(`/products`)
            },
             createReview(){
                var userId = this.$route.params.userId;
                this.$router.push(`/users/${userId}/reviews`);
       },
            updateProfile(){
                var userId = this.$route.params.userId
                this.$router.push(`/users/${userId}/profile`)
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
