package tech.takenoko.androidmvvm.common

import rx.SingleSubscriber
import rx.Subscriber
import tech.takenoko.androidmvvm.CompletedBlock
import tech.takenoko.androidmvvm.ErrorBlock
import tech.takenoko.androidmvvm.NextBlock
import tech.takenoko.androidmvvm.SuccessBlock
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/15.
 */
class BaseSubscriber {

    /**
     *
     */
    open class CustomSubscriber<T>(val log: String) : Subscriber<T>() {
        override fun onCompleted() {
            ULog.info(log, "onCompleted called.")
        }

        override fun onNext(t: T?) {
            ULog.info(log, "onNext called.")
        }

        override fun onError(e: Throwable?) {
            ULog.info(log, "onError called.")
        }

    }

    /**
     *
     */
    open class CustomSingleSubscriber<T>(val log: String) : SingleSubscriber<T>() {

        private var successBlock: SuccessBlock<T>    = {}
        private var errorBlock: ErrorBlock          = { throw  it }

        /** This method can not be override. */
        final override fun onSuccess(t: T) {
            ULog.info(log, "onSuccess called.")
            successBlock(t)
        }

        /** This method can not be override. */
        final override fun onError(e: Throwable?) {
            ULog.info(log, "onError called.")
//            errorBlock(e?)
        }

        fun setSuccessBlock(success: SuccessBlock<T>): CustomSingleSubscriber<T> {
            successBlock = success
            return this
        }

        fun setErrorBlock(error: ErrorBlock): CustomSingleSubscriber<T> {
            errorBlock = error
            return this
        }
    }
}
