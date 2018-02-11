package tech.takenoko.androidmvvm

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by takenoko on 2018/02/09.
 */
class AndroidApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        // Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler(this, "AndroidApplication"));
    }
}
