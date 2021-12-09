var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var reviewSchema = new Schema({
  reviewer: {type: Schema.Types.ObjectId, ref: 'users'},
  date: { type: Date, default: Date.now() },
  rating: { type: Number, required: true },
  comment: { type: String, required: true }
});


module.exports = mongoose.model('Review', reviewSchema);