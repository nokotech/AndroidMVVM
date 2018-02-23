package tech.takenoko.androidmvvm.page_sample2


import rx.Observable
import rx.Subscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
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
        viewModel.sampleText.update("loding....", BR.sampleText)

        // define subscriber.
        val subscriber = object: Subscriber<Sample_Api.GetLatestEntity>() {
            override fun onNext(t: Sample_Api.GetLatestEntity?) {
                ULog.debug("Sample2_Usecase", "subscriber.onNext")
            }
            override fun onCompleted() { onMainThread {
                val latest: Sample_Api.GetLatestEntity? = repository.getPropaty()["latest"]
                val past: Sample_Api.GetLatestEntity? = repository.getPropaty()["past"]

                //
                latest?.rates?.forEach {
                    val text1 = "${it.key} / ${latest.base}"
                    val text2 = "${it.value}"
                    ULog.debug("latest.rates", "${text1}, ${text2}")
                    // calc
                    if(it.value.isNullOrBlank()) return@forEach
                    val sub = BigDecimal(it.value) - BigDecimal(past?.rates?.get(it.key)?:"0")
                    val rate = (sub / BigDecimal(it.value)).multiply(BigDecimal(100)) + BigDecimal(1)
                    // insert
                    val index: Int = viewModel.latestButtonList.indexOfFirst { it.text1 == text1 }
                    if(index > -1) {
                        val row = viewModel.latestButtonList[index]
                        row.text1 = text1
                        row.text2 = text2
                        row.text3 = rate.toInt()
                        viewModel.latestButtonList[index] = row
                    } else {
                        viewModel.latestButtonList.add(Sample2_CustomAdapter.SampleList(text1, text2, rate.toInt()))
                    }
                }
                // sort.
                viewModel.latestButtonList.sortByDescending { java.lang.Float.parseFloat(it.text2) }
                // API call sucess.
                ULog.info("subscriber", "onCompleted called.")
                viewModel.sampleText.update("Success [${latest?.date}]", BR._all)
            }}

            override fun onError(error: Throwable?) { onMainThread {
                // API call error.
                ULog.error("subscriber", "onError called. ${error.toString()}")
                viewModel.sampleText.update("Error", BR._all)
            }}
        }
        // get repository.
        Observable.merge(
                repository.getLatest("USD", "JPY", Const.ReadType.PROPERTY).toObservable(),
                repository.getPast("2017-02-18", "USD", "JPY", Const.ReadType.PROPERTY).toObservable()
        ).subscribe(subscriber)
    }

}