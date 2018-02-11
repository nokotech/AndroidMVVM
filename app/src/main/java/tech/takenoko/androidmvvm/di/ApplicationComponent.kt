package tech.takenoko.androidmvvm.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import android.app.Application
import dagger.BindsInstance
import dagger.android.AndroidInjector
import tech.takenoko.androidmvvm.AndroidApplication
import tech.takenoko.androidmvvm.page_sample0.RouteModules

/**
 * Created by takenoko on 2018/02/10.
 */
@Singleton
@Component(modules = arrayOf(
        RouteModules.MainActivityModule::class,
//        RouteModules.RouteFragmentModule::class,
        AndroidInjectionModule::class
))
interface ApplicationComponent : AndroidInjector<AndroidApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
