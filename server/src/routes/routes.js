const { Router } = require('express');
const router = Router();
const { validateUser } = require('../database/manager');
const { getUsers } = require('../database/manager');
const { getEvents } = require('../database/manager');
const { getRoutes } = require('../database/manager');
const { newUser } = require('../database/manager');
const { editUser } = require('../database/manager');
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
router.delete('/api/users', newUser);
//----------------------------------------------- Eventos

//----------------------------------------------- Rutas

//----------------------------------------------- Municipios
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