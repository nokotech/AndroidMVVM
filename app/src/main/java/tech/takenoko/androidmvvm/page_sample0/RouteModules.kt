package tech.takenoko.androidmvvm.page_sample0

import android.app.Activity
import android.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.FragmentKey
import dagger.multibindings.IntoMap
import javax.inject.Scope

/**
 * Created by takenoko on 2018/02/11.
 */
interface RouteModules {

    /**
     * annotation of Sample0
     */
    @Scope @Retention(AnnotationRetention.RUNTIME) @MustBeDocumented
    annotation class AnnotSample0Scope

    /**
     * Activity
     */
    @Subcomponent
    interface RouteActivitySubcomp: AndroidInjector<RouteActivity> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<RouteActivity>()
    }

    /**
     * Activity
     */
    @Module(subcomponents = arrayOf(RouteActivitySubcomp::class))
    abstract class RouteActivityModule {

        @Binds @IntoMap @ActivityKey(RouteActivity::class)
        internal abstract fun bindAndroidInjectorFactory(builder: RouteModules.RouteActivitySubcomp.Builder): AndroidInjector.Factory<out Activity>
    }

    /**
     * Fragment
     */
    @Subcomponent
    interface RouteFragmentSubcomp: AndroidInjector<RouteFragment> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<RouteFragment>()
    }

    /**
     * Fragment
     */
    @Module(subcomponents = arrayOf(RouteModules.RouteFragmentSubcomp::class))
    abstract class RouteFragmentModule {

        @Binds @IntoMap @FragmentKey(RouteFragment::class)
        internal abstract fun bindRouteFragment(builder: RouteModules.RouteFragmentSubcomp.Builder): AndroidInjector.Factory<out Fragment>

        @Subcomponent
        interface MainFragmentSubComponent: AndroidInjector<RouteFragment> {
            @Subcomponent.Builder
            abstract class Builder: AndroidInjector.Builder<RouteFragment>()
        }
    }
}
