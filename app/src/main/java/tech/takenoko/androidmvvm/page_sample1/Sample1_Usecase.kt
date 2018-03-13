package tech.takenoko.androidmvvm.page_sample1

import tech.takenoko.androidmvvm.common.BaseUsecase
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample1_Usecase @Inject constructor(): BaseUsecase () {

    override val log: String = "Sample1_Usecase"

    fun changeTitle1(): String {
        return Sample1_Repository.getSampleText1()
    }

    fun changeTitle2(): String {
        return Sample1_Repository.getSampleText2()
    }
}