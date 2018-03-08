package tech.takenoko.androidmvvm.utility

import rx.Observable
import rx.Subscriber
import rx.Subscription
import tech.takenoko.androidmvvm.CompletedBlock
import tech.takenoko.androidmvvm.ErrorBlock
import tech.takenoko.androidmvvm.NextBlock


/**
 * Created by takenoko on 2018/02/15.
 */
class CustomObservable {

    val log: String = "CustomObservable"

    fun <T> Observable<T>.onError(block: ErrorBlock): ExtendSubscription<T> {
        ULog.info(log, "Observable.onError called.")
        return ExtendSubscription(this).onError(block)
    }

    fun <T> Observable<T>.onCompleted(block: CompletedBlock): ExtendSubscription<T> {
        ULog.info(log, "Observable.onNext called.")
        return ExtendSubscription(this).onCompleted(block)
    }

    fun <T> Observable<T>.onNext(block: NextBlock<T>): ExtendSubscription<T> {
        ULog.info(log, "Observable.onCompleted called.")
        return ExtendSubscription(this).onNext(block)
    }

    fun Subscription.onError(block: ErrorBlock): Subscription {
        ULog.info(log, "Subscription.onError called.")
        return this
    }

    inner class ExtendSubscription<T>(val observable: Observable<T>) {
        private var next: NextBlock<T>          = {}
        private var error: ErrorBlock           = { throw  it!! }
        private var completed: CompletedBlock   = {}

        fun onError(block: ErrorBlock): ExtendSubscription<T> {
            error = block
            return this
        }
        fun onCompleted(block: CompletedBlock): ExtendSubscription<T> {
            completed = block
            return this
        }
        fun onNext(block: NextBlock<T>): ExtendSubscription<T> {
            next = block
            return this
        }
        fun subcribe(): Subscription = observable.subscribe(object: Subscriber<T>(){
            override fun onError(e: Throwable?) {
                if(e == null) return
                error.invoke(e)
            }
            override fun onCompleted() {
                completed.invoke()
            }
            override fun onNext(t: T) {
                next.invoke(t)
            }
        })
    }
}
