package tech.takenoko.androidmvvm.common

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.DaggerFragment
import tech.takenoko.androidmvvm.AndroidApplication
import tech.takenoko.androidmvvm.di.ApplicationComponent

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseFragment : Fragment() {

    init {
        retainInstance = true
    }

    abstract fun layoutId(): Int

    /**
     * applicationComponent getter.
     */
    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).applicationComponent
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId(), container, false)
    }

    internal fun firstTimeCreated(savedInstanceState: Bundle?)
            = (savedInstanceState == null)

    open fun onBackPressed() {}
}