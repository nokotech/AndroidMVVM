package tech.takenoko.androidmvvm.common

import android.databinding.ObservableField

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseUsecase() {

    abstract val log: String;

    /**
     * (Extensions) change value and notify.
     */
    fun <T> ObservableField<T>.update(value: T, notify: Int) {
        this.set(value)
        notifyPropertyChanged(notify);
    }

    /**
     * Mock Function1
     */
    fun function1(viewModel: BaseViewModel) {
        // BaseRepository.func1()
    }
}