package tech.takenoko.androidmvvm

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import tech.takenoko.androidmvvm.page_sample0.RouteModules
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/10.
 */
@Singleton
@Component(modules = arrayOf(
        RouteModules.RouteActivityModule::class,
        RouteModules.RouteFragmentModule::class,
        AndroidInjectionModule::class
))
interface ApplicationComponent: AndroidInjector<AndroidApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: AndroidApplication): Builder
        fun build(): ApplicationComponent
    }
}
