import NeDB from 'nedb';
import _ from 'lodash';
import CONST from '../const';
import {log, getIP} from '../utils/index'

class DBManager {

    constructor() {
        this.db = {};
        _.each(CONST.TABLES, table => {
            this.db[table] = new NeDB({filename: `./db/${table}.db`});
            this.db[table].loadDatabase();
        });
        this.find = this.find.bind(this)
        this.insert = this.insert.bind(this)
        this.remove = this.remove.bind(this)
    }

    check() {
        // check table.
    }

    find(table, param) {
        log.info("DBManager.find");
        return new Promise((resolve, reject) => {
            this.db[table].find(param, (err, docs) => err ? reject(err) : resolve(docs));
        });
    }

    insert(table, param) {
        log.info("DBManager.insert");
        return new Promise((resolve, reject) => {
            this.db[table].insert(param, (err, docs) => err ? reject(err) : resolve(docs));
        });
    }
    
    remove(table, param) {
        log.info("DBManager.remove");
        return new Promise((resolve, reject) => {
            this.db[table].remove(param, (err, numRemoved) => err ? reject(err) : resolve(numRemoved));
        });
    }
}

export default new DBManager()