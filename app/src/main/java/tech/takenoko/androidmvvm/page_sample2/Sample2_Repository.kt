package tech.takenoko.androidmvvm.page_sample2

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_ApiProtocol
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_Entity
import tech.takenoko.androidmvvm.utility.RxField
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, Sample2_Entity>() {

    lateinit var getLatest: Observable<Sample2_Entity.Response>

//    fun getSampleText(): Observable<Sample2_Entity.Response>? {
//        getLatest = protocol().getLatest("USD", "JPY")
//        return getLatest.subscribeOn(Schedulers.newThread())
//                // .observeOn(AndroidSchedulers.mainThread())
//    }


    fun getSampleText(): Observable<Sample2_Entity.Response> {
        getLatest = protocol().getLatest("USD", "JPY")
        getLatest.subscribeOn(Schedulers.newThread())
                // .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Subscriber<Sample2_Entity.Response>(){
                override fun onCompleted() {
                    ULog.info("CustomInterceptor", "getSampleText.onCompleted build.")
                }
                override fun onNext(r: Sample2_Entity.Response?) {
                    ULog.info("CustomInterceptor", "getSampleText.onNext build.")

                    val a = RxField<Sample2_Entity.Response>(getLatest)
                    ULog.debug("CustomInterceptor", "getSampleText.onNext response =  " + a.get())
                }
                override fun onError(e: Throwable?) {
                    ULog.info("CustomInterceptor", "getSampleText.onError build.")
                }
            })
        return getLatest
    }

    class CustomInterceptor(): Interceptor {
        @Throws override fun intercept(chain: Interceptor.Chain?): Response {
            val original = chain!!.request()
            val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
            val response = chain.proceed(request)
            ULog.info("CustomInterceptor", "intercept build.")
            return response
        }
    }

    fun httpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val client: Interceptor = CustomInterceptor()
        val logger: Interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(client)
        httpClient.addInterceptor(logger)
        return httpClient.build()
    }

    fun protocol(): Sample2_ApiProtocol {
        val builder = Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return builder.create(Sample2_ApiProtocol::class.java)
    }
}