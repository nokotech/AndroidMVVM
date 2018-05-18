package tech.takenoko.androidmvvm.data_layer.api

import rx.Single
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.Const
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/13.
 */
class Sample_Api @Inject constructor() : Sample_Protocol {

    /** Sample_Api.getLatest()'s entity. */
    data class GetLatestEntity (
            var base: String?,
            var date: String?,
            var rates: Map<String, String>?
    ): Serializable

    /**
     * Foreign exchange rates and currency conversion API
     * - http://fixer.io/
     * @param base
     * @param symbols
     * @return RxSingle
     */
    @Singleton
    override fun getLatest(base: String/*, symbols: String*/): Single<GetLatestEntity> {
        return ApiBuilder
                .build(Const.BaseUrl.SAMPLE_API)
                .create(Sample_Protocol::class.java)
                .getLatest(base/*, symbols*/)
                .subscribeOn(Schedulers.newThread())
    }

    @Singleton
    override fun getPast(date: String, base: String): Single<GetLatestEntity> {
        return ApiBuilder
                .build(Const.BaseUrl.SAMPLE_API)
                .create(Sample_Protocol::class.java)
                .getPast(date, base/*, symbols*/)
                .subscribeOn(Schedulers.newThread())
    }


}