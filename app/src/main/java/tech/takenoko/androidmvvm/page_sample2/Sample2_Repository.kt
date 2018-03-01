package tech.takenoko.androidmvvm.page_sample2

import rx.Single
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.cache.Sample_Cache
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.database.Sample_Dao
import tech.takenoko.androidmvvm.database.Sample_Table
import tech.takenoko.androidmvvm.utility.ULog
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, Date>() {

    override val log: String = "Sample2_Repository"

    /** DI DB and Api. */
    @Inject lateinit var sampleCache: Sample_Cache
    @Inject lateinit var sampleDao: Sample_Dao
    @Inject lateinit var sampleApi: Sample_Api

    /**
     * load database.
     * @param readType
     * @param subscriber
     * @param base
     */
    private fun loadDatabase(date: String, base: String): List<Sample_Cache.Entity>? {
        // db
        var list = mutableListOf<Sample_Cache.Entity>()
        sampleDao.findAll().forEach { t ->
            ULog.debug("loadDatabase", "[param] date = $date, base = $base")
            ULog.debug("loadDatabase", "[DB] date = ${t.date}, base = ${t.base}")
            if(base == t.base && date == t.date) list.add(Sample_Cache.Entity(t.base, t.target, t.date, t.rate))
        }
        return if(list.size > 0) list else null
    }

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getLatest(base: String, symbols: String): Single<List<Sample_Cache.Entity>> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (true == sampleCache.loadPropertyGetLatest()?.isNotEmpty()) {
                subscriber.onSuccess(sampleCache.loadPropertyGetLatest())
                return@rxSingle
            }
            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getLatest")
            apiSubscriber.setSuccessBlock{ t ->
                // clear cache.
                sampleCache.clearPropertyGetLatest()
                // caching DB.
                var entityList = mutableListOf<Sample_Cache.Entity>()
                t.rates?.forEach { rate ->
                    entityList.add(Sample_Cache.Entity(t.base, rate.key, t.date, rate.value))
                    sampleDao.insert(Sample_Table().creat(t.date, t.base, rate.key, rate.value))
                    sampleCache.savePropertyGetLatest(t.base, rate.key, t.date, rate.value)
                }
                // return value
                subscriber.onSuccess(entityList)
            }
            apiSubscriber.setErrorBlock { e ->
                // return value
                subscriber.onError(e)
            }

            // get an entity from api.
            sampleApi.getLatest(base/*, symbols*/).subscribe(apiSubscriber)
        }}
    }

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getPast(date: String, base: String, symbols: String): Single<List<Sample_Cache.Entity>> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (true == sampleCache.loadPropertyGetPast()?.isNotEmpty()) {
                subscriber.onSuccess(sampleCache.loadPropertyGetPast())
                return@rxSingle
            }
            // get DB cache.
            if (true == loadDatabase(date, base)?.isNotEmpty()) {
                loadDatabase(date, base)?.forEach { t -> sampleCache.savePropertyGetPast(t.base, t.target, t.date, t.rate) }
                subscriber.onSuccess(loadDatabase(date, base))
                return@rxSingle
            }
            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getPast")
            apiSubscriber.setSuccessBlock{ t ->
                // clear cache.
                sampleCache.clearPropertyGetPast()
                // caching DB.
                var entityList = mutableListOf<Sample_Cache.Entity>()
                t.rates?.forEach { rate ->
                    entityList.add(Sample_Cache.Entity(t.base, rate.key, t.date, rate.value))
                    sampleDao.insert(Sample_Table().creat(t.date, t.base, rate.key, rate.value))
                    sampleCache.savePropertyGetPast(t.base, rate.key, t.date, rate.value)
                }
                // return value
                subscriber.onSuccess(entityList)
            }
            apiSubscriber.setErrorBlock { e ->
                // return value
                subscriber.onError(e)
            }

            // get an entity from api.
            sampleApi.getPast(date, base/*, symbols*/).subscribe(apiSubscriber)
        }}
    }

}