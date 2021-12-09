<template>
  <div id="review">
<blockquote>
    <i>"{{review.comment}}" </i>
    <br>
    <p id="rating">Rating: <span v-for="review in review.rating" :key="review._id">⭐</span></p>
    <h6>-{{review.reviewer.fName}} {{review.reviewer.lName}}</h6>
  </blockquote>
  </div>
</template>

<script>



    export default {
        name: 'review-item',
        props: ['review']
    }


</script>

<style scoped>

  #review{
    color: #3c3d47;
  }
  img{
    border-radius: 100%;
    width: 60px;
  }
  i{
    color: black;
  }
  #rating{
    font-weight: bold;
  }
</style>