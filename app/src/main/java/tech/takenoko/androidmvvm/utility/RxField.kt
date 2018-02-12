package tech.takenoko.androidmvvm.utility

import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableField
import rx.Observable
import rx.Subscription


/**
 * Created by takenoko on 2018/02/12.
 */
class RxField<T> : ObservableField<T> {

    private val observable: Observable<T>
    private val sucscriptionMap = HashMap<Int, Subscription>()

    constructor(observable: Observable<T>) : super() {
        this.observable = observable
    }

    constructor(observable: Observable<T>, defaultValue: T) : super(defaultValue) {
        this.observable = observable
    }

    @Synchronized override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        super.addOnPropertyChangedCallback(callback)
        sucscriptionMap.put(callback.hashCode(), observable.subscribe({ value -> set(value) }))
    }

    @Synchronized override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        if (sucscriptionMap.containsKey(callback.hashCode())) {
            val subscription = sucscriptionMap[callback.hashCode()]
            subscription?.unsubscribe()
            sucscriptionMap.remove(callback.hashCode())
        }
        super.removeOnPropertyChangedCallback(callback)
    }

    override fun set(value: T) {
        super.set(value)
    }

    fun tObservable(): Observable<T> {
        return observable
    }
}