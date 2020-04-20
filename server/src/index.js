const express = require("express");
const app = express();
var body_parser = require('body-parser');
const morgan = require('morgan');
const multer = require('multer');
const path = require('path');

const port = 8880;

const storage = multer.diskStorage({
    destination:path.join(__dirname,'public/uploads'),
    filename : (req, file, cb) => {
        cb(null, file.originalname);
    }
});

//midleware
app.use(express.static(__dirname));
app.use(body_parser.urlencoded({extended:true}));
app.use(express.json());

app.use(morgan('dev'));

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Authorization, X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Allow-Request-Method');
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
    res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
    next();
});

app.use(multer({
    storage,
    dest: path.join(__dirname,'public/uploads')
}).single('imagen'));

//Routes
app.use(require('./routes/routes.js'));

app.listen(port);
