package tech.takenoko.androidmvvm

import android.app.Application
import tech.takenoko.androidmvvm.di.ApplicationComponent
import tech.takenoko.androidmvvm.di.DaggerApplicationComponent


/**
 * Created by takenoko on 2018/02/09.
 */
class AndroidApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }
}
