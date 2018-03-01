package tech.takenoko.androidmvvm.cache

import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.utility.ULog
import java.io.Serializable
import java.util.*

/**
 * Created by takenaka on 2018/03/01.
 */
class Sample_Cache: BaseCache() {

    /** cache property */
    data class Entity (
            var base: String?,
            var target: String?,
            var date: String?,
            var rate: String?
    ): Serializable

    var cacheGetLatestDate: Date? = null; private set
    var cacheGetLatest: MutableList<Entity> = mutableListOf(); private set

    var cacheGetPastDate: Date? = null; private set
    var cacheGetPast: MutableList<Entity> = mutableListOf(); private set

    /**
     * load property.
     * @param readType
     * @param subscriber
     */
    private fun loadPropertyGetLatest(readType: Const.ReadType): List<Entity>? {
        // check read type.
        return if(!readType.contain(Const.ReadType.PROPERTY) || !checkTimeout(cacheGetLatestDate, Const.CACHE_TIMEOUT)) null else cacheGetLatest
    }

    /**
     * load property.
     * @param readType
     * @param subscriber
     */
    private fun loadPropertyGetPast(readType: Const.ReadType): List<Entity>? {
        // check read type.
        return if(!readType.contain(Const.ReadType.PROPERTY) || !checkTimeout(cacheGetPastDate,  Const.CACHE_TIMEOUT)) null else cacheGetPast
    }

    /**
     * save property.
     * @param readType
     * @param list
     */
    private fun savePropertyGetLatest(readType: Const.ReadType, base: String?, target: String?, date: String?, rate: String?) {
        // check read type.
        if(!readType.contain(Const.ReadType.PROPERTY)) return
        // save.
        cacheGetLatest.add(Entity(base, target, date, rate))
        cacheGetLatestDate = Date()
    }

    /**
     * save property.
     * @param readType
     * @param list
     */
    private fun savePropertyGetPast(readType: Const.ReadType, base: String?, target: String?, date: String?, rate: String?) {
        // check read type.
        if(!readType.contain(Const.ReadType.PROPERTY)) return
        // save.
        cacheGetPast.add(Entity(base, target, date, rate))
        cacheGetPastDate = Date()
    }

}