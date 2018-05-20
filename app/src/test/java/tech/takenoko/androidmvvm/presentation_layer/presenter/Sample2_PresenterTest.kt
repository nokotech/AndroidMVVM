package tech.takenoko.androidmvvm.presentation_layer.presenter

import android.util.Log
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import rx.Observable
import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample2_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_ViewModel

/**
 * Created by takenoko on 2018/05/19.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Sample1_Presenter::class, Sample2_Usecase::class, CommonNavigator::class, Log::class)
class Sample2_PresenterTest {

    val sample2Presenter= Sample2_Presenter(App())

    // Mock
    @Mock
    lateinit var sample2Usecase: Sample2_Usecase
    @Mock
    lateinit var commonNavigator: CommonNavigator<Sample2_Activity>

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        sample2Presenter.usecase = sample2Usecase;
        sample2Presenter.viewModel = Sample2_ViewModel()
        sample2Presenter.navigator = commonNavigator
        Mockito.`when`(sample2Usecase.getSampleText()).thenReturn(Observable.create { a -> null })
    }

    @Test
    fun loadView() {
        sample2Presenter.loadView()
    }

    @Test
    fun onClickToNavigator() {
        sample2Presenter.onClickToCallApi()
    }
}