const { Router } = require('express');
const router = Router();
const { validateUser } = require('../database/manager');
const { getUsers } = require('../database/manager');

const { getRoutes } = require('../database/manager');
//----------------------------------------------------------Users
const { newUser } = require('../database/manager');
const { editUser } = require('../database/manager');


//----------------------------------------------------------Muni
const { getMuni } = require('../database/manager');
const { newMuni } = require('../database/manager');
const { editMuni } = require('../database/manager');
const { deleteMuni } = require('../database/manager');

//---------------------------------------------------------- Eventos
const { getEvents } = require('../database/manager');
const { newEvents } = require('../database/manager');
const { deleteEvents } = require('../database/manager');


const fs=require('fs');


router.get("/", async (req, res)=>{
    try {
        fs.readFile('./src/html/index.html', (err, html)=>{
            if(err)throw err;
            res.write(html);
            res.end();
        });
    } catch (e) {
        console.log("Error");
    }
});

//--Verificar si el usuario está en la base
router.post('/loggin', validateUser);


/*
    post: Crear
    put: Editar
    delete: Eliminar
*/
//----------------------------------------------- Usuarios
router.post('/api/users', newUser);
router.put('/api/users', editUser);
//router.delete('/api/users', newUser);

//----------------------------------------------- Rutas

//----------------------------------------------- Eventos
router.get('/eventos', async (req, res)=>{
    try {
        fs.readFile('./src/html/eventos.html', (err, html)=>{
            if(err)throw err;
            res.write(html);
            res.end();
        });
    } catch (e) {
        console.log("Error");
    }
});

router.get('/api/events', getEvents);
router.post('/api/events', newEvents);
router.delete('/api/events', deleteEvents);

//----------------------------------------------- Municipios
router.get('/api/municipality', getMuni);
router.post('/api/municipality', newMuni);
router.put('/api/municipality', editMuni);
router.delete('/api/municipality', deleteMuni);


router.get('/municipios', async (req, res)=>{
    try {
        fs.readFile('./src/html/municipios.html', (err, html)=>{
            if(err)throw err;
            res.write(html);
            res.end();
        });
    } catch (e) {
        console.log("Error");
    }
});

module.exports = router;