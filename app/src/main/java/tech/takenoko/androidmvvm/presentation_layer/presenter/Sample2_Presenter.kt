package tech.takenoko.androidmvvm.presentation_layer.presenter

import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.database.SharedPref
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample2_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample1_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_ViewModel
import javax.inject.Inject

/**
 * Created by takenaka on 2018/05/18.
 */
class Sample2_Presenter @Inject constructor(app: App): BasePresenter(app) {

    /** ViewModel */
    @Inject lateinit var viewModel: Sample2_ViewModel

    /** DI Usecase */
    @Inject lateinit var usecase: Sample2_Usecase

    /** */
    @Inject lateinit var navigator: CommonNavigator<Sample2_Activity>
    @Inject lateinit var preference: SharedPref
    @Inject lateinit var sampleDao: Sample_Dao


    override fun loadView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onClickToCallApi() {
        usecase.getSampleText(viewModel)
        // navigator.next<Sample1_Activity>()
    }
}