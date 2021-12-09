<template>
<div>

       <b-container class="bv-example-row">
         <b-row>
         <b-col v-for="product in products" :key="product._id">
           <product-item :product="product"></product-item>
         </b-col>
       </b-row>
       </b-container>

</div>

</template>

<script>
import { Api } from '@/Api'
import ProductItem from '../components/ProductItem'

    export default {
        name: 'Products',
        data() {
            return {
                products: []
            }
         } ,
        mounted() {
            this.getProducts();
        },
        methods: {
            getProducts() {
                Api.get('/products')
                    .then(response => {
                        this.products = response.data.products
                    })
                    // eslint-disable-next-line handle-callback-err
                    .catch(error => {
                        alert('error here')
                    })
            },
        },
        components: {
            ProductItem
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
