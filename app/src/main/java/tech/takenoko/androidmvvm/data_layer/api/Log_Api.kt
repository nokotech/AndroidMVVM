package tech.takenoko.androidmvvm.data_layer.api

import com.google.gson.Gson
import rx.Single
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.Const
import javax.inject.Inject

/**
 * Created by takenaka on 2018/03/08.
 */
class Log_Api @Inject constructor(): Log_Protocol {

    override fun putLog(userId: String, eventId: String): Single<Gson> {
        return ApiBuilder
                .build(Const.BaseUrl.LOG_API)
                .create(Log_Protocol::class.java)
                .putLog(userId, eventId)
                .subscribeOn(Schedulers.newThread())
    }
}