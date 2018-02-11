package tech.takenoko.androidmvvm

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import tech.takenoko.androidmvvm.page_sample1.Sample1_Modules
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/10.
 */
@Singleton
@Component(modules = arrayOf(
        Sample1_Modules.RouteActivityModule::class,
        Sample1_Modules.RouteFragmentModule::class,
        AndroidInjectionModule::class
))
interface ApplicationComponent: AndroidInjector<AndroidApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: AndroidApplication): Builder
        fun build(): ApplicationComponent
    }
}
