package tech.takenoko.androidmvvm.page_sample2

import rx.SingleSubscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseCustomAdapter
import tech.takenoko.androidmvvm.common.BaseUsecase
import tech.takenoko.androidmvvm.utility.ULog
import java.math.BigDecimal
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
                //
                t?.rates?.forEach {
                    val text1 = "${it.key} / ${t?.base}"
                    val text2 = "${it.value}"
                    val index: Int = viewModel.latestButtonList.indexOfFirst { it.text1 == text1 }
                    if(index > -1) {
                        val row = viewModel.latestButtonList.get(index)
                        if(t?.date.equals("2017-02-17")) {
                            val sub = BigDecimal(row.text2) - BigDecimal(text2)
                            val rate = (sub / BigDecimal(text2)).multiply(BigDecimal(100)) + BigDecimal(1)
                            row.text3 = rate.toInt()
                        } else {
                            row.text1 = text1
                            row.text2 = text2
                        }
                        viewModel.latestButtonList.set(index, row)
                    } else {
                        viewModel.latestButtonList.add(BaseCustomAdapter.SampleList(text1, text2, 0))
                    }
                }
                // sort.
                viewModel.latestButtonList.sortByDescending { java.lang.Float.parseFloat(it.text2) }

                // API call sucess.
                viewModel.sampleText.update("Success [${t?.date}]", BR._all)
            }
            override fun onError(error: Throwable?) {
                // API call error.
                viewModel.sampleText.update("Error", BR._all)
            }
        }

        // get repository.
        repository.getLatest("USD", "JPY", Const.ReadType.API).subscribe(subscriber)
        repository.getPast("2017-02-18", "USD", "JPY", Const.ReadType.API).subscribe(subscriber)
    }

}