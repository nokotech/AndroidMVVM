package tech.takenoko.androidmvvm.common

import android.os.Bundle
import dagger.android.DaggerActivity
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity : DaggerActivity() {

    abstract val log : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ULog.info(log, "called. onCreate()")
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}