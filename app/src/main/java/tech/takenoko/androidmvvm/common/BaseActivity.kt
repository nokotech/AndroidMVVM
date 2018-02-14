package tech.takenoko.androidmvvm.common

import android.os.Bundle
import dagger.android.DaggerActivity
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity: DaggerActivity() {

    abstract val log: String;

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
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onStart() {
        ULog.info(log, "called onStart.")
        super.onStart()
        viewModel.onStart()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onResume() {
        ULog.info(log, "called onResume.")
        super.onResume()
        viewModel.onResume()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onPause() {
        ULog.info(log, "called onPause.")
        super.onPause()
        viewModel.onPause()
    }

    /**
     * Android lifecycle. this function is override.
     */
    override fun onStop() {
        ULog.info(log, "called onStop.")
        super.onStop()
        viewModel.onStop()
    }

    /**
     * Back Button Action.
     */
    override fun onBackPressed() {
        ULog.info(log, "called onBackPressed.")
        super.onBackPressed()
        viewModel.onBackPressed()
    }
}