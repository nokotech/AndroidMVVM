package tech.takenoko.androidmvvm.page_sample2

import android.databinding.ObservableField
import tech.takenoko.androidmvvm.common.BaseUsecase
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    override val log: String = "Sample2_Usecase"

    @Inject lateinit var repository: Sample2_Repository

    fun getSampleText(sampleText: ObservableField<String>) {
        ULog.debug("Sample2_Usecase", "getSampleText.")
        sampleText.set("loding....")
        repository.getSampleText(sampleText)
    }
}