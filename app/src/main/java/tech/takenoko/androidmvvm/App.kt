package tech.takenoko.androidmvvm

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by takenoko on 2018/02/09.
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
