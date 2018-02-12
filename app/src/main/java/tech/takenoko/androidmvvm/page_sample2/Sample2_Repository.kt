package tech.takenoko.androidmvvm.page_sample2

import android.databinding.ObservableField
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.Const.baseUrl
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.common.CustomSubscriber
import tech.takenoko.androidmvvm.utility.ApiBuilder
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, String>() {

    override val log: String = "Sample2_Repository"

    // CACHE KEY
    val SAMPLE_TEXT__DATA = "SAMPLE_TEXT__DATA"
    val SAMPLE_TEXT__BASE = "SAMPLE_TEXT__BASE"

    fun getSampleText(sampleText: ObservableField<String>) {
        if(getCache().get(SAMPLE_TEXT__BASE) != null) {
            sampleText.set(getCache().get(SAMPLE_TEXT__BASE))
            return
        }
        val callback = object: CustomSubscriber<Sample2_Entity.Response>(log) {
            override fun onNext(t: Sample2_Entity.Response?) {
                super.onNext(t)
                getCache().put(SAMPLE_TEXT__DATA, t?.date?: "")
                getCache().put(SAMPLE_TEXT__BASE, t?.base?: "")
                sampleText.set(t?.date)
            }
        }
        ApiBuilder
                .build(baseUrl)
                .create(Sample2_ApiProtocol::class.java)
                .getLatest("USD", "JPY")
                .subscribeOn(Schedulers.newThread())
                .subscribe(callback)
    }
}