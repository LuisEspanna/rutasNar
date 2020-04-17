const { Pool } = require("pg");

const config = {
    user : 'postgres',
    host : '172.18.2.21',
    password : '1234',
    database : 'RutasNar'
};

const pool = new Pool(config);

const validateUser = async(req, res)=>{
    try {
        let jsonInfo = req.body;
        if(jsonInfo.header != null && jsonInfo.user != null && jsonInfo.password != null){
            if(jsonInfo.header == 'validate_user'){
                console.log(req.body);
                let str_query = `SELECT * FROM USUARIOS WHERE nom_usuario like '${jsonInfo.user}' and clave_usuario like '${jsonInfo.password}'`;
                //console.log(str_query);
                const response = await pool.query(str_query);
                console.log(response.rows[0]);
                if(response.rows[0] === undefined){
                    res.status(400).send("404"); 
                }else res.status(200).json(response.rows[0].id_usuario);
            }
        }else res.send("Error");
    } catch (e) {
        console.log(e);
    }    
} 

const getUsers = async(req, res)=>{
    try {
        let str_query = `SELECT * FROM USUARIOS`;
        console.log(str_query);
        const response = await pool.query(str_query);
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }    
} 

const getEvents = async(req, res)=>{
    try {
        let jsonInfo = req.body;
        if(jsonInfo.header != null && jsonInfo.user != null && jsonInfo.password != null){
            console.log(req.body);
            let str_query = `SELECT * FROM USUARIOS WHERE nom_usuario like '${jsonInfo.user}' and clave_usuario like '${jsonInfo.password}'`;
            const response = await pool.query(str_query);
            res.status(200).json(response.rows);
        }else res.send("Error :(");
    } catch (e) {
        console.log(e);
    }    
} 

const getRoutes = async(req, res)=>{
    try {
        let jsonInfo = req.body;
        if(jsonInfo.header != null && jsonInfo.user != null && jsonInfo.password != null){
            console.log(req.body);
            let str_query = `SELECT * FROM USUARIOS WHERE nom_usuario like '${jsonInfo.user}' and clave_usuario like '${jsonInfo.password}'`;
            const response = await pool.query(str_query);
            res.status(200).json(response.rows);
        }else res.send("Error :(");
    } catch (e) {
        console.log(e);
    }    
} 


//--------------------------------------------------- DATA
const newData = async(req, res)=>{
    try {
        console.log(req.body);
    } catch (e) {
        console.log(e);
    }    
} 

const editData = async(req, res)=>{
    try {
        console.log(req.body);
    } catch (e) {
        console.log(e);
    }    
} 


module.exports = {
    validateUser,
    getUsers,
    getEvents,
    getRoutes, 
    newData, 
    editData
}

