package tech.takenoko.androidmvvm.page_sample2

import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
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
        viewModel.sampleText.update("loding....", BR._all)
        repository.getSampleText(viewModel.sampleText, Const.ReadType.API)
    }
}