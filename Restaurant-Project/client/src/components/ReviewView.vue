<template>

  <div id="review">
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">{{review.reviewer.fName}}</h5>
          <small>Rating : {{review.rating}} </small>
        </div>
        <p class="mb-1"> Comment : {{review.comment}}</p>
        <!-- Date/Time -->
        <p>Posted on {{review.date}}</p>
        <button type="button" class="close" @click="$emit('delete-review', review._id)">DELETE REVIEW</button>
        <button type="button" class="close" @click="$emit('edit-review', review._id)">EDIT REVIEW</button>
        </a>
      </div>
  </div>
</template>
<script>

    import { Api } from '@/Api'

    export default {
        name: 'review-view',
        props: ['review']
    }
</script>

<style scoped>
  .close:hover{
    background-color: #dc3545;
  }
  #review{
    margin-top: 3rem;

  }
  .close{
    background-color: #dc3545;
    margin-right: 2em;
    border: 1px solid black;
    color: black;
  }

</style>
