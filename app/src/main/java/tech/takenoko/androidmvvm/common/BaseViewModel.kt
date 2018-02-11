package tech.takenoko.androidmvvm.common

import android.databinding.BaseObservable
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseViewModel(log: String): BaseObservable() {

    val log: String = log;

    /**
     * Sample DI.
     */
    // @inject
    // val xxxxUsecase: XxxxUsecase

    init {
        ULog.info(log, "called init.")
    }

    open fun onStart(){
        ULog.info(log, "called onStart.")
    }

    open fun onResume(){
        ULog.info(log, "called onResume.")
    }

    open fun onPause(){
        ULog.info(log, "called onPause.")
    }

    open fun onStop(){
        ULog.info(log, "called onStop.")
    }

    open fun onBackPressed() {
        ULog.info(log, "called onBackPressed")
    }
}