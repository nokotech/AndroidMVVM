import os from 'os'
import log from './log'
export {
    log
}

export const getIP = () => {
    let addressArr = [];
    let interfaces = os.networkInterfaces();

    for (let dev in interfaces) {
        interfaces[dev].forEach((details) => {
            if (details.internal || details.family !== 'IPv4') return
            // let mes = `${os.hostname()}: ${details.address} (standup)`
            addressArr.push(details.address)
        });
    }
    return addressArr
}