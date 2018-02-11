package tech.takenoko.androidmvvm.common

import android.os.Bundle
import tech.takenoko.androidmvvm.R

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivityWithFragment : BaseActivity() {

    /**
     * (abstract setter) Fragment.
     */
    abstract fun fragment(): BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        fragment().onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment()).commit()
    }
}