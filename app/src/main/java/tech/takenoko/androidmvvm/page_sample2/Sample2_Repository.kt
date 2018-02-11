package tech.takenoko.androidmvvm.page_sample2

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_ApiProtocol
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_Entity
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, Sample2_Entity>() {

//    @Inject lateinit var retrofit: Retrofit
//    @Inject lateinit var apiProtocol: Sample2_ApiProtocol

    fun getSampleText(): String {
        val getLatest: Call<Sample2_Entity> = protocol().getLatest("USD", "JPY")
        try {
            val res = getLatest.execute()
            val response: Sample2_Entity = res.body()!!
            return response.date

        } catch (e: Exception) {
            ULog.warn("Sample2_Repository", e)
            return e.localizedMessage?: ""
        }

    }

    fun protocol(): Sample2_ApiProtocol {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(it.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build())
                }.build()
        val builder = Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .client(httpClient)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return builder.create(Sample2_ApiProtocol::class.java)
    }
}