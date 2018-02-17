package tech.takenoko.androidmvvm.common

import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivityWithFragment: BaseActivity() {

    /**
     * (abstract setter) Fragment.
     */
    abstract fun fragment(): BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        // ULog.info(log, "called onCreate.")
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        ULog.info(log, "called addFragment.")
        savedInstanceState ?: fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment()).commit()
    }

    override fun onBackPressed() {
        ULog.info(log, "called onBackPressed.")
        fragment().onBackPressed()
        // super.onBackPressed()
    }
}