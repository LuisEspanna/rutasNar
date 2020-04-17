const { Router } = require('express');
const router = Router();
const { validateUser } = require('../database/manager');
const { getUsers } = require('../database/manager');
const { getEvents } = require('../database/manager');
const { getRoutes } = require('../database/manager');
const { newData } = require('../database/manager');
const { editData } = require('../database/manager');

router.get("/", async (req, res)=>{
    try {
        res.send("Servidor Node funcionando :D");
    } catch (e) {
        console.log("Error");
    }
});

//--Verificar si el usuario está en la base
router.post('/loggin', validateUser);

// Registrar nuevo (depende del header:) 
router.post('/register', newData);

// Editar nuevo (depende del header:) 
router.post('/edit', editData);

// Retorna un JsonArray de los usuarios en base
router.get('/users', getUsers);

// Retorna un JsonArray de los eventos en base
router.get('/events', getEvents);

// Retorna un JsonArray de las rutas en base
router.get('/routes', getRoutes);

module.exports = router;