const { Pool } = require("pg");

const config = {
    user : 'postgres',
    host : 'localhost',
    password : '1234',
    database : 'RutasNar'
};

const pool = new Pool(config);

//--------------------------------------------------- USERS
const validateUser = async(req, res)=>{
    try {
        console.log("Validate user");
        let jsonInfo = req.body;
        let str_query = `SELECT * FROM USUARIOS WHERE nom_usuario like '${jsonInfo.nom_usuario}' and clave_usuario like '${jsonInfo.clave_usuario}'`;

        const response = await pool.query(str_query);

        if (response.rows[0] === undefined) {
            res.status(404).send(jsonInfo);
        } else {
            res.status(200).json(response.rows[0]);
            console.log("Inicio de sesión correcto para " + response.rows[0].nom_usuario);
        }
       
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

const newUser = async(req, res)=>{
    console.log("new user");
    try {
        console.log("new user");
        let infoJson = req.body;
        infoJson.id_usuario = new Date().getTime();
        console.log(req.body);

        let str_query = `INSERT INTO USUARIOS(ID_USUARIO, NOM_USUARIO, CLAVE_USUARIO) VALUES ('${infoJson.id_usuario}','${infoJson.nom_usuario}','${infoJson.clave_usuario}')`;
        console.log(str_query);
        const response = await pool.query(str_query);
      
        res.json(req.body);
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
        //let str_query = `SELECT * FROM EVENTOS`;
        let str_query = `select id_evento, nom_municipio as id_municipio, nom_evento, desc_evento, img_evento, fecha_evento, disponible, eventos.latitud, eventos.longitud from
        eventos join municipios on eventos.id_municipio = municipios.id_municipio`;
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
        let str_query = "";
        let json = req.body;
        
        if (json.id_evento != undefined){
            console.log("Evento consultado");
            //str_query = `SELECT * FROM EVENTOS WHERE id_evento like '${json.id_evento}'`;
            str_query = `select id_evento, nom_municipio as id_municipio, nom_evento, desc_evento, img_evento, fecha_evento, disponible, eventos.latitud, eventos.longitud from
            eventos join municipios on eventos.id_municipio = municipios.id_municipio WHERE id_evento like '${json.id_evento}'`;
            const response = await pool.query(str_query);
            res.status(200);
            res.json(response.rows);
        }
        else{
            let id = new Date();
            id = id.getTime();
            str_query = `INSERT INTO 
            EVENTOS(id_evento, id_municipio, nom_evento, desc_evento, img_evento, fecha_evento, disponible, latitud, longitud) VALUES 
            ('${id}','${json.id_municipio}','${json.nom_evento}','${json.desc_evento}','${req.file.originalname}','${json.fecha_evento}','${json.disponible}','${json.latitud}', '${json.longitud}');`;
            //console.log(req.file);
            const response = await pool.query(str_query);
            console.log(response);
            res.render('regresar.ejs', { title: 'Evento', message: "Evento creado exitosamente", str_url: '/eventos' });
        }
        //console.log("Evento:\n" + JSON.stringify(req.body));
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

const showEvent = async(req, res)=>{
    try {
        console.log("Mostrar ruta");
        
        let str_query = `select latitud, longitud from eventos where id_evento like '${req.params.valor}'`;
        //console.log(str_query);

        const response = await pool.query(str_query);

        //res.status(200).json(response.rows);
        res.render('mapa.ejs', {coordenadas: JSON.stringify(response.rows)});

    } catch (e) {
        console.log(e);
    }    
} 

//------------------------------------------------------- Municipios
const getMuni = async(req, res)=>{
    try {
        console.log("Municipio:\n" + req.body);
        let str_query = `SELECT * FROM MUNICIPIOS order by nom_municipio`;
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
        //let str_query = `SELECT * FROM RUTAS`;
        let str_query = `select id_ruta, nom_municipio as id_municipio, nom_ruta, desc_ruta, img_ruta, tiempo_ruta from
        rutas join municipios on rutas.id_municipio = municipios.id_municipio`;
        const response = await pool.query(str_query);
        res.setHeader('Content-Type', 'application/json');
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }      
} 

const newRoutes = async(req, res)=>{
    try {
        let str_query = "";
        let json = req.body;

        if (json.id_ruta != undefined){
            console.log("Ruta consultada");
            //str_query = `SELECT * FROM RUTAS WHERE id_ruta like '${json.id_ruta}'`;
            str_query = `select id_ruta, nom_municipio as id_municipio, nom_ruta, desc_ruta, img_ruta, tiempo_ruta from
            rutas join municipios on rutas.id_municipio = municipios.id_municipio where id_ruta like '${json.id_ruta}'`;
            const response = await pool.query(str_query);
            res.status(200);
            res.json(response.rows);
        }
        else{
            console.log(req.body);
            let id = new Date();
            id = id.getTime();
            str_query = `INSERT INTO RUTAS(ID_RUTA, ID_MUNICIPIO, NOM_RUTA, DESC_RUTA, IMG_RUTA, TIEMPO_RUTA) VALUES
            ('${id}', '${json.id_municipio}', '${json.nom_ruta}', '${json.desc_ruta}', '${req.file.originalname}', '${json.tiempo_ruta} ${json.tipo_tiempo}')`;
            const response = await pool.query(str_query);

            let coord = eval(json.txt_coordenadas);
            coord.forEach(elem => {
                str_query = `select nuevaCoordenada('${id}', '${elem.lat}', '${elem.lon}');`;
                pool.query(str_query);
                console.log(str_query);
            });

            res.render('regresar.ejs', { title: 'Rutas', message: "Ruta creada exitosamente", str_url: '/rutas' });
        }
    } catch (e) {
        console.log(e);
    }    
} 

const editRoutes = async(req, res)=>{
    try {
        console.log("PUT: " + JSON.stringify(req.body));
    } catch (e) {
        console.log(e);
    }    
} 

const deleteRoutes = async(req, res)=>{
    try {
        let str_query = `DELETE FROM RUTAS WHERE id_ruta like '${req.body.id_ruta}'`;
        const response = await pool.query(`DELETE FROM COORDENADAS WHERE id_ruta like '${req.body.id_ruta}'`);
        pool.query(str_query);
        res.send("DELETED");
    } catch (e) {
        console.log(e);
    }    
} 


//------------------------------------------------ Actividades
const getActivity = async(req, res)=>{
    try {
        console.log("listado de actividades");
        console.log(req.body);

        const response = await pool.query(`SELECT * FROM ACTIVIDADES`);
        res.setHeader('Content-Type', 'application/json');
        res.status(200).json(response.rows);
    } catch (e) {
        console.log(e);
    }    
} 


const newActivity = async(req, res)=>{
    try {
        console.log("Nueva actividad");
        console.log("listado de actividades");
        console.log(req.body);

        if(req.body.id_usuario != undefined){
            let json = req.body;
            let id = new Date().getTime();

            if(req.body.nom_actividad != ""){
                const response = await pool.query(`INSERT INTO 
                ACTIVIDADES(id_actividad, id_usuario, nom_actividad, id_ruta, id_evento) 
                VALUES ('${id}' , '${json.id_usuario}', '${json.nom_actividad}', '${json.id_ruta}', '${json.id_evento}')`);
                res.setHeader('Content-Type', 'application/json');
                res.status(200).json(json);
            }
            else{
                const response = await pool.query(`SELECT * FROM ACTIVIDADES WHERE ID_USUARIO LIKE '${json.id_usuario}'`);
                res.setHeader('Content-Type', 'application/json');
                res.status(200).json(response.rows);
            }
        }
        else if(req.body.id_usuario === undefined){
            res.status(404).send("404");
        }
    } catch (e) {
        res.setHeader('Content-Type', 'application/json');
        res.status(500).json({message:e});
        console.log(e);
    }    
} 

const deleteActivity = async(req, res)=>{
    try {
        console.log("Actividad elimiada");
        console.log(req.body);

        let str_query = `delete from actividades where id_actividad like '${req.body.id_actividad}'`;
        //console.log(str_query);

        const response = await pool.query(str_query);

        res.status(200).json({id_actividad:"", id_usuario:"", nom_actividad:"", id_ruta:"", id_evento:""});

    } catch (e) {
        console.log(e);
    }    
} 


const showRoute = async(req, res)=>{
    try {
        console.log("Mostrar ruta");
        
        let str_query = `select latitud, longitud from coordenadas where id_ruta like '${req.params.valor}'`;
        //console.log(str_query);

        const response = await pool.query(str_query);

        //res.status(200).json(response.rows);
        res.render('mapa.ejs', { coordenadas: JSON.stringify(response.rows)});

    } catch (e) {
        console.log(e);
    }    
} 


module.exports = {
    validateUser,
    getUsers,
    deleteUser,

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
    deleteRoutes,

    getActivity,
    newActivity,
    deleteActivity,

    showRoute, 
    showEvent
}

