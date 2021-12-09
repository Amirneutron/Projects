import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import Contact from './views/Contact.vue'
import Products from './views/Products.vue'
import Info from './views/Info.vue'
import Master from './views/Master.vue'
import AdminLogin from './views/AdminLogin.vue'
import UsersList from './views/UsersList.vue'
import CreateProduct from './views/CreateProduct.vue'
import AdminRegister from './views/AdminRegister.vue'
import ProductsList from './views/ProductsList.vue'
import UserPortal from "./views/UserPortal.vue"
import Review from './views/Review.vue'
import EditReview from './views/EditReview.vue'
import EditProduct from './views/EditProduct.vue'
import MakeReview from './views/MakeReview.vue'
import EditProfile from './views/EditProfile.vue'



Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    { path: '/',
      name:'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    },
    {
      path: '/products',
      name: 'products',
      component: Products
    },
    {
      path: '/products/:Pid',
      name: 'info',
      component: Info
    },
    {
      path: '/admins/:id',
      beforeEnter:checkAuth,
      name: 'master',
      component: Master
    },
    {
      path: '/adminLogin',
      name: 'adminLogin',
      component: AdminLogin
    },
    {
      path: '/registerAdmin',
      name: 'registerAdmin',
      component: AdminRegister
    },
    {
      path: '/admins/:id/users',
      beforeEnter:checkAuth,
      name: 'usersList',
      component: UsersList
    },
    {
      path: '/admins/:id/products',
      beforeEnter:checkAuth,
      name: 'createProduct',
      component: CreateProduct
    },
    {
      path: '/admins/:id/menus',
      beforeEnter:checkAuth,
      name: 'productList',
      component: ProductsList
    },
    {
    path:'/users/:userId',
      name: 'userPortal',
      component: UserPortal
    },
    {
      path: '/users/:userId/reviews/myReviews',
      name: 'showReviews',
      component: Review
    },
    {
      path: '/products/:id/edit',
      name: 'editProduct',
      component: EditProduct
    },
    {
      path: '/users/:userId/reviews/:id',
      name: 'editReview',
      component: EditReview
    },
    {
      path: '/users/:userId/reviews',
      name: 'createReview',
      component: MakeReview
    },
    {
      path: '/users/:userId/profile',
      name: 'editProfile',
      component: EditProfile
    }
  ]

});
function checkAuth(to, from, next) {

  if (document.cookie ==="") {
    next('/')
  } else {
    next(); // we are authorized, continue on to the requested route
  }
}
