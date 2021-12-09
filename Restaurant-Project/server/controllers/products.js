var express = require('express');
var router = express.Router();
var Product = require('../models/product');
var Admin = require('../models/admin');

var ordersController = require('./orders');

//Return a list of all the products on the panel
router.get('/', function (req, res, next) {
  Product.find(function (err, product) {
    if (err) { return next(err); }
    res.json({ 'products': product });
  });
});

//Create a new product
router.post('/', function (req, res, next) {
  let adminId = req.adminId;
  var product = new Product(req.body);
  Admin.findById(adminId, function(err, admin){
    if (err) { return next(err); }
    if (admin === null) {
      return res.status(404).json({ 'message': 'No Admin found' });
    }
    product.save(function (err) {
      if (err) { return next(err); }
      res.status(201).json(product);
  }); 
  });
});

//Return a product with the given id
router.get('/:id', function (req, res, next) {
  var id = req.params.id;
  Product.findById(id, function (err, product) {
    if (err) { return next(err); }
    if (product === null) {
      return res.status(404).json({ 'message': 'No Product found' });
    }
    res.json(product);
  });
});

// Update a product 
router.put('/:id', function (req, res, next) {
  var id = req.params.id;
  Product.findById(id, function (err, product) {
    if (err) {
      return next(err);
    }
    if (product == null) {
      return res.status(404).json({ 'message': 'Product not found' });
    }
    product.name = req.body.name;
    product.price = req.body.price;
    product.image = req.body.image;
    product.content = req.body.content;
    product.save();
    res.json(product);
  }
  );
});

// Update product info
router.patch('/:id', function (req, res, next) {
  var id = req.params.id;
  Product.update({ _id: id }, { $set: req.body }, function (err, product) {
    if (err) { return next(err); }
    if (product == null) {
      return res.status(404).json({ 'message': 'Product not found' });
    }
    Product.findById(id, function (err, product) {
      if (err) { return next(err); }
      if (product === null) {
        return res.status(404).json({ 'message': 'No Product found' });
      }
      res.json(product);
    });

    
  });
});

//Delete all products
router.delete('/', function (req, res, next) {
  Product.remove({}, function (err, product) {
    if (err) { return next(err); }
    res.json(product);
  });
});

// Delete a product by its ID
router.delete('/:id', function (req, res, next) {
  var id = req.params.id;
  Product.findOneAndDelete({ _id: id }, function (err, product) {
    if (err) { return next(err); }
    if (product === null) {
      return res.status(404).json({ 'message': 'Products not found' });
    }
    res.json(product);
  });
});

router.use('/:id/orders', function (req, res, next) {
  req.productId = req.params.id;
  next();
}, ordersController);


module.exports = router;

