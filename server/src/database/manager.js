const { Pool } = require("pg");

const config = {
    user : 'postgres',
    host : 'localhost',
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
        res.setHeader('Content-Type', 'application/json');
        res.status(200);
        res.json(response.rows);
    } catch (e) {
        console.log(e);
    }    
} 

const newEvents = async(req, res)=>{
    try {
        //console.log("Evento:\n" + JSON.stringify(req.body));
        let json = req.body;
        let id = new Date();
        id = id.getTime();
        let str_query = `INSERT INTO 
        EVENTOS(id_evento, id_municipio, nom_evento, desc_evento, img_evento, fecha_evento, disponible, latitud, longitud) VALUES 
        ('${id}','${json.id_municipio}','${json.nom_evento}','${json.desc_evento}','${req.file.originalname}','${json.fecha_evento}','${json.disponible}','${json.latitud}', '${json.longitud}');`;
        //console.log(req.file);
        const response = await pool.query(str_query);
        console.log(response);
        res.render('regresar.ejs', {title:'Evento', message:"Evento creado exitosamente", str_url:'/eventos'});
    } catch (e) {
        console.log(e);
    }    
} 

const deleteEvents = async(req, res)=>{
    try {
        
        let str_query = `DELETE FROM EVENTOS WHERE id_evento like '${req.body.id_evento}'`;
        //console.log(req.file);
        const response = await pool.query(str_query);
        console.log(response);
        res.render('regresar.ejs', {title:'Evento', message:"Evento eliminado exitosamente", str_url:'/eventos'});
    } catch (e) {
        console.log(e);
    }    
} 

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

//--------------------------------------------------------------------------Rutas
const getRoutes = async(req, res)=>{
    try {
        let str_query = `SELECT * FROM RUTAS`;
        const response = await pool.query(str_query);
        res.setHeader('Content-Type', 'application/json');
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }      
} 

const newRoutes = async(req, res)=>{
    try {
        console.log(req.body);
        let json = req.body;
        let id = new Date();
        id = id.getTime();
        let str_query = `INSERT INTO RUTAS(ID_RUTA, ID_MUNICIPIO, NOM_RUTA, DESC_RUTA, IMG_RUTA, TIEMPO_RUTA) VALUES
        ('${id}', '${json.id_municipio}', '${json.nom_ruta}', '${json.desc_ruta}', '${req.file.originalname}', '${json.tiempo_ruta} ${json.tipo_tiempo}')`;
        const response = await pool.query(str_query);
        
        let coord = eval(json.txt_coordenadas);
        coord.forEach(elem => {
            str_query = `select nuevaCoordenada('${id}', '${elem.lat}', '${elem.lon}');`;
            pool.query(str_query);
            console.log(str_query);
        });
        
        res.render('regresar.ejs', {title:'Rutas', message:"Ruta creada exitosamente", str_url:'/rutas'});
    } catch (e) {
        console.log(e);
    }    
} 

const editRoutes = async(req, res)=>{
    try {
        console.log("PUT: " + JSON.stringify(req.body));
        //console.log(res);
        /*
        let json = req.body;
        let id = new Date();
        id = id.getTime();
        let str_query = `INSERT INTO RUTAS(ID_RUTA, ID_MUNICIPIO, NOM_RUTA, DESC_RUTA, IMG_RUTA, TIEMPO_RUTA) VALUES
        ('${id}', '${json.id_municipio}', '${json.nom_ruta}', '${json.desc_ruta}', '${req.file.originalname}', '${json.tiempo_ruta}')`;
        const response = await pool.query(str_query);
        */
        //console.log(response);
        //res.render('regresar.ejs', {title:'Rutas', message:"Ruta creada exitosamente", str_url:'/rutas'});
    } catch (e) {
        console.log(e);
    }    
} 

const deleteRoutes = async(req, res)=>{
    try {
        let str_query = `DELETE FROM RUTAS WHERE id_ruta like '${req.body.id_ruta}'`;
        const response = await pool.query(str_query);
        res.send("DELETED");
    } catch (e) {
        console.log(e);
    }    
} 



module.exports = {
    validateUser,
    getUsers,

    newUser, 
    editUser,
    deleteUser,

    getMuni,
    newMuni, 
    editMuni,
    deleteMuni,

    getEvents,
    newEvents,
    deleteEvents,

    getRoutes,
    newRoutes,
    editRoutes,
    deleteRoutes
}

