package tech.takenoko.androidmvvm.domain_layer.usecase

import android.util.Log
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import tech.takenoko.androidmvvm.data_layer.api.ApiBuilder
import tech.takenoko.androidmvvm.data_layer.api.Sample_Api
import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.repository.Sample2_Repository

/**
 * Created by takenoko on 2018/03/11.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Sample2_Usecase::class, Sample_Dao::class, Sample_Api::class, ApiBuilder::class, Log::class)
class Sample2_UsecaseTest {

    val sample2Case= Sample2_Usecase()

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        sample2Case.repository = Sample2_Repository()
    }

    @Test
    fun getSampleText() {
        sample2Case.getSampleText()
    }
}
