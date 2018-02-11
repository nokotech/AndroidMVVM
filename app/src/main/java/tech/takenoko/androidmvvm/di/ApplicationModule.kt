package tech.takenoko.androidmvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.takenoko.androidmvvm.page_sample0.RouteActivity

/**
 * Created by takenoko on 2018/02/11.
 */
@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun contributeRouteActivity(): RouteActivity
}