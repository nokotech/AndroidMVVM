package tech.takenoko.androidmvvm.common.custom

import rx.Subscriber
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/12.
 */
open class CustomSubscriber<T>(val log: String) : Subscriber<T>(){
    override fun onCompleted() {
        ULog.info(log, "onCompleted build.")
    }
    override fun onNext(t: T?) {
        ULog.info(log, "onNext build.")
    }
    override fun onError(e: Throwable?) {
        ULog.info(log, "onError build.")
    }
}