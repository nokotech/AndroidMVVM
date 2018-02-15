package tech.takenoko.androidmvvm

import rx.SingleSubscriber
import tech.takenoko.androidmvvm.common.BaseSubscriber

/**
 * Created by takenaka on 2018/02/15.
 */

/** RX */
typealias SuccessBlock<T>   = (T) -> Unit
typealias ErrorBlock        = (Throwable) -> Unit
typealias CompletedBlock    = () -> Unit
typealias NextBlock<T>      = (T) -> Unit

typealias SingleSubscriberBlock<T> = (SingleSubscriber<in T>) -> Unit

typealias RxSubscriber<T>  = BaseSubscriber.CustomSubscriber<T>
typealias RxSingleSubscriber<T>  = BaseSubscriber.CustomSingleSubscriber<T>