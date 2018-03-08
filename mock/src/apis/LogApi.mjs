import BaseApi from './BaseApi';
import {log, getIP} from '../utils/index'
import DBManager from '../database/DBManager';

export default class LogApi extends BaseApi {
    
    constructor () {
        super();
    }

    call(req, res) {
        log.info(getIP(req));
        const userId = req.query['user_id'];
        const eventId = req.query['event_id'];
        log.info( `[REQUEST] -- ${userId} ${eventId}`);
        const logData = {
            userId: userId,
            eventId: eventId,
            insertDate: new Date().getTime()
        }
        const promise = Promise.resolve();
        promise.then(() => DBManager.insert("log", logData))
        promise.then(r => {
            log.info( `[RESPONSE] -- ${logData}`);
            super.crateResponse(res, {})
        }).catch(e => {
            log.error(e)
        });
    }
}