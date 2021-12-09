<template>
    <div>
        
        <table style="width: 100%">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Mobile Number</th>
                <th>Email Address</th>
            </tr>
            <tr>
                <td>{{user.fName}}</td>
                <td>{{user.lName}}</td>
                <td>{{user.mobileNumber}}</td>
                <td>{{user.email}}</td>             
            </tr>
            <b-button type="button" class="close"  @click="$emit('delete-user', user._id)">Delete User</b-button>

        </table>
    </div>
</template>

<script>

export default {
  name: 'user-item',
  props: ['user']
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