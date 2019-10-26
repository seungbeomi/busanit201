var express = require('express');
var router = express.Router();

/* GET users listing. */
router.post('/auth', function(req, res, next) {
  
  const email = req.body.email;
  const password = req.body.password;

  res.json({ 
    //result : (email == "test@bnksys.co.kr" && password == "1234")
    email: email,
    name: 'seungbeomi'
  });
});

module.exports = router;
