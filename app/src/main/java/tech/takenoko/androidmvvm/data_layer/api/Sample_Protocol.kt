package tech.takenoko.androidmvvm.data_layer.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single
import tech.takenoko.androidmvvm.data_layer.api.Sample_Api

/**
 * Created by takenoko on 2018/02/12.
 */
interface Sample_Protocol {

    /**
     * Foreign exchange rates and currency conversion API
     * - http://fixer.io/
     * @param base
     * @param symbols
     * @return Single
     */
    @GET("latest")
    fun getLatest(
            @Query("base") base: String
            //, @Query("symbols") symbols: String
    ): Single<Sample_Api.GetLatestEntity>

    @GET("{date}")
    fun getPast(
            @Path("date") date: String
            ,@Query("base") base: String
            //, @Query("symbols") symbols: String
    ): Single<Sample_Api.GetLatestEntity>
}