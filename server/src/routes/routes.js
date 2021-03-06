const { Router } = require('express');
const router = Router();
const { validateUser } = require('../database/manager');
const { getUsers } = require('../database/manager');
//----------------------------------------------------------Users
const { newUser } = require('../database/manager');
const { editUser } = require('../database/manager');
const { deleteUser } = require('../database/manager');

//----------------------------------------------------------Muni
const { getMuni } = require('../database/manager');
const { newMuni } = require('../database/manager');
const { editMuni } = require('../database/manager');
const { deleteMuni } = require('../database/manager');

//---------------------------------------------------------- Eventos
const { getEvents } = require('../database/manager');
const { newEvents } = require('../database/manager');
const { deleteEvents } = require('../database/manager');
const { showEvent } = require('../database/manager');
//---------------------------------------------------------- Rutas
const { getRoutes } = require('../database/manager');
const { newRoutes } = require('../database/manager');
const { editRoutes } = require('../database/manager');
const { deleteRoutes } = require('../database/manager');
//---------------------------------------------------------- Rutas
const { getActivity } = require('../database/manager');
const { newActivity } = require('../database/manager');
const { deleteActivity } = require('../database/manager');
const { showRoute } = require('../database/manager');


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

/*
    post: Crear
    put: Editar
    delete: Eliminar
*/
//----------------------------------------------- Usuarios
//router.get('/api/users', getUsers); //No es seguro enviar esta informacion :v
//--Verificar si el usuario está en la base
router.post('/api/loggin', validateUser);

router.post('/api/users', newUser);
router.put('/api/users', editUser);
router.delete('/api/users', deleteUser);

//----------------------------------------------- Rutas
router.get('/rutas', async (req, res)=>{
    try {
        fs.readFile('./src/html/rutas.html', (err, html)=>{
            if(err)throw err;
            res.write(html);
            res.end();
        });
    } catch (e) {
        console.log("Error");
    }
});

router.get('/rutas/:valor', showRoute);

router.get('/api/routes', getRoutes);
router.post('/api/routes', newRoutes);
router.put('/api/routes', editRoutes);
router.delete('/api/routes', deleteRoutes);

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
router.get('/eventos/:valor', showEvent);

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

//----------------------------------------------- Actividades
router.get('/api/activity', getActivity);
router.post('/api/activity', newActivity);
router.delete('/api/activity', deleteActivity);

module.exports = router;