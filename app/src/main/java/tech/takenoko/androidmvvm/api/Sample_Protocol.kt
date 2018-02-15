package tech.takenoko.androidmvvm.api

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import rx.Single

/**
 * Created by takenoko on 2018/02/12.
 */
interface Sample_Protocol {

    @GET("latest")
    fun getLatestProtocol(
            @Query("base") base: String,
            @Query("symbols") symbols: String): Single<Sample_Api.GetLatestEntity>
}