package tech.takenoko.androidmvvm.page_sample2

import rx.Single
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.database.Sample_Dao
import tech.takenoko.androidmvvm.database.Sample_Table
import tech.takenoko.androidmvvm.utility.ULog
import tech.takenoko.androidmvvm.utility.Util
import java.io.Serializable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, String>() {

    override val log: String = "Sample2_Repository"

    /** DI DB. */
    @Inject lateinit var sampleDao: Sample_Dao

    /** DI Api. */
    @Inject lateinit var sampleApi: Sample_Api

    /** cache key */
    val GET_LATEST__DATE = "GET_LATEST__DATE"
    val GET_PAST__DATE = "GET_PAST__DATE"

    /** cache property */
    data class Entity (var base: String?, var target: String?, var date: String?, var rate: String?): Serializable
    var cacheGetLatest: List<Entity>? = null; private set
    var cacheGetPast: List<Entity>? = null; private set

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getLatest(base: String, symbols: String, readType: Const.ReadType): Single<List<Entity>> {
        return rxSingle { subscriber -> run {

            fun propaty(): Boolean {
                subscriber.onSuccess(cacheGetLatest)
                return true
            }

            fun db(): Boolean {
                var c = mutableListOf<Entity>()
                val list: List<Sample_Table> = sampleDao.findAll()
                ULog.debug("db", "===================================")
                ULog.debug("db", "count = ${list.size}")
                list.forEach { t ->
                    ULog.debug("db", "${t.date}, ${t.base}, ${t.target}, ${t.rate}")
                    if(base == t.base) c.add(Entity(t.base, t.target, t.date, t.rate))
                }
                ULog.debug("db", "===================================")
                if(c.size > 0) {
                    cacheGetLatest = c
                    getCache().put(GET_LATEST__DATE, Util.dateToString(Date()))
                    return propaty()
                }
                return false
            }

            // get property cache.
            if (readType.contain(Const.ReadType.PROPERTY) && getCache()[GET_LATEST__DATE] != null && propaty()) return@rxSingle
            // get DB cache.
            if (readType.contain(Const.ReadType.LOCAL_DB) && db()) return@rxSingle
            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getLatest"
            ).setSuccessBlock{ t ->
                val cachingStartTime = System.currentTimeMillis();
                // caching
                if(readType.contain(Const.ReadType.LOCAL_DB)) {
                    t.rates?.forEach { rate ->
                        val table: Sample_Table = Sample_Table().creat(t.date, t.base, rate.key, rate.value)
                        sampleDao.insert(table)
                    }
                    db()
                } else if(readType.contain(Const.ReadType.PROPERTY)) {
                    var c = mutableListOf<Entity>()
                    t.rates?.forEach { rate ->
                        if(base == t.base) c.add(Entity(t.base, rate.key, t.date, rate.value))
                    }
                    cacheGetLatest = c
                    getCache().put(GET_LATEST__DATE, Util.dateToString(Date()))
                    propaty()
                }
                val cachingEndTime = System.currentTimeMillis();
                ULog.info("Sample2_Repository", "cachingTime = ${cachingEndTime - cachingStartTime}")
                // return value
                // subscriber.onSuccess(t)
            }.setErrorBlock { e ->
                // return value
                subscriber.onError(e)
            }

            // get an entity from api.
            if(readType.contain(Const.ReadType.API)) {
                sampleApi.getLatest(base/*, symbols*/).subscribe(apiSubscriber)
            }
        }}
    }

    fun getPast(date: String, base: String, symbols: String, readType: Const.ReadType): Single<List<Entity>> {
        return rxSingle { subscriber -> run {

            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getPast"
            ).setSuccessBlock{ t ->
                var c = mutableListOf<Entity>()
                t.rates?.forEach { rate ->
                    if(base == t.base) c.add(Entity(t.base, rate.key, t.date, rate.value))
                }
                // caching
                if(readType.contain(Const.ReadType.PROPERTY)) {
                    cacheGetPast = c
                    getCache().put(GET_PAST__DATE, Util.dateToString(Date()))
                }
                // return value
                subscriber.onSuccess(c)
            }.setErrorBlock { e ->
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