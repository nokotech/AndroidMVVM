package tech.takenoko.androidmvvm.common

import android.app.Fragment
import android.os.Bundle
import tech.takenoko.androidmvvm.R

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivityWithFragment : BaseActivity() {

    /**
     * (abstract setter) Fragment.
     */
    abstract fun fragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
//        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) : Unit {
        savedInstanceState ?: fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment()).commit()
    }
}