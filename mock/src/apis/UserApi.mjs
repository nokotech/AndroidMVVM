import BaseApi from './BaseApi';
import {log, getIP} from '../utils/index'
import DBManager from '../database/DBManager';

export default class UserApi extends BaseApi {
    
    constructor () {
        super();
        this.random = this.random.bind(this);
        this.call = this.call.bind(this);
    }

    random() {
        var c = "abcdefghijklmnopqrstuvwxyz0123456789";
        var r = "";
        for(var i = 0; i < 16; i++) {
            r += c[ Math.floor( Math.random() * c.length) ];
        }
        return r;
    }

    call(req, res) {
        log.info(getIP(req));
        const method = req.params.method;
        const name = req.query.name;
        log.info( `[REQUEST] -- ${method} ${name}`);
        const users = {
            "name": name,
        }
        const token = {
            "name": name,
            "token": this.random()
        }
        const promise = Promise.resolve();
        promise.then(() => DBManager.insert("users", users))
        promise.then(() => DBManager.insert("tokens", token))
        promise.then(r => {
            log.info( `[RESPONSE] -- ${r}`);
            super.crateResponse(res, {})
        }).catch(e => {
            log.error(e)
        });
    }

    verify() {
        return new Promise(() => {

        });
    }

    token() {
        return new Promise(() => {
            
        });
    }
}