package tech.takenoko.androidmvvm.modules

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import tech.takenoko.androidmvvm.Page1Test
import tech.takenoko.androidmvvm.application.App
import javax.inject.Singleton

/**
 * Created by takenaka on 2018/05/30.
 */
@Singleton
@Component(modules = arrayOf(
        TestModules::class,
        AndroidInjectionModule::class
))
interface AppTestComponent: AndroidInjector<DaggerApplication> {

    fun inject(test: Page1Test)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppTestComponent
    }
}
