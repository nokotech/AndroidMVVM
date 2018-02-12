package tech.takenoko.androidmvvm.page_sample2

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by takenoko on 2018/02/12.
 */
interface Sample2_ApiProtocol {

    @GET("latest")
    fun getLatest(
            @Query("base") base: String,
            @Query("symbols") symbols: String): Observable<Sample2_Entity.Response>
}