package tech.takenoko.androidmvvm.modules

import dagger.Module
import dagger.Provides
import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.presentation_layer.presenter.Sample1_Presenter

/**
 * Created by takenaka on 2018/05/30.
 */
@Module
class TestModules {

    @Provides
    fun provideSample1_Presenter(application: App): Sample1_Presenter {
        return Sample1_Presenter(application)
    }
}
