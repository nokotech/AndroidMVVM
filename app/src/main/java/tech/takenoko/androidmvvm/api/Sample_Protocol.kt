package tech.takenoko.androidmvvm.api

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by takenoko on 2018/02/12.
 */
interface Sample_Protocol {

    @GET("latest")
    fun getLatestProtocol(
            @Query("base") base: String,
            @Query("symbols") symbols: String): Observable<Sample_Api.GetLatestEntity>
}