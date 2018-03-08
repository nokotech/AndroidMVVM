package tech.takenoko.androidmvvm.api

import com.google.gson.Gson
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Single
import java.util.*

/**
 * Created by takenoko on 2018/03/08.
 */
interface Log_Protocol {

    /**
     * Logging API
     * @param user_id
     * @param event_id
     * @return Single
     */
    @POST("log")
    fun putLog(
            @Query("user_id") userId: String,
            @Query("event_id") eventId: String
    ): Single<Gson>
}