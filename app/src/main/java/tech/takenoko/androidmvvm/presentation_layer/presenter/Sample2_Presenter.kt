package tech.takenoko.androidmvvm.presentation_layer.presenter

import rx.Subscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.application.App
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.database.SharedPref
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample2_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_CustomAdapter
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_ViewModel
import tech.takenoko.androidmvvm.utility.ULog
import tech.takenoko.androidmvvm.utility.Util
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

    /**
     *
     */
    fun onClickToCallApi() {
        // loading.
        viewModel.sampleText.update("loading....", BR.sampleText)
        // define subscriber.
        val subscriber = object: Subscriber<List<Sample2_CustomAdapter.SampleList>>() {
            override fun onNext(t: List<Sample2_CustomAdapter.SampleList>) { onMainThread {
                ULog.debug("Sample2_Usecase", "subscriber.onNext")
                Util.endTime("Sample2_Usecase.getSampleText")
                // rewrite and sort.
                viewModel.latestButtonList.clear()
                viewModel.latestButtonList.addAll(t)
                viewModel.latestButtonList.sortByDescending { java.lang.Float.parseFloat(it.text2) }
            }}
            override fun onCompleted() {
                ULog.info("subscriber", "onCompleted called.")
                // API call sucess.
                viewModel.sampleText.update("Success", BR._all)
            }
            override fun onError(error: Throwable?) { onMainThread {
                // API call error.
                ULog.error("subscriber", "onError called. ${error.toString()}")
                viewModel.sampleText.update("Error", BR._all)
            }}
        }
        Util.startTime("Sample2_Usecase.getSampleText")
        usecase.getSampleText().subscribe(subscriber)
        // navigator.next<Sample1_Activity>()
    }
}