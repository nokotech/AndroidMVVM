import IndexApi from './IndexApi'
import FixerApi from './FixerApi'
import UserApi from './UserApi'

export default {
    IndexApi: new IndexApi().call,
    latestApi: new FixerApi("latest").call,
    pastApi: new FixerApi("past").call,
    userApi: new UserApi().call,
}