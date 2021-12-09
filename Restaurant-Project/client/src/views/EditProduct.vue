<template>
  <div class="container">
    <h1>Edit : <span>{{ product.name }}</span></h1>
    <form @submit.prevent="editProduct">
      <label for="name"><b>Change product name</b></label>
      <input v-model="newProduct.name" class="input" type="text" :placeholder="product.name">
      <label for="name"><b>Change product price</b></label>
      <input v-model="newProduct.price" class="input" type="number" :placeholder="product.price">
      <label for="name"><b>Select product image</b></label>
      <select v-model="newProduct.image" name="image" >
                <option>spicy.jpg</option>
                <option>kebabPizza.jpg</option>
                <option>gardenClassic.jpg</option>
                <option>hawaii.jpg</option>
                <option>margaritaPizza.jpg</option>
                <option>pepperoniPizza.jpg</option>
                <option>tropical.jpg</option>
                <option>veggiePizza.jpg</option>
                <option>hawaii.jpg</option>
                <option>bbqChickenPizza.jpg</option>             
      </select>
      <hr>
      <label for="name"><b>Change product content</b></label>
      <input v-model="newProduct.content" class="input" type="text" :placeholder="product.content">
      <b-button class="editButton" type="submit" >Update Product Item</b-button>
    </form>
  </div>
</template>

<script>
import { Api } from '@/Api'
export default {
  name: 'EditProduct',
  data() {
    return {
      product: {},
      newProduct: {}
    }
  },
  created() {
    this.productId = this.$route.params.id
  },
  mounted() {
    this.getProduct()
  },
  methods: {
    getProduct() {
      Api.get('/products/' + this.productId)
        .then(response => {
          this.product = response.data
        })
        .catch(error => {
          this.product = null
          console.log(error)
        })
        .then(() => {
          // This code is always executed (after success or error).
        })
    },
    editProduct() {
      if (
        this.newProduct.name &&
        this.newProduct.price &&
        this.newProduct.image &&
        this.newProduct.content
      ){
        Api.put('/products/' + this.productId, this.newProduct)
          .then(response => {
            console.log(response.data)
            alert("The product has been updated successfully")
            this.$router.push('/products/' + this.productId)
          })
          .catch(error => {
            console.log(error)
          })
      }else{
        Api.patch('/products/' + this.productId, this.newProduct)
          .then(response => {
            console.log(response.data)
            alert("The product has been updated successfully")
            this.$router.push('/products/' + this.productId)
          })
          .catch(error => {
            console.log(error)
          })

      }   
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
.editButton {
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