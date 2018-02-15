package tech.takenoko.androidmvvm.page_sample2

import rx.Single
import rx.SingleSubscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseUsecase
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    override val log: String = "Sample2_Usecase"

    /** DI Repository. */
    @Inject lateinit var repository: Sample2_Repository

    /**
     * get repository data.
     * - ViewModel.bindingProperty(ObservableField) --> (Observable)
     * - notifyPropertyChanged(BR.xxx);
     */
    fun getSampleText(viewModel: Sample2_ViewModel) {
        ULog.debug("Sample2_Usecase", "getSampleText.")

        // loading.
        viewModel.sampleText.update("loding....", BR._all)

        // define subscriber.
        val subscriber = object: SingleSubscriber<Sample_Api.GetLatestEntity>() {
            override fun onSuccess(t: Sample_Api.GetLatestEntity?) {
                val text = "base: ${t?.base}, date: ${t?.date}, rate:${t?.rates.toString()}}"
                viewModel.sampleText.update(text, BR._all)
            }
            override fun onError(error: Throwable?) {
                viewModel.sampleText.update("Error", BR._all)
            }
        }

        // get repository.
        repository.getLatest(Const.ReadType.API).subscribe(subscriber)
    }
}