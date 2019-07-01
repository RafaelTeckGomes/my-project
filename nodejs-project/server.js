var express    =    require('express');
var path = require('path');
var app        =    express();
var routes =  require('./router/main');



app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);
app.use('/', routes);


var server =  app.listen(3000,function(){
    console.log("Started server on port 3000");
});
