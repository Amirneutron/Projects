<template>
    <div class="container">
        <form class="create-review" @submit="postReview">
            <label for="rating"><b>Please rate your review</b></label>
            <select type="number" name="rating" v-model="rating">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <br>
            <label for="comment"><b>Comment: </b></label>
            <input type="text" placeholder="Enter review comment:" v-model="comment" required>
            <hr>
            <button type="submit" class="postReview">Post Review</button>
        </form>     
    </div>
</template>

<script>
import { Api } from '@/Api';

export default {
      name: 'createReview',
      data(){
          return {
                reviews: [],
                rating: '',
                comment: ''
               
          }
      },
      created(){
          this.userId = this.$route.params.userId
      }
      ,methods:{
            postReview(){
                Api.post('/users/' + this.userId + '/reviews',{
              
                    rating: this.rating,
                    comment: this.comment
                }).then(response=>{
                    alert("Review has been posted");
                    this.$router.push({name: 'userPortal'});
                }).catch(error=>{
                    this.reviews = []
                    console.log(error);
                });
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
.postReview {
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