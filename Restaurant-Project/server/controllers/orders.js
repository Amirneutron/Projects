var express = require('express');
var router = express.Router();
var Order = require('../models/order');
var Admin = require('../models/admin');


//list all orders
router.get('/', function (req, res, next) {
  Order.find(function (err, order) {
    if (err) { return next(err); }
    res.json({ 'orders': order });
  });
});

//Create a new  order
router.post('/', function (req, res, next) {
  let userId = req.params._id;
  var newOrder = Order({
    orderedBy: userId
  });
  newOrder.save(function (err) {
      if (err) { return next(err); }
      res.status(201).json(newOrder);
    });
  });

// Update a specific order
router.patch('/:id', function(req, res, next){
    var id = req.params.id;
    var product = req.body.products;
    console.log(product);
    
    Order.update({ _id: id }, {
      $push: { products: product }}, function(err, order) {
        if (err) return next(err);
        if (order == null) {
          return res.status(404).json({ 'message': 'Order not found' });
      }
        });  
  var quantity = req.body.quantity;
  Order.update({ _id: id }, {
    $push: { quantity: quantity }
  }, function (err, order) {
    if (err) return next(err);
    if (order == null) {
      return res.status(404).json({ 'message': 'Order not found' });
    }
    res.status(201).json(order);
  });  
});

//Get order with the specific Id
router.get('/:id', function (req, res, next) {
  var id = req.params.id;
  return Order.findOne({ _id: id }).populate('products').exec((err, order) => {
    res.json(order);
  });
}); 

// Delete an order by its ID
router.delete('/:id', function (req, res, next) {
  let adminId = req.adminId;
  let id = req.params.id;
  Admin.findById(adminId, function (err, admin) {
    if (err) { return next(err); }
    if (admin === null) {
      return res.status(404).json({ 'message': 'No Product found' });
    }
    Order.findOneAndDelete({ _id: id }, function (err, order) {
      if (err) { return next(err); }
      if (order === null) {
        return res.status(404).json({ 'message': 'Products not found' });
      }
      res.json(order);
    });
    
  });
});

module.exports = router;