package tech.takenoko.androidmvvm.page_sample1

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
interface Sample1_Modules {

    /**
     * annotation of Sample0
     */
    @Scope @Retention(AnnotationRetention.RUNTIME) @MustBeDocumented
    annotation class AnnotSample0Scope

    /**
     * Activity
     */
    @Subcomponent
    interface RouteActivitySubcomp: AndroidInjector<Sample1_Activity> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<Sample1_Activity>()
    }

    /**
     * Activity
     */
    @Module(subcomponents = arrayOf(RouteActivitySubcomp::class))
    abstract class RouteActivityModule {

        @Binds @IntoMap @ActivityKey(Sample1_Activity::class)
        internal abstract fun bindAndroidInjectorFactory(builder: Sample1_Modules.RouteActivitySubcomp.Builder): AndroidInjector.Factory<out Activity>
    }

    /**
     * Fragment
     */
    @Subcomponent
    interface RouteFragmentSubcomp: AndroidInjector<Sample1_Fragment1> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<Sample1_Fragment1>()
    }

    /**
     * Fragment
     */
    @Module(subcomponents = arrayOf(Sample1_Modules.RouteFragmentSubcomp::class))
    abstract class RouteFragmentModule {

        @Binds @IntoMap @FragmentKey(Sample1_Fragment1::class)
        internal abstract fun bindRouteFragment(builder: Sample1_Modules.RouteFragmentSubcomp.Builder): AndroidInjector.Factory<out Fragment>

        @Subcomponent
        interface MainFragmentSubComponent: AndroidInjector<Sample1_Fragment1> {
            @Subcomponent.Builder
            abstract class Builder: AndroidInjector.Builder<Sample1_Fragment1>()
        }
    }
}
