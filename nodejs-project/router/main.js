import mongoose from "mongoose";
const express = require('express');
const router = express.Router();


router.get('/version', function (req, res, next) {
    res.status(200).send({
        title: "Node Express API",
        version: "0.0.1"
    });
});


router.get('/userFake',function(req,res){
   res.render('index',{user: "John Smith",title:"homepage"});
});


router.post("/add", (req, res, next) => {
  const user = new User({
        _id: mongoose.Types.ObjectId(),
        name: req.body.name,
        address:req.body.address,
    });

    user.save()
    .then(result => {
        res.status(200).json({
            docs:[user]
        });
    })
    .catch(err => {
        console.log(err);
    });
});
module.exports = router;
