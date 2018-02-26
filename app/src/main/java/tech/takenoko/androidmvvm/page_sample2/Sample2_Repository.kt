package tech.takenoko.androidmvvm.page_sample2

import rx.Single
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.Const.CACHE_TIMEOUT
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.database.Sample_Dao
import tech.takenoko.androidmvvm.database.Sample_Table
import tech.takenoko.androidmvvm.utility.Util
import java.io.Serializable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, Date>() {

    override val log: String = "Sample2_Repository"

    /** DI DB and Api. */
    @Inject lateinit var sampleDao: Sample_Dao
    @Inject lateinit var sampleApi: Sample_Api

    /** cache key */
    val GET_LATEST__DATE = "GET_LATEST__DATE"
    val GET_PAST__DATE = "GET_PAST__DATE"

    /** cache property */
    data class Entity (var base: String?, var target: String?, var date: String?, var rate: String?): Serializable
    var cacheGetLatest: MutableList<Entity> = mutableListOf(); private set
    var cacheGetPast: MutableList<Entity> = mutableListOf(); private set

    /**
     * load property.
     * @param readType
     * @param subscriber
     */
    private fun loadPropertyGetLatest(readType: Const.ReadType): List<Entity>? {
        // check read type.
        return if(!readType.contain(Const.ReadType.PROPERTY) || !checkTimeout(getCache()[GET_LATEST__DATE], CACHE_TIMEOUT)) null else cacheGetLatest
    }

    /**
     * load property.
     * @param readType
     * @param subscriber
     */
    private fun loadPropertyGetPast(readType: Const.ReadType): List<Entity>? {
        // check read type.
        return if(!readType.contain(Const.ReadType.PROPERTY) || !checkTimeout(getCache()[GET_PAST__DATE], CACHE_TIMEOUT)) null else cacheGetPast
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
        getCache()[GET_LATEST__DATE] = Date()
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
        getCache()[GET_PAST__DATE] = Date()
    }

    /**
     * load database.
     * @param readType
     * @param subscriber
     * @param base
     */
    private fun loadDatabase(readType: Const.ReadType, base: String): List<Entity>? {
        // check read type.
        if (!readType.contain(Const.ReadType.LOCAL_DB)) return null
        // db
        var list = mutableListOf<Entity>()
        sampleDao.findAll().forEach { t ->
            if(base == t.base) list.add(Entity(t.base, t.target, t.date, t.rate))
        }
        return if(list.size > 0) list else null
    }

    /**
     * save database.
     * @param readType
     * @param base
     */
    private fun saveDatabase(readType: Const.ReadType, base: String?, target: String?, date: String?, rate: String?) {
        // check read type.
        if(!readType.contain(Const.ReadType.LOCAL_DB)) return
        // save.
        sampleDao.insert(Sample_Table().creat(date, base, target, rate))
    }

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getLatest(base: String, symbols: String, readType: Const.ReadType): Single<List<Entity>> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (true == loadPropertyGetLatest(readType)?.isNotEmpty()) {
                subscriber.onSuccess(loadPropertyGetLatest(readType))
                return@rxSingle
            }
            // get DB cache.
            if (true == loadDatabase(readType, base)?.isNotEmpty()) {
                loadDatabase(readType, base)?.forEach { t -> savePropertyGetLatest(readType, t.base, t.target, t.date, t.rate) }
                subscriber.onSuccess(loadDatabase(readType, base))
                return@rxSingle
            }
            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getLatest")
            apiSubscriber.setSuccessBlock{ t ->
                // caching DB.
                var entityList = mutableListOf<Entity>()
                t.rates?.forEach { rate ->
                    entityList.add(Entity(t.base, rate.key, t.date, rate.value))
                    saveDatabase(readType, t.base, rate.key, t.date, rate.value)
                    savePropertyGetLatest(readType, t.base, rate.key, t.date, rate.value)
                }
                // return value
                subscriber.onSuccess(entityList)
            }
            apiSubscriber.setErrorBlock { e ->
                // return value
                subscriber.onError(e)
            }

            // get an entity from api.
            if(readType.contain(Const.ReadType.API)) sampleApi.getLatest(base/*, symbols*/).subscribe(apiSubscriber)
        }}
    }

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getPast(date: String, base: String, symbols: String, readType: Const.ReadType): Single<List<Entity>> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (true == loadPropertyGetPast(readType)?.isNotEmpty()) {
                subscriber.onSuccess(loadPropertyGetPast(readType))
                return@rxSingle
            }
            // get DB cache.
            if (true == loadDatabase(readType, base)?.isNotEmpty()) {
                loadDatabase(readType, base)?.forEach { t -> savePropertyGetPast(readType, t.base, t.target, t.date, t.rate) }
                subscriber.onSuccess(loadDatabase(readType, base))
                return@rxSingle
            }
            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getPast")
            apiSubscriber.setSuccessBlock{ t ->
                // caching DB.
                var entityList = mutableListOf<Entity>()
                t.rates?.forEach { rate ->
                    entityList.add(Entity(t.base, rate.key, t.date, rate.value))
                    saveDatabase(readType, t.base, rate.key, t.date, rate.value)
                    savePropertyGetPast(readType, t.base, rate.key, t.date, rate.value)
                }
                // return value
                subscriber.onSuccess(entityList)
            }
            apiSubscriber.setErrorBlock { e ->
                // return value
                subscriber.onError(e)
            }

            // get an entity from api.
            if(readType.contain(Const.ReadType.API)) {
                sampleApi.getPast(date, base/*, symbols*/).subscribe(apiSubscriber)
            }
        }}
    }

}