package tech.takenoko.androidmvvm.presentation_layer.view_controller

import android.os.Bundle
import android.view.View
import dagger.android.DaggerActivity
import tech.takenoko.androidmvvm.presentation_layer.view_model.BaseViewModel
import tech.takenoko.androidmvvm.utility.ApiLog
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity: DaggerActivity(), View.OnClickListener {

    abstract val log: String;
    @Inject lateinit var apiLog: ApiLog

    /** ViewModel */
    private lateinit var viewModel: BaseViewModel

    /**
     * binding ViewModel
     */
    protected fun bindViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        // this.viewModel.onCreate()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        ULog.info(log, "called onCreate.")
        super.onCreate(savedInstanceState)
        apiLog.post(log, "onCreate")
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onStart() {
        ULog.info(log, "called onStart.")
        super.onStart()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onResume() {
        ULog.info(log, "called onResume.")
        super.onResume()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onPause() {
        ULog.info(log, "called onPause.")
        super.onPause()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onStop() {
        ULog.info(log, "called onStop.")
        super.onStop()
    }

    /**
     * Back Button Action.
     */
    override fun onBackPressed() {
        ULog.info(log, "called onBackPressed.")
        super.onBackPressed()
    }

    override fun onClick(view: View?) {
        ULog.info(log, "called onClickButton. id = " + view?.id)
        view?.isEnabled = false
        // prevent double tapping.
        view?.postDelayed({ view.isEnabled = true }, 1000L)
    }
}