import BaseApi from './BaseApi';
import {log, getIP} from '../utils/index'

export default class IndexApi extends BaseApi {
    
    constructor () {
        super()
    }

    call(req, res) {
        log.info(getIP(req));
        const response = {
        }
        log.info( `[RESPONSE] -- ${log.json(response)}`);
        super.crateResponse(res, response)
    }
}