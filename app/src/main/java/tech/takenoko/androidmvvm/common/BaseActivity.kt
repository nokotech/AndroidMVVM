package tech.takenoko.androidmvvm.common

import android.os.Bundle
import dagger.android.DaggerActivity
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity: DaggerActivity() {

    abstract val log: String;

    // binding
    private lateinit var viewModel: BaseViewModel

    protected fun bindViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        // this.viewModel.onCreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ULog.info(log, "called onCreate.")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        ULog.info(log, "called onStart.")
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        ULog.info(log, "called onResume.")
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        ULog.info(log, "called onPause.")
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        ULog.info(log, "called onStop.")
        super.onStop()
        viewModel.onStop()
    }

    override fun onBackPressed() {
        ULog.info(log, "called onBackPressed.")
        super.onBackPressed()
        viewModel.onBackPressed()
    }
}