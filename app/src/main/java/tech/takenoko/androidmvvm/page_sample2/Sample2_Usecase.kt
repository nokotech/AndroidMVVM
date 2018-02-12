package tech.takenoko.androidmvvm.page_sample2

import android.databinding.ObservableField
import tech.takenoko.androidmvvm.common.BaseUsecase
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_Entity
import tech.takenoko.androidmvvm.utility.RxField
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    @Inject lateinit var repository: Sample2_Repository
    
    fun getSampleText(): ObservableField<String> {
        val res = repository.getSampleText()
        val formattedLaps: ObservableField<Sample2_Entity.Response> = RxField<Sample2_Entity.Response>(res)
        ULog.debug("Sample2_Usecase", "getSampleText." + formattedLaps.get())
        return ObservableField((formattedLaps.get()?.date) ?: "----------")
    }
}