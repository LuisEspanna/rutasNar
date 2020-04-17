/*


const { Pool } = require("pg");

const config = {
    user : 'postgres',
    host : '192.168.219.71',
    password : '1234',
    database : 'RutasNar'
};

const pool = new Pool(config);

const getRutas = async() => {
    try {
        const res = await pool.query("select * from usuarios;");
        console.log(res.rows);
        //Quitar despues
        pool.end();
    }catch (e){
        console.log(e);
    }
}

getRutas();
*/

const express = require("express");
const app = express();
const port = 8880;

//midleware
app.use(express.json());
app.use(express.urlencoded({extended:false}));


//Routes
app.use(require('./routes/routes.js'));

app.listen(port);
