package tech.takenoko.androidmvvm.domain_layer.usecase

import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.database.SharedPref
import tech.takenoko.androidmvvm.data_layer.repository.Sample1_Repository
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample1_Usecase @Inject constructor(): BaseUsecase() {

    override val log: String = "Sample1_Usecase"

    @Inject lateinit var preference: SharedPref
    @Inject lateinit var sampleDao: Sample_Dao

    fun changeTitle1(): String {
        return Sample1_Repository.getSampleText1()
    }

    fun changeTitle2(): String {
        return Sample1_Repository.getSampleText2()
    }

    fun clear() {
        preference.allClear()
        sampleDao.deleteAll()
    }
}