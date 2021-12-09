<template>
    <div>       
        <table style="width: 100%">
            <tr>
                <th>Role</th>
                <th>Email Address</th>
                <th>Encrypted Password</th>
            </tr>
            <tr>
                <td>Admin</td>
                <td>{{admin.email}}</td>  
                <td>{{admin.password}}</td>            
            </tr>
            <b-button type="button" class="close"  @click="$emit('delete-admin', admin._id)">Delete Admin</b-button>

        </table>
    </div>
</template>

<script>

export default {
  name: 'admin-item',
  props: ['admin']
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