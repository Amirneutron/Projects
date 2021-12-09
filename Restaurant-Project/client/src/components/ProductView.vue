<template>

    <div id="product">
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">{{product.name}}</h5>
          <small>{{product.price}} SEK</small>
        </div>
        <p class="mb-1">{{product.content}}</p>
        <button type="button" class="close" @click="$emit('delete-product', product._id)">DELETE</button>
        <button type="button" class="close" @click="$emit('edit-product', product._id)">EDIT PRODUCT</button>
        <button type="button" class="close" @click="$emit('view-product', product._id)">VIEW PRODUCT</button>
        </a>
      </div>
  </div>
</template>
<script>

import { Api } from '@/Api'

export default {
    name: 'product-view',
    props: ['product']
}
</script>

<style scoped>
  .close:hover{
    background-color: #dc3545;
  }
  #product{
    margin-top: 3rem;

  }
 .close{
  background-color: #dc3545;
  margin-right: 2em;
  border: 1px solid black;
  color: black;
}

</style>
