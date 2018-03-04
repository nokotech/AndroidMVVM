import cluster from 'cluster';  
import express from 'express';
import os from 'os';

import CONST from './const';
import {log} from './utils/index';
import API from './apis/index';
import DBManager from './database/DBManager';

 // Create a worker.
if (cluster.isMaster) {
    setup();
    for (let i = 0; i < os.cpus().length; i++) cluster.fork();
} else main();

function main() {
    // EXPRESS SETUP
    const app = express();
    app.use(log.express)
    // app.use(express.static(__dirname + '/public'))
    const server = app.listen(CONST.SERVER_PORT)

    // EXPRESS ROUTING
    app.get('/', API.IndexApi)
    app.get('/latest', API.latestApi)
    app.get('/user/:method?', API.userApi)
    app.get('/:date?', API.pastApi)
}

function setup() {
    DBManager.find("users", {}).then(r => {
        log.info(r)
    }).catch(e => {
        log.error(e)
    });
}
