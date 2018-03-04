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
        const random = () => (!date || date=="letest") ? 0 : Math.floor( Math.random () * 100000 )
        const response = {
            "base": base || "USD",
            "date": date || "2099-12-31",
            "rates": {
                "AAA": 111111 + random() ,
                "BBB": 222222 + random() ,
                "CCC": 333333 + random() ,
                "DDD": 444444 + random() ,
                "EEE": 555555 + random() ,
                "FFF": 666666 + random() ,
                "GGG": 777777 + random() ,
                "HHH": 888888 + random() ,
                "III": 111111 - random() ,
                "JJJ": 222222 - random() ,
                "KKK": 333333 - random() ,
                "LLL": 444444 - random() ,
                "MMM": 555555 - random() ,
                "NNN": 666666 - random() ,
                "OOO": 777777 - random() ,
                "PPP": 888888 - random() ,
            }
        }
        log.info( `[RESPONSE] -- ${response.date}`);
        super.crateResponse(res, response)
    }
}