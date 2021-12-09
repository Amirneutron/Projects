<template>
  <div>
    <h2>My reviews :  {{reviews.length}}</h2>
    <button type="button" class="btn btn-danger" @click="deleteAllReviews">Delete All Reviews</button>
        <hr>   
    <b-list-group >
      <b-list-group-item ><review-view v-for="review  in reviews" :key="review._id" :review="review" @delete-review="deleteReview" @edit-review="editReview"></review-view></b-list-group-item>
    </b-list-group>
  </div>

</template>

<script>
    import { Api } from '@/Api'
    import ReviewView from "../components/ReviewView"

    export default {
        name: 'Reviews',
        data() {
            return {
                reviews: []
            }
        } ,
        created(){
            this.userId = this.$route.params.userId
        },
        mounted() {
            this.getReviews();
        },
        methods: {
            getReviews() {
                Api.get('/users/' + this.userId + '/reviews/myReviews').then(response=>{
                    this.reviews= response.data;
                }).catch(error=>{
                    console.log(error);
                })
            },
            deleteReview(id){
                Api.delete('/users/'+ this.userId + '/reviews/' + id)
                    .then(response => {
                        console.log(response.data)
                        var index = this.reviews.findIndex(review => review._id === id)
                        this.reviews.splice(index, 1)
                    })
                    .catch(error => {
                        console.log(error)
                    })
            },
            editReview(id){
                this.$router.push(`/users/${this.userId}/reviews/${id}`);
            },
            deleteAllReviews(){
              Api.delete('/users/' + this.userId + '/reviews')
            .then(response => {
              alert("Are you sure you want to delete all reviews ?")
              this.reviews = []
            })
            .catch(error => {
              console.log(error)
            })
      }
        },
        components: {
            ReviewView
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .menu-style{
    width: 50%;
    text-align:center;
    justify-content: center;
    margin: auto;
    font-size: 2rem;
  }
  .menu-style span{
    color: #3c3d47;
    font-weight: bold;
    font-size: 1rem;
  }
  img{
    width: 10%;
    border-radius: 100%;
  }
  .price{
    color:#dc3545;
    font-size: 1.5rem;
    font-weight: bold;
  }


</style>
