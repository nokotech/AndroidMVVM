import BaseApi from './BaseApi';
import {log, getIP} from '../utils/index'

export default class IndexApi extends BaseApi {
    
    constructor () {
        super()
    }

    call(req, res) {
        log.info(getIP(req));
        const base = req.query.base;
        const date = req.params.date;
        log.info( `[REQUEST] -- ${base} ${date}`);
        const random = (len) => (!date || date=="letest") ? 0 : (Math.floor( Math.random () * len *2 ) - len) 
        const response = {
            "base": base || "USD",
            "date": date || "2099-12-31",
            "rates": {
                "AAA": 111111 + random(100000) ,
                "BBB": 222222 + random(200000) ,
                "CCC": 333333 + random(300000) ,
                "DDD": 444444 + random(400000) ,
                "EEE": 555555 + random(400000) ,
                "FFF": 666666 + random(300000) ,
                "GGG": 777777 + random(200000) ,
                "HHH": 888888 + random(100000) ,
                "III": 111111 + random(100000) ,
                "JJJ": 222222 + random(200000) ,
                "KKK": 333333 + random(300000) ,
                "LLL": 444444 + random(400000) ,
                "MMM": 555555 + random(400000) ,
                "NNN": 666666 + random(300000) ,
                "OOO": 777777 + random(200000) ,
                "PPP": 888888 + random(100000) ,
            }
        }
        log.info( `[RESPONSE] -- ${response.date}`);
        super.crateResponse(res, response)
    }
}