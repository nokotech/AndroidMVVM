package tech.takenoko.androidmvvm.page_sample2

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.schedulers.Schedulers.single
import rx.Single
import rx.SingleSubscriber
import rx.Subscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.SingleSubscriberBlock
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.utility.CustomSubscriber
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
     * get repository data return Single.
     * @param readType select ReadType.
     * @return Sample_Api.GetLatestEntity of Single emitter.
     */
    fun getLatest(readType: Const.ReadType): Single<Sample_Api.GetLatestEntity> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (readType.contain(Const.ReadType.PROPERTY) && getCache()[GET_LATEST__DATE] != null) {
                subscriber.onSuccess(cacheGetLatest)
            }

            // get an entity from api.
            if(readType.contain(Const.ReadType.API)) {
                val apiSubscriber = object: CustomSubscriber<Sample_Api.GetLatestEntity>(log) {
                    override fun onNext(t: Sample_Api.GetLatestEntity?) {
                        super.onNext(t)
                        // caching
                        if(readType.contain(Const.ReadType.PROPERTY)) {
                            cacheGetLatest = t
                            getCache().put(GET_LATEST__DATE, Util.dateToString(Date()))
                        }
                        // return value
                        subscriber.onSuccess(t)
                    }
                }
                sampleApi.getLatest(apiSubscriber, "USD", "JPY")
            }
        }}
    }
}