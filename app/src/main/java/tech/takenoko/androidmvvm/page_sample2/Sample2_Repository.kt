package tech.takenoko.androidmvvm.page_sample2

import android.databinding.ObservableField
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.common.CustomSubscriber
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, String>() {

    override val log: String = "Sample2_Repository"

    /** DI Api. */
    @Inject lateinit var sampleApi: Sample_Api

    /** CACHE KEY */
    val SAMPLE_TEXT__DATA = "SAMPLE_TEXT__DATA"
    val SAMPLE_TEXT__BASE = "SAMPLE_TEXT__BASE"

    /**
     *
     */
    fun getSampleText(sampleText: ObservableField<String>, readType : Const.ReadType) {

        // get property cache.
        if(readType.contain(Const.ReadType.PROPERTY) && getCache().get(SAMPLE_TEXT__BASE) != null) {
            sampleText.set(getCache().get(SAMPLE_TEXT__BASE))
            return
        }

        // get an entity from api.
        val subscriber = object: CustomSubscriber<Sample_Api.GetLatestEntity>(log) {
            override fun onNext(t: Sample_Api.GetLatestEntity?) {
                super.onNext(t)
                getCache().put(SAMPLE_TEXT__DATA, t?.date?: "")
                getCache().put(SAMPLE_TEXT__BASE, t?.base?: "")
                sampleText.set(t?.date)
            }
        }
        sampleApi.getLatest(subscriber, "USD", "JPY")
    }
}