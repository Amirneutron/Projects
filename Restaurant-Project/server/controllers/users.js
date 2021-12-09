

var express = require('express');
var router = express.Router();
var User = require('../models/user');
var md5 = require('md5');
var ordersController = require('./orders');
var Review =require('../models/review');

var productsController = require('./products');
var reviewsController = require('./reviews');

// Create a new user
router.post('/register', function (req, res, next) {
  var newUser = new User({
    fName: req.body.fName,
    lName: req.body.lName,
    email: req.body.email,
    mobileNumber: req.body.mobileNumber,
    address: req.body.address,
    password: md5(req.body.password)
  });
  newUser.save(function (err) {
    if (err) { return next(err); }
    res.status(201).json(newUser);
  });
});

// Return a list of all Users
/*router.get('/', function (req, res, next) {
  User.find(function (err, user) {
    if (err) { return next(err); }
    res.json({ 'users': user });
  });
});*/

// Login an existing User
router.post('/login', function (req, res) {
  const email = req.body.email;
  const password = md5(req.body.password);
  User.findOne({ email: email }, function (err, foundUser) {
    if (err) {
      console.log(err);
    }
    else {
      if (foundUser) {
        if (foundUser.password === password) {
          res.status(200).json(foundUser);
         // res.status(200).json({'message': 'You are now logged in'});
        } else {
          return res.status(404).json({ 'message': 'Incorrect Password ' });
        }
      } else {
        return res.status(404).json({ 'message': 'Incorrect Email' });
      }
    }
  });

});

//update user info with a given id
router.put('/:id', function (req, res, next) {
  var id = req.params.id;
  User.findById(id, function (err, user) {
    if (err) {
      return next(err);
    }
    if (user == null) {
      return res.status(404).json({ 'message': 'User not found' });
    }
    user.fName = req.body.fName;
    user.lName = req.body.lName;
    user.email = req.body.email;
    user.mobileNumber = req.body.mobileNumber;
    user.save();
    res.json(user);
  });
});

// Update user password
router.patch('/:id/password', function (req, res, next) {
  var id = req.params.id;
  User.findById(id, function (err, user) {
    if (err) {
      return next(err);
    }
    if (user == null) {
      return res.status(404).json({ 'message': 'User not found' });
    }
    user.password = md5(req.body.password);
    user.save();
    res.json(user);
  }
  );
});

// Delete the user with the given ID
router.delete('/:id', function (req, res, next) {
  var id = req.params.id;
  User.findOneAndDelete({ _id: id }, function (err, users) {
    if (err) { return next(err); }
    if (users === null) {
      return res.status(404).json({ 'message': 'Unsuccessful operation' });
    }
    Review.deleteMany({reviewer:id},function(err,review){
      if (err) { return next(err); }
      if (review === null) {
        return res.status(200).json({ 'Message': 'The user had no reviews' });
      }

    });

    res.json(users);
  });
});

//Return a user with the given id
router.get('/:id', function (req, res, next) {
  var id = req.params.id;
  User.findById(id, function (err, user) {
    if (err) { return next(err); }
    if (user === null) {
      return res.status(404).json({ 'message': 'No User found' });
    }
    res.json(user);
  });
});

//Delete all users
router.delete('/', function(req, res, next) {
  User.remove({}, function(err,response) {
      if (err) { return next(err); }
      res.json(response);
  });
});

//nested request
router.use('/:id/products/orders', function (req, res, next) {
  req.userId = req.params.id;
  next();
}, ordersController);

router.use('/:id/products', function (req, res, next) {
  req.userId = req.params.id;
  next();
}, productsController);

router.use('/:id/reviews', function (req, res, next) {
  req.userId = req.params.id;
  next();
}, reviewsController);

module.exports = router;

