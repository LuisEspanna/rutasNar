const { Router } = require('express');

const router = Router();

router.get("/", async (req, res)=>{
    try {
        res.send("Node API REST GET");
    } catch (e) {
        console.log("Error");
    }
});

router.post('/', (req, res)=>{
    try {
        res.send("Node API REST POST");
    } catch (e) {
        console.log("Error");
    }
});

module.exports = router;