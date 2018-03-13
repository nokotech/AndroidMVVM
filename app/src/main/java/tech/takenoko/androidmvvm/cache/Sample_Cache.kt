package tech.takenoko.androidmvvm.cache

import tech.takenoko.androidmvvm.constant.Const
import java.io.Serializable
import java.util.*
import javax.inject.Inject

/**
 * Created by takenoko on 2018/03/01.
 */
class Sample_Cache @Inject constructor(): BaseCache() {

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
    fun loadPropertyGetLatest(): List<Entity>? {
        // check read type.
        return if(!checkTimeout(cacheGetLatestDate, Const.CACHE_TIMEOUT)) null else cacheGetLatest
    }

    /**
     * save property.
     * @param readType
     * @param list
     */
    fun savePropertyGetLatest(base: String?, target: String?, date: String?, rate: String?) {
        // save.
        cacheGetLatest.add(Entity(base, target, date, rate))
        cacheGetLatestDate = Date()
    }

    /**
     * clear property.
     */
    fun clearPropertyGetLatest() {
        cacheGetLatestDate = null
        cacheGetLatest = mutableListOf()
    }

    /**
     * load property.
     * @param readType
     * @param subscriber
     */
    fun loadPropertyGetPast(): List<Entity>? {
        // check read type.
        return if(!checkTimeout(cacheGetPastDate,  Const.CACHE_TIMEOUT)) null else cacheGetPast
    }

    /**
     * save property.
     * @param readType
     * @param list
     */
    fun savePropertyGetPast(base: String?, target: String?, date: String?, rate: String?) {
        // save.
        cacheGetPast.add(Entity(base, target, date, rate))
        cacheGetPastDate = Date()
    }

    /**
     * clear property.
     */
    fun clearPropertyGetPast() {
        cacheGetLatestDate = null
        cacheGetLatest = mutableListOf()
    }

}