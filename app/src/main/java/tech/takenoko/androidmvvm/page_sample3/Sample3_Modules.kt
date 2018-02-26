package tech.takenoko.androidmvvm.page_sample3

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import tech.takenoko.androidmvvm.page_sample2.Sample2_Activity
import javax.inject.Scope

/**
 * Created by takenoko on 2018/02/11.
 */
interface Sample3_Modules {

    /**
     * Activity
     */
    @Subcomponent
    interface RouteActivitySubcomp: AndroidInjector<Sample3_Activity> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<Sample3_Activity>()
    }

    /**
     * Activity
     */
    @Module(subcomponents = arrayOf(RouteActivitySubcomp::class))
    abstract class RouteActivityModule {

        @Binds
        @IntoMap
        @ActivityKey(Sample3_Activity::class)
        internal abstract fun bindAndroidInjectorFactory(builder: Sample3_Modules.RouteActivitySubcomp.Builder): AndroidInjector.Factory<out Activity>
    }

}