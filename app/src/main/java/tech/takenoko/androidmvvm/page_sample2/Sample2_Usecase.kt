package tech.takenoko.androidmvvm.page_sample2

import android.databinding.ObservableField
import tech.takenoko.androidmvvm.common.BaseUsecase
import tech.takenoko.androidmvvm.common.custom.CustomSubscriber
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_Entity
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    override val log: String = "Sample2_Usecase"

    @Inject lateinit var repository: Sample2_Repository

    fun getSampleText(sampleText: ObservableField<String>) {
        val callback = object: CustomSubscriber<Sample2_Entity.Response>(log) {
            override fun onNext(t: Sample2_Entity.Response?) {
                super.onNext(t)
                sampleText.set(t?.date)
            }
        }
        repository.getSampleText()?.subscribe(callback)
        ULog.debug("Sample2_Usecase", "getSampleText.")
        sampleText.set("----------")
    }
}