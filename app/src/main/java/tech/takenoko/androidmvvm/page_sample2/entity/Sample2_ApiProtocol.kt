package tech.takenoko.androidmvvm.page_sample2.entity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by takenoko on 2018/02/12.
 */
interface Sample2_ApiProtocol {

    @GET("latest")
    fun getLatest(
            @Query("base") base: String,
            @Query("symbols") symbols: String): Call<Sample2_Entity>
}