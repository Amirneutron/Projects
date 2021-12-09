<template>
<div id="productV">
  <div class="flip-card">
    <div class="flip-card-inner">
      <div class="flip-card-front">
        <h3>{{product.name}}</h3>
        <img id = "imageHolder" :src="'../images/' + product.image" v-bind:alt="product.image" style="width:300px;height:200px;">
      </div>
      <div class="flip-card-back">
        <p class="currencyInfo">{{ product.price }} <span id="currencyTag">SEK</span></p>
        <p>{{ product.content }}</p>
        <b-button :href="'/products/' + product._id" >View product</b-button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { Api } from '@/Api'

    export default {
        name: 'product-item',
        props: ['product']
    }

</script>

<style scoped>
  /*img {
    width: 33px;
    margin-right: 10px;
  }*/
  /* The flip card container - set the width and height to whatever you want. We have added the border property to demonstrate that the flip itself goes out of the box on hover (remove perspective if you don't want the 3D effect */
  .flip-card {
    background-color: transparent;
    width: 300px;
    height: 200px;
    border: 1px solid #f1f1f1;
    perspective: 1000px; /* Remove this if you don't want the 3D effect */
  }

  /* This container is needed to position the front and back side */
  .flip-card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    text-align: center;
    transition: transform 0.8s;
    transform-style: preserve-3d;
  }

  /* Do an horizontal flip when you move the mouse over the flip box container */
  .flip-card:hover .flip-card-inner {
    transform: rotateY(180deg);
  }

  /* Position the front and back side */
  .flip-card-front, .flip-card-back {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
  }

  /* Style the front side (fallback if image is missing) */
  .flip-card-front {
    background-color: #bbb;
    color: black;
  }

  /* Style the back side */
  .flip-card-back {
    background-color: #dc3545;
    color: white;
    transform: rotateY(180deg);
  }
  h3{
    color: white;
    position: absolute;
    text-align: center;
    background-color: rgba(0,0,0, 0.4);
    margin: auto;
  }
  #currencyTag{
    font-size: 1rem;
    color: black;
  }
  .currencyInfo{
    margin-top: 2rem;
    font-size: 1.5rem;
    font-weight: bold;
  }
  button{
    border: 1px solid black;
    background-color: #dc3545;
    color: white;
  }
  button:hover{
    background-color: #3c3d47;
  }
  #productV{
    margin-top: 3rem;

  }

</style>
