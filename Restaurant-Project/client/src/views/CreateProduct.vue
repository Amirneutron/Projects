<template>
    <div class="container">
        <form class="create-product" @submit="createProduct">
            <label for="name"><b>Product name</b></label>
            <input type="text" placeholder=" Enter product name" v-model="name" required>
            <label for="price"><b>Product price</b></label>
            <input type="number" placeholder=" Enter product price in SEK" v-model="price" required>
            <label for="image"><b>Select product image</b></label>
            <!--<input type="text" placeholder=" Enter product image" v-model="image" required>-->
            <select type="file" name="image" v-model="image">
                <option>spicy.jpg</option>
                <option>kebabPizza.jpg</option>
                <option>gardenClassicPizza.jpg</option>
                <option>hawaii.jpg</option>
                <option>margaritaPizza.jpg</option>
                <option>pepperoniPizza.jpg</option>
                <option>tropical.jpg</option>
                <option>veggiePizza.jpg</option>
                <option>hawaii.jpg</option>
                <option>bbqChickenPizza.jpg</option>
            </select>-->
            <br>
            <label for="content"><b>Product content</b></label>
            <input type="text" placeholder="Enter product content" v-model="content" required>

            <hr>
               <button type="submit" class="createButton">Create Product</button>

        </form>
    </div>
</template>

<script>
import { Api } from '@/Api';

export default {
      name: 'createProduct',
      data(){
          return {
                products: [],
                name: '',
                price: '',
                image: '',
                content: ''
          }
      },mounted(){
        this.logInCheck();
    },
      created(){
          this.adminId = this.$route.params.id
      }
      ,methods:{
            createProduct(){
                Api.post('/admins/' + this.adminId + '/products',{
                    name: this.name,
                    price: this.price,
                    image: this.image,
                    content: this.content
                }).then(response=>{
                    
                    alert("Product has been created");
                    this.$router.push(`/admins/${this.adminId}`);
                }).catch(error=>{
                    this.products = []
                    console.log(error);
                });
            },logInCheck(){

            if(document.cookie === ""){
                this.$router.push('/adminLogin');
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
.createButton {
  background-color: #dc3545;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

</style>
