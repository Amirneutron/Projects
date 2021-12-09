var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var orderSchema = new Schema({
  orderedBy: { type: Schema.Types.ObjectId, ref: 'users' },
  products: [{ type: Schema.Types.ObjectId,ref:'Products' }],
  quantity: [{ type: Number }]
});


module.exports = mongoose.model('orders', orderSchema);
