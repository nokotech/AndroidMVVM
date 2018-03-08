package tech.takenoko.androidmvvm.utility

import com.google.gson.Gson
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Log_Api
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.cache.Sample_Cache
import tech.takenoko.androidmvvm.database.Sample_Table
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/03/08.
 */
@Singleton
class ApiLog @Inject constructor() {

    val log: String = "ApiLog"

    @Inject lateinit var logApi: Log_Api

    fun post(userId: String, eventId: String) {
        val apiSubscriber = RxSingleSubscriber<Gson>("ApiLog.putLog")
        apiSubscriber.setSuccessBlock{ t -> ULog.debug(log, "success.") }
        apiSubscriber.setErrorBlock { e -> ULog.error(log, "error.") }
        logApi.putLog(userId, eventId).subscribe(apiSubscriber)
    }
}