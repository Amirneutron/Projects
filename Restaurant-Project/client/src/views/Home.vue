<template>
  <div>
    <div class="top-container">

      <h1>{{message}} <br><span>Trust me</span> </h1>
      <b-button @click="registerNew" id="sign-up" variant="danger">Register</b-button>
      <b-button @click="logIn" variant="outline-dark">Log in</b-button>
      <hr>

     <!-- <button><a href="/register" role="button" aria-pressed="true">SIGN UP</a></button>-->
    </div>
    <div class="middle-container">
    <p>Bro trust me we have the best food in the world. Our chefs pay is more than bill gates  and elon musk's net
      worth combined. You wont believe but thats actually true. If you have any doubts or wanna challenge us,
      then come try our chef's special: BUT PLEASE TIP AT YOUR OWN CARE. we won't be responsible if you end up tipping us with all that you have in your pocket!
    </p>
      <hr>
    </div>
    <div class="bottom-container">
      <h1>Reviews</h1>
      <p>We value what our customers have to say about our Restaurant and we are constantly after improving our services accordingly</p>
      <carousel :per-page="1"  :mouse-drag="false" :autoplay="true" :loop="true">


        <slide v-for="review in reviews" :key="review._id" >
          <review-item  :review="review"></review-item>
        </slide>

      </carousel>

    </div>
  </div>


</template>

<script>
// @ is an alias to /src
import { Api } from '@/Api'
import ReviewItem from '../components/ReviewItem'
import { Carousel, Slide } from 'vue-carousel';
export default {
  name: 'home',
  data() {
    return {
      message: '',
      reviews: [],
      cooky: false

    }
  },
  mounted() {
    this.getMessage()
    this.getReviews()
  },
  methods: {
      registerNew(){
          this.$router.push("/register");
      },
      logIn(){
          this.$router.push("/login");
      },getReviews(){
          Api.get('/reviews')
              .then(response => {
                  this.reviews=response.data;

              })
              .catch(error => {
                  this.message = error
              })
      },
    getMessage() {
      Api.get(`/`)
        .then(response => {
          this.message = response.data.message
        })
        .catch(error => {
          this.message = error
        })
    }
  },
  components: {
      ReviewItem,Carousel,
      Slide
  }
}
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Montserrat|Neucha&display=swap');
*{
  font-family: 'Montserrat', sans-serif;
}

footer{
  background-color: #3c3d47;
  padding: 40px;
  color: white;
}
button{
  padding: 0.5rem 1rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
  margin-right: 2rem;

}
  img{
    position: relative;
    width:60%;
    height:600px;
    filter: blur(3px);
    transform: rotate(20deg);
  }
  .middle-container h2{
    color: white;
    font-weight: bold;
    font-size: 3rem;
    font-family: 'Permanent Marker', cursive;
  }
.top-container{
  padding: 5rem;

}
  h1 {
    text-align: center;
    margin: auto;
  }
</style>
