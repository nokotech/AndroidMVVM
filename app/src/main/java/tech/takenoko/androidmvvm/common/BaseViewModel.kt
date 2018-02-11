package tech.takenoko.androidmvvm.common

import android.databinding.BaseObservable
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseViewModel : BaseObservable() {

    abstract val log : String;

    init {
        ULog.info("BaseViewModel", "called. init()")
    }

}