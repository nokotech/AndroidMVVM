package tech.takenoko.androidmvvm.page_sample2

import rx.SingleSubscriber
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.common.CustomSubscriber
import tech.takenoko.androidmvvm.utility.Util
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, String>() {

    override val log: String = "Sample2_Repository"

    /** DI Api. */
    @Inject lateinit var sampleApi: Sample_Api

    /** cache key */
    val GET_LATEST__DATE = "GET_LATEST__DATE"

    /** cache property */
    var cacheGetLatest: Sample_Api.GetLatestEntity? = null


    /**
     * get repository data.
     * @param subscriber callback subscriber.
     * @param readType read place
     */
    fun getLatest(subscriber: SingleSubscriber<in Sample_Api.GetLatestEntity>, readType: Const.ReadType) {

        // get property cache.
        if(readType.contain(Const.ReadType.PROPERTY) && getCache().get(GET_LATEST__DATE) != null) {
            return subscriber.onSuccess(cacheGetLatest)
        }

        // get an entity from api.
        if(readType.contain(Const.ReadType.API)) {
            val subscriber = object: CustomSubscriber<Sample_Api.GetLatestEntity>(log) {
                override fun onNext(response: Sample_Api.GetLatestEntity?) {
                    super.onNext(response)
                    // caching
                    if(readType.contain(Const.ReadType.PROPERTY)) {
                        cacheGetLatest = response
                        getCache().put(GET_LATEST__DATE, Util.dateToString(Date()))
                    }
                    // return value
                    subscriber.onSuccess(response)
                }
            }
            sampleApi.getLatest(subscriber, "USD", "JPY")
            return
        }

        // other case.
        return
    }
}