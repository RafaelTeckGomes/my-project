const express = require('express');
const router = express.Router();

router.get('/',function(req,res){
   res.render('index',{user: "John Smith",title:"homepage"});
});

router.get('/version', function (req, res, next) {
    res.status(200).send({
        title: "Node Express API",
        version: "0.0.1"
    });
});
module.exports = router;
