package tech.takenoko.androidmvvm.page_sample2

import android.util.Log
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import rx.Single
import rx.Single.create
import rx.SingleSubscriber
import rx.observers.TestSubscriber
import tech.takenoko.androidmvvm.RxSingleSubscriber
import tech.takenoko.androidmvvm.api.Sample_Api
import tech.takenoko.androidmvvm.cache.Sample_Cache
import tech.takenoko.androidmvvm.database.Sample_Dao
import tech.takenoko.androidmvvm.database.Sample_Table
import tech.takenoko.androidmvvm.utility.ApiBuilder

/**
 * Created by takenoko on 2018/03/11.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Sample2_Repository::class, Sample_Dao::class, Sample_Api::class, ApiBuilder::class, Log::class)
class Sample2_RepositoryTest {

    val list: MutableList<Sample_Table> = mutableListOf()
    val sample2Repository = Sample2_Repository()

    // Mock
    @Mock lateinit var sampleDao: Sample_Dao
    @Mock lateinit var sampleApi: Sample_Api

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        PowerMockito.mockStatic(Sample_Api::class.java)
        sample2Repository.sampleCache = Sample_Cache()
        sample2Repository.sampleDao = sampleDao
        sample2Repository.sampleApi = sampleApi
        `when`(sampleDao.findByBaseAndDate("", "")).thenReturn(list)
    }

    @Test
    fun getLatest() {
        `when`(sampleApi.getLatest(ArgumentMatchers.anyString())).thenReturn(null)
        val single = sample2Repository.getLatest("", "")
//        assertEquals(null, single)
    }

    fun createSingle(): Single<Sample_Api.GetLatestEntity>? {
        return create(object : Single.OnSubscribe<Sample_Api.GetLatestEntity> {
            override fun call(t: SingleSubscriber<in Sample_Api.GetLatestEntity>?) {
                val map: MutableMap<String, String> = mutableMapOf()
                map.put("aaa", "bbb")
                val entity = Sample_Api.GetLatestEntity("a", "b", map)
                if (t != null) {
                    t.onSuccess(entity)
                }
            }
        })
    }

    @Test
    fun getPast() {
        `when`(sampleApi.getPast(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(createSingle())
        val single = sample2Repository.getPast("99991230", "aaa", "bbb")
        val testSubscriber = TestSubscriber<Any>()

        val apiSubscriber = RxSingleSubscriber<List<Sample_Cache.Entity>>("Sample2_Repository.getPast")
        apiSubscriber.setSuccessBlock{ t ->
            assertEquals(null, t)
        }
        single.subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
//        testSubscriber.assertValues(single)
    }

}
