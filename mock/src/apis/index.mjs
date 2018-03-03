import IndexApi from './IndexApi'
import FixerApi from './FixerApi'

export default {
    IndexApi: new IndexApi().call,
    latestApi: new FixerApi("latest").call,
    pastApi: new FixerApi("past").call,
}