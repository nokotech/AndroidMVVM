package tech.takenoko.androidmvvm.api

import rx.Observable
import rx.Single
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.utility.ApiBuilder
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/13.
 */
class Sample_Api @Inject constructor() {

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
    fun getLatest(base: String, symbols: String): Single<GetLatestEntity> {
        return ApiBuilder
                .build(Const.BaseUrl.SAMPLE_API)
                .create(Sample_Protocol::class.java)
                .getLatestProtocol(base, symbols)
                .subscribeOn(Schedulers.newThread())
    }
}