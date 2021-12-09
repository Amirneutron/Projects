var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var productSchema = new Schema({
  name: { type: String },
  price: { type: Number },
  image: { type: String },
  content: { type: String }
});


module.exports = mongoose.model('products', productSchema);