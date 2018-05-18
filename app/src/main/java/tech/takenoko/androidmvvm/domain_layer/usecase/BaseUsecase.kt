package tech.takenoko.androidmvvm.domain_layer.usecase

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Handler
import android.os.Looper
import android.os.Looper.getMainLooper
import tech.takenoko.androidmvvm.DefaultBlock

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseUsecase {

    abstract val log: String;

    /**
     * Mock Function1
     */
    // fun function1(viewModel: BaseViewModel) {
    //     BaseRepository.func1()
    // }
}