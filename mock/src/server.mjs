import cluster from 'cluster';  
import express from 'express';
import os from 'os';

import CONST from './const'
import {log} from './utils/index'
import API from './apis/index'

 // Create a worker.
if (cluster.isMaster) for (let i = 0; i < os.cpus().length; i++) cluster.fork(); else main();

function main() {
    // EXPRESS SETUP
    const app = express();
    app.use(log.express)
    // app.use(express.static(__dirname + '/public'))
    const server = app.listen(CONST.SERVER_PORT)

    // EXPRESS ROUTING
    app.get('/', API.IndexApi)
    app.get('/latest', API.latestApi)
    app.get('/:date?', API.pastApi)
}
