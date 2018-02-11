package tech.takenoko.androidmvvm.common

import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import tech.takenoko.androidmvvm.utility.CLog
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseViewModel : BaseObservable() {

    abstract val log : String;

    init {
        CLog.info(log, "called. init()")
    }

}