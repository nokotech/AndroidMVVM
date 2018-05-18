package tech.takenoko.androidmvvm.presentation_layer.view_model

import android.databinding.BaseObservable
import android.view.View
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseViewModel(log: String): BaseObservable() {

    val log: String = log;

    init {
        ULog.info(log, "called init.")
    }
}