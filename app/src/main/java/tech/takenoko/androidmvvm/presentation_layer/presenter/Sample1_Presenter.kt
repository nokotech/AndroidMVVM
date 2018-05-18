package tech.takenoko.androidmvvm.presentation_layer.presenter

import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.database.SharedPref
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample1_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample1_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample1_ViewModel
import javax.inject.Inject

/**
 * Created by takenaka on 2018/05/18.
 */

class Sample1_Presenter @Inject constructor(app: App): BasePresenter(app) {

    /** ViewModel */
    @Inject lateinit var viewModel: Sample1_ViewModel

    /** DI Usecase */
    @Inject lateinit var sample1Usecase: Sample1_Usecase

    /** */
    @Inject lateinit var navigator: CommonNavigator<Sample1_Activity>
    @Inject lateinit var preference: SharedPref
    @Inject lateinit var sampleDao: Sample_Dao


    override fun loadView() {
        viewModel.title = sample1Usecase.changeTitle2()
    }

    fun onClickToNavigator() {
        navigator.next<Sample2_Activity>()// title = sample1Usecase.cangeTitle1()
        viewModel.notifyPropertyChanged(BR._all)
    }

    fun onClickToOK() {
        viewModel.title = sample1Usecase.changeTitle2()
        viewModel.notifyPropertyChanged(BR._all)
    }

    fun onClickToPreference() {
        preference.allClear()
        sampleDao.deleteAll()
        viewModel.notifyPropertyChanged(BR._all)
    }
}