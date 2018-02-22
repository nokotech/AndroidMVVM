package tech.takenoko.androidmvvm.page_sample2

import io.reactivex.android.schedulers.AndroidSchedulers
import rx.SingleSubscriber
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseCustomAdapter
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
        viewModel.sampleText.update("loding....", BR.sampleText)

        // define subscriber.
        val subscriber = object: SingleSubscriber<Sample_Api.GetLatestEntity>() {
            override fun onSuccess(t: Sample_Api.GetLatestEntity?) {

                val latest: Sample_Api.GetLatestEntity? = repository.getPropaty()["latest"]
                val past: Sample_Api.GetLatestEntity? = repository.getPropaty()["past"]

                //
                latest?.rates?.forEach {
                    val text1 = "${it.key} / ${latest.base}"
                    val text2 = "${it.value}"
                    // calc
//                    val past = past?.rates?.get(it.key)
//                    ULog.debug("=====", past?:"")
//                    val sub = BigDecimal(it.value) - BigDecimal(past?.rates?.get(it.key))
//                    val rate = (sub / BigDecimal(text2)).multiply(BigDecimal(100)) + BigDecimal(1)
                    // insert
                    val index: Int = viewModel.latestButtonList.indexOfFirst { it.text1 == text1 }
                    if(index > -1) {
                        val row = viewModel.latestButtonList[index]
                        row.text1 = text1
                        row.text2 = text2
                        row.text3 = 0//rate.toInt()
                        viewModel.latestButtonList[index] = row
                    } else {
                        viewModel.latestButtonList.add(BaseCustomAdapter.SampleList(text1, text2, 0/*rate.toInt()*/))
                    }
                }
                // sort.
                // viewModel.latestButtonList.sortByDescending { java.lang.Float.parseFloat(it.text2) }

                // API call sucess.
                viewModel.sampleText.update("Success [${latest?.date}]", BR._all)
            }
            override fun onError(error: Throwable?) {
                // API call error.
                ULog.error("onError", error.toString())
                viewModel.sampleText.update("Error", BR._all)
            }
        }

        val nextSubscriber = object: SingleSubscriber<Sample_Api.GetLatestEntity>() {
            override fun onSuccess(t: Sample_Api.GetLatestEntity?) {
                repository.getPast("2017-02-18", "USD", "JPY", Const.ReadType.PROPERTY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(subscriber)
            }
            override fun onError(error: Throwable?) {
                // API call error.
                ULog.error("onError", error.toString())
                viewModel.sampleText.update("Error", BR._all)
            }
        }
        // get repository.
        repository.getLatest("USD", "JPY", Const.ReadType.PROPERTY).subscribe(nextSubscriber)

    }

}