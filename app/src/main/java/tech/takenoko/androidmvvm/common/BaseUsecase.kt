package tech.takenoko.androidmvvm.common

import android.databinding.ObservableField
import android.os.Handler
import android.os.Looper
import tech.takenoko.androidmvvm.DefaultBlock

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseUsecase() {

    abstract val log: String;

    /* MainThreadHandler */
    private val mHandler = Handler(Looper.getMainLooper())

    /**
     * execute Main Thread.
     * @param callback
     */
    fun onMainThread(callback: DefaultBlock) {
        mHandler.post { callback() }
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
     * Mock Function1
     */
    // fun function1(viewModel: BaseViewModel) {
    //     BaseRepository.func1()
    // }
}