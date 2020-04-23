import express from "express"
import bodyParser from 'body-parser'

const app = express();
const PORT = 3000;

var mysql = require('mysql');

app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json());


var con = mysql.createConnection({
    host: "localhost",
    user: "apiUser",
    password: "temp",
    database: "androidproject"
});

con.connect();


/*con.connect(function (err) {
    if (err) throw err;
    console.log("Connected to DB!");
});*/

app.get('/recettes/', (req, res) => {


    con.query("SELECT * FROM androidproject.tblrecette;", function (err, result, fields) {
        if (err) throw err;
        res.send(result)

    });
})


app.get('/recettes/:userId', (req, res) => {

    con.query(`select TR.nomRecette, TR.txtRecette from tbluser 
	join tbluserpreference as UP
    on UP.idUser = tbluser.idUser
    join tblrecettecategorie as RC
    on RC.idCategorie = UP.idPreferenceCategorie
    join tblRecette as TR 
    on TR.idRecette = RC.idRecette
    where tbluser.idUser like ` + req.params.userId + `;`, function (err, result, fields) {
        if (err) throw err;
        res.send(result)

    });
})

app.get('/user/:username/:userPassword', (req, res) => {

    let dbUserId;
    let loginValid;


    con.query(`select count(*) as 'nbr' from tbluser where username like '` + req.params.username + `';`, function (err, result, fields) {
        if (err) throw err;
        if (result[0].nbr == 0)
            res.send({
                "loggedIn": false
            });

    });
    con.query(`select * from tbluser where username like '` + req.params.username + `';`, function (err, result, fields) {
        if (err) throw err;
        console.log(result[0].userPassword);

        if (result[0].userPassword == req.params.userPassword) {
            res.send({
                "loggedIn": true,
                "userId": result[0].idUser
            })
        }
        else{
            res.send({
                "loggedIn": false
            });
        }
    });




    /* var responseDict = {
         "average_weare": average_weare,
         "average_lost_entries": average_lost_entries,
         "average_active_percent": average_active_percent,
         "average_offline_activity_percent": average_offline_activity_percent,
         "average_no_acti_pct" : average_no_acti_pct,
         "average_small_acti_pct" : average_small_acti_pct,
         "average_moderate_acti_pct" : average_moderate_acti_pct,
         "average_no_offl_acti_pct" : average_no_offl_acti_pct,
         "average_small_offl_acti_pct" : average_small_offl_acti_pct,
         "average_moderate_offle_acti_pct" : average_moderate_offle_acti_pct
     };*/
})


app.get('/', (req, res) => {
    res.send("hello world");
});

app.listen(PORT, () =>
    console.log(`Node server running on ${PORT}!`),
);



