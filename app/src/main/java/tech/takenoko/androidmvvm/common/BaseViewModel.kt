package tech.takenoko.androidmvvm.common

import android.databinding.BaseObservable
import android.view.View
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/11.
 */
abstract class BaseViewModel(log: String): BaseObservable() {

    val log: String = log;

    /** Sample DI. */
    // @inject
    // val xxxxUsecase: XxxxUsecase

    init {
        ULog.info(log, "called init.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onCreate() {
        ULog.info(log, "called onCreate.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onStart(){
        ULog.info(log, "called onStart.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onResume(){
        ULog.info(log, "called onResume.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onPause(){
        ULog.info(log, "called onPause.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onStop(){
        ULog.info(log, "called onStop.")
    }

    /**
     * Android lifecycle. this function is override or overload.
     */
    open fun onBackPressed() {
        ULog.info(log, "called onBackPressed")
    }

    open fun onClickButton(view: View) {
        ULog.info(log, "called onClickButton. id = " + view.id)
        view.isEnabled = false

        // prevent double tapping.
        view.postDelayed({ view.isEnabled = true }, 1000L)
    }
}