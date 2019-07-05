import mongoose from "mongoose";

mongoose.connect(
  "url_DB",
    {
      useNewUrlParser: true
    }
  );

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  // Database connected!!!
  console.log("Connected to MongoDB")
});

module.exports = db;
