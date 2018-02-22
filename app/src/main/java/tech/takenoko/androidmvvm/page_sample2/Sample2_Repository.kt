package tech.takenoko.androidmvvm.page_sample2

import com.google.gson.Gson
import rx.Single
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.utility.Util
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap


/**
 * Created by takenoko on 2018/02/11.
 *
 * リポジトリ層ではDB設計と同じイメージでデータ設計が必要
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, String>() {

    override val log: String = "Sample2_Repository"

    /** DI Api. */
    @Inject lateinit var sampleApi: Sample_Api

    /** cache key */
    val GET_LATEST__DATE = "GET_LATEST__DATE"
    val GET_PAST__DATE = "GET_PAST__DATE"

    /** cache property */
    var cacheGetLatest: Sample_Api.GetLatestEntity? = null
    var cacheGetPast: Sample_Api.GetLatestEntity? = null

    /**
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getLatest(base: String, symbols: String, readType: Const.ReadType): Single<Sample_Api.GetLatestEntity> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (readType.contain(Const.ReadType.PROPERTY) && getCache()[GET_LATEST__DATE] != null) {
                subscriber.onSuccess(cacheGetLatest)
            }

            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getLatest"
            ).setSuccessBlock{ t ->
                // caching
                if(readType.contain(Const.ReadType.PROPERTY)) {
                    cacheGetLatest = t
                    getCache().put(GET_LATEST__DATE, Util.dateToString(Date()))
                }
                // return value
                subscriber.onSuccess(t)
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

    fun getPast(date: String, base: String, symbols: String, readType: Const.ReadType): Single<Sample_Api.GetLatestEntity> {
        return rxSingle { subscriber -> run {

            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<Sample_Api.GetLatestEntity>("Sample2_Repository.getPast"
            ).setSuccessBlock{ t ->
                // caching
                if(readType.contain(Const.ReadType.PROPERTY)) {
                    cacheGetPast = t
                    getCache().put(GET_PAST__DATE, Util.dateToString(Date()))
                }
                // return value
                subscriber.onSuccess(t)
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

    /**
     * propaty getter.
     */
    fun getPropaty(): Map<String, Sample_Api.GetLatestEntity?> {
        return mapOf("latest" to cacheGetLatest, "past" to cacheGetPast)
    }
}