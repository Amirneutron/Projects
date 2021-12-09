<template>
    <div>
        <h2>List Of All Products : {{products.length}}</h2>
        <button type="button" class="btn btn-danger" @click="deleteAllProduct">Delete All Products</button>
        <hr>
        <b-list-group >
        <b-list-group-item ><product-view v-for="product in products" :key="product._id" :product="product" @delete-product="deleteProduct" @edit-product="editProduct" @view-product="viewProduct"></product-view></b-list-group-item>
        </b-list-group>
    </div>   
</template>

<script>
import { Api } from "@/Api";
import ProductView from "@/components/ProductView";

export default {
    name: 'ListProducts',
    data(){
        return{
            products: []
        }
    },
    mounted(){
        this.getAllProducts()
    },
    created(){
        this.adminId = this.$route.params.id
    },
    methods: {
        getAllProducts(){
            Api.get('/admins/'+ this.adminId + '/products')
            .then(response => {
            this.products = response.data.products  
            })
            .catch(error => {
            this.products = []
            console.log(error)
            })
      },
      deleteProduct(id){
          Api.delete('/admins/'+ this.adminId + '/products/' + id)
        .then(response => {
          console.log(response.data)
          var index = this.products.findIndex(product => product._id === id)
          this.products.splice(index, 1)
        })
        .catch(error => {
          console.log(error)
        })
      },
      editProduct(id){
          this.$router.push({name: 'editProduct', params:{id: id}});
      },
      viewProduct(id){
          this.$router.push('/products/' + id)
      },
      deleteAllProduct(){
          Api.delete('/admins/' + this.adminId + '/products')
        .then(response => {
          alert("Are you sure you want to delete all products ?")
          this.products = []
        })
        .catch(error => {
          console.log(error)
        })

      }
    },
    components: {
        ProductView

    }

}
</script>
<style scoped>
table, th, td{
    border: 1px solid black;
    padding: 5px;
}
table{
    border-spacing: 5px; 
}

</style>
