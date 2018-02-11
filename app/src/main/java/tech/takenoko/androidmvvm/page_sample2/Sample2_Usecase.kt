package tech.takenoko.androidmvvm.page_sample2

import tech.takenoko.androidmvvm.common.BaseUsecase
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_Usecase @Inject constructor(): BaseUsecase() {

    @Inject lateinit var repository: Sample2_Repository

    fun getSampleText(): String {
        return repository.getSampleText();
    }
}