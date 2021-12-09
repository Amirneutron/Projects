<template>
<div class="info">
  <div class="container">
   <div class="row">
    <div class="col-md-12" v-for="(product,index) in products" :key="index">
     <div v-if="productId == product._id">
      <h1>{{product.name}}</h1>
      <img id = "imageHolder" :src="'../images/' + product.image" v-bind:alt="product.image" style="width:300px;height:200px;">
      <h2> {{product.content}}</h2>
      <h2> {{product.price}} SEK</h2>
     </div>
    </div>
   </div>
  </div>
 </div>
</template>


<script>
import { Api } from '@/Api'

 export default{
  name:'info',
  data(){
   return{
    title:"Information Of Product",
    productId:this.$route.params.Pid,
    products: []
   }
  },
  mounted() {
    this.getProducts();
  },
  methods: {
     getProducts() {
        Api.get(`/products`)
        .then(response => {
        this.products = response.data.products
        }).catch(error => {
        alert("error here")
        })
            },
        }
 }
</script>
<style scoped>

</style>