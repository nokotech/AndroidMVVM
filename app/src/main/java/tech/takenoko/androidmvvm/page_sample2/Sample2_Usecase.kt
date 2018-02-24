package tech.takenoko.androidmvvm.page_sample2


import rx.Observable
import rx.Subscriber
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.Const
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
        val subscriber = object: Subscriber<List<Sample2_Repository.Entity>>() {
            override fun onNext(t: List<Sample2_Repository.Entity>) {
                ULog.debug("Sample2_Usecase", "subscriber.onNext")
            }
            override fun onCompleted() { onMainThread {
                val latest: List<Sample2_Repository.Entity>? = repository.cacheGetLatest
                val past:   List<Sample2_Repository.Entity>? = repository.cacheGetPast

                //
                latest?.forEach { l ->
                    val text1 = "${l.target} / ${l.base}"
                    val text2 = "${l.rate}"
                    ULog.debug("latest.rates", "${text1}, ${text2}")
                    // calc
                    if(l.rate.isNullOrBlank()) return@forEach
                    val sub = BigDecimal(l.rate) - BigDecimal(past?.find{ p -> l.target == p.target }?.rate?:"0")
                    val rate = (sub / BigDecimal(l.rate)).multiply(BigDecimal(100)) + BigDecimal(1)
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
                viewModel.sampleText.update("Success", BR._all)
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

