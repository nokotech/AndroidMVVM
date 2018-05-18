package tech.takenoko.androidmvvm.data_layer.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.takenoko.androidmvvm.utility.ULog

/**
 * Created by takenoko on 2018/02/12.
 */
object ApiBuilder {

    fun build(baseUrl: String): Retrofit {
        ULog.info("ApiBuilder", "build called.")
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private val customInterceptor = object: Interceptor {
        @Throws override fun intercept(chain: Interceptor.Chain?): Response {
            ULog.info("Interceptor", "intercept called.")
            val original = chain!!.request()
            val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
            return chain.proceed(request)
        }
    }

    private fun httpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val client: Interceptor = customInterceptor
        val logger: Interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(client)
        httpClient.addInterceptor(logger)
        return httpClient.build()
    }
}