package tech.takenoko.androidmvvm.data_layer.repository

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

/**
 * Created by takenoko on 2018/03/11.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Sample1_Repository::class, Sample_Dao::class, Sample_Api::class, ApiBuilder::class, Log::class)
class Sample1_RepositoryTest {

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        PowerMockito.mockStatic(Sample_Api::class.java)
    }

    @Test
    fun getSampleText1() {
        Sample1_Repository.getSampleText1()
    }

    @Test
    fun getPast() {
        Sample1_Repository.getSampleText2()
    }

}
