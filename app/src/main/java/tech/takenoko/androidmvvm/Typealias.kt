package tech.takenoko.androidmvvm

import rx.SingleSubscriber

/**
 * Created by takenaka on 2018/02/15.
 */

/** RX */
typealias ErrorBlock        = (Throwable) -> Unit
typealias CompletedBlock    = () -> Unit
typealias NextBlock<T>      = (T) -> Unit

typealias SingleSubscriberBlock<T> = (SingleSubscriber<in T>) -> Unit