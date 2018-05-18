package tech.takenoko.androidmvvm.domain_layer.usecase


import rx.Observable
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.data_layer.cache.Sample_Cache
import tech.takenoko.androidmvvm.data_layer.repository.Sample2_Repository
import tech.takenoko.androidmvvm.domain_layer.translater.Sample2_Translater
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_CustomAdapter
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    override val log: String = "Sample2_Usecase"

    /** DI Repository. */
    @Inject lateinit var repository: Sample2_Repository

    /** DI Trancelater */
    @Inject lateinit var trancelater: Sample2_Translater

    class GetSampleTextResponse(var latest: List<Sample_Cache.Entity>, var past: List<Sample_Cache.Entity>)

    /**
     * get repository data.
     * - ViewModel.bindingProperty(ObservableField) --> (Observable)
     * - notifyPropertyChanged(BR.xxx);
     */
    fun getSampleText(): Observable<List<Sample2_CustomAdapter.SampleList>> {
        ULog.debug("Sample2_Usecase", "getSampleText.")
        // get repository.
        return Observable.zip(
                repository.getLatest("USD", "JPY").toObservable(),
                repository.getPast("2017-02-18", "USD", "JPY").toObservable()
        )
        {
            t1: List<Sample_Cache.Entity>, t2: List<Sample_Cache.Entity> -> GetSampleTextResponse(t1, t2)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { t -> trancelater.translateDataList(t) }
    }
}
