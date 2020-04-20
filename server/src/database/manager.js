const { Pool } = require("pg");

const config = {
    user : 'postgres',
    host : '181.55.121.253',
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


//--------------------------------------------------- USERS
const newUser = async(req, res)=>{
    try {
        console.log(req.body);
    } catch (e) {
        console.log(e);
    }    
} 

const editUser = async(req, res)=>{
    try {
        console.log(req.body);
    } catch (e) {
        console.log(e);
    }    
} 

const deleteUser = async(req, res)=>{
    try {
        console.log(req.body);
    } catch (e) {
        console.log(e);
    }    
} 


//------------------------------------------------------- Events
const getEvents = async(req, res)=>{
    try {
        console.log(req.body);
        let str_query = `SELECT * FROM EVENTOS`;
        const response = await pool.query(str_query);
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }    
} 

const newEvents = async(req, res)=>{
    try {
        console.log("Evento:\n" + JSON.stringify(req.body));
        console.log(req.file);
        //const response = await pool.query("SELECT * FROM EVENTOS");
        //console.log(response);
        res.send('Evento Agregado');
    } catch (e) {
        console.log(e);
    }    
} 

//------------------------------------------------------- Actividades
//------------------------------------------------------- Municipios
const getMuni = async(req, res)=>{
    try {
        console.log("Municipio:\n" + req.body);
        let str_query = `SELECT * FROM MUNICIPIOS`;
        const response = await pool.query(str_query);
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }    
} 

const newMuni = async(req, res)=>{
    try {
        console.log("Municipio:\n" + req.body);
        let json = req.body;
        let id = new Date();
        id = id.getTime();
        let str_query = `INSERT INTO MUNICIPIOS(ID_MUNICIPIO, NOM_MUNICIPIO, LATITUD, LONGITUD) VALUES
        ('${id}', '${json.nom_municipio}', '${json.latitud}', '${json.longitud}')`;
        const response = await pool.query(str_query);
        console.log(response);
        res.send("INSERTED");
    } catch (e) {
        console.log(e);
    }    
} 

const editMuni = async(req, res)=>{
    try {
        console.log("Municipio:\n" + req.body);
    } catch (e) {
        console.log(e);
    }    
} 

const deleteMuni = async(req, res)=>{
    try {
        console.log("Municipio:\n" + req.body);
        let json = req.body;
        let str_query = `DELETE FROM MUNICIPIOS WHERE ID_MUNICIPIO LIKE '${json.id_municipio}'`;
        const response = await pool.query(str_query);
        console.log(response);
        res.send("DELETED");
    } catch (e) {
        console.log(e);
    }    
} 



module.exports = {
    validateUser,
    getUsers,
    getRoutes, 

    newUser, 
    editUser,
    deleteUser,

    getMuni,
    newMuni, 
    editMuni,
    deleteMuni,

    getEvents,
    newEvents
}

