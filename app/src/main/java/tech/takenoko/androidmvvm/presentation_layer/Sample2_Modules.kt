package tech.takenoko.androidmvvm.presentation_layer

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import javax.inject.Scope


/**
 * Created by takenoko on 2018/02/11.
 */
interface Sample2_Modules {

    /**
     * annotation of Sample0
     */
    @Scope
    @Retention(AnnotationRetention.RUNTIME) @MustBeDocumented
    annotation class AnnotSample2Scope

    /**
     * Activity
     */
    @Subcomponent
    interface RouteActivitySubcomp: AndroidInjector<Sample2_Activity> {

        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<Sample2_Activity>()
    }

    /**
     * Activity
     */
    @Module(subcomponents = arrayOf(RouteActivitySubcomp::class))
    abstract class RouteActivityModule {

        @Binds
        @IntoMap
        @ActivityKey(Sample2_Activity::class)
        internal abstract fun bindAndroidInjectorFactory(builder: RouteActivitySubcomp.Builder): AndroidInjector.Factory<out Activity>
    }

}