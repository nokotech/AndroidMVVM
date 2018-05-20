package tech.takenoko.androidmvvm.presentation_layer.presenter

import android.util.Log
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample1_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample1_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample1_ViewModel

/**
 * Created by takenoko on 2018/05/19.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Sample1_Presenter::class, Sample1_Usecase::class, CommonNavigator::class, Log::class)
class Sample1_PresenterTest {

    val sample1Presenter= Sample1_Presenter(App())

    // Mock
    @Mock lateinit var sample1Usecase: Sample1_Usecase
    @Mock lateinit var commonNavigator: CommonNavigator<Sample1_Activity>

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        sample1Presenter.sample1Usecase = sample1Usecase;
        sample1Presenter.viewModel = Sample1_ViewModel()
        sample1Presenter.navigator = commonNavigator
        Mockito.`when`(sample1Usecase.changeTitle2()).thenReturn("test_text")
    }

    @Test
    fun loadView() {
        sample1Presenter.loadView()
        Assert.assertEquals(sample1Presenter.viewModel.title, "test_text")
    }

    @Test
    fun onClickToNavigator() {
        sample1Presenter.onClickToNavigator()
    }

    @Test
    fun onClickToOK() {
        sample1Presenter.onClickToOK()
    }
}