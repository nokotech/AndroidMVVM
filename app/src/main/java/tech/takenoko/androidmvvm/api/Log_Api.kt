package tech.takenoko.androidmvvm.api

import com.google.gson.Gson
import rx.Single
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.constant.Const
import tech.takenoko.androidmvvm.utility.ApiBuilder
import javax.inject.Inject

/**
 * Created by takenaka on 2018/03/08.
 */
class Log_Api @Inject constructor(): Log_Protocol {

    override fun putLog(userId: String, eventId: String): Single<Gson> {
        return ApiBuilder
                .build(Const.env.BASE_URL_LOG_API)
                .create(Log_Protocol::class.java)
                .putLog(userId, eventId)
                .subscribeOn(Schedulers.newThread())
    }
}