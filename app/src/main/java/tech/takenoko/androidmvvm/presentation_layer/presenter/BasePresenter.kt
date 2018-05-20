package tech.takenoko.androidmvvm.presentation_layer.presenter

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Looper
import tech.takenoko.androidmvvm.DefaultBlock
import tech.takenoko.androidmvvm.application.App

/**
 * Created by takenaka on 2018/05/18.
 */
abstract class BasePresenter(val app: App) {

    /** Sample DI. */
    // @inject
    // val xxxxUsecase: XxxxUsecase


    /* MainThreadHandler */
    private val mHandler = android.os.Handler(Looper.getMainLooper())

    /**
     * execute Main Thread.
     * @param callback
     */
    fun onMainThread(callback: DefaultBlock) {
        if(Thread.currentThread() == Looper.getMainLooper()?.thread) {
            callback()
        } else {
            mHandler.post { callback() }
        }
    }

    /**
     * (Extensions) change value and notify on Main Thread.
     * @param value
     * @param notify target
     */
    fun <T> ObservableField<T>.update(value: T, notify: Int) {
        onMainThread {
            this.set(value)
            notifyPropertyChanged(notify)
        }
    }

    /**
     * (Extensions) change value and notify on Main Thread.
     * @param value
     * @param notify target
     */
    fun ObservableBoolean.update(value: Boolean, notify: Int) {
        onMainThread {
            this.set(value)
            notifyPropertyChanged(notify)
        }
    }

    abstract fun loadView()
}