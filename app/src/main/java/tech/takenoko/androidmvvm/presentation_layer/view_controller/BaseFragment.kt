package tech.takenoko.androidmvvm.presentation_layer.view_controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.DaggerFragment
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseFragment: DaggerFragment() {

    abstract val log: String;
    abstract fun layoutId(): Int

    init {
        // don't regeneration
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ULog.info(log, "called onCreate.")
        return inflater?.inflate(layoutId(), container, false)
    }

    open fun onBackPressed() {
        ULog.info(log, "called onBackPressed.")
    }
}