package tech.takenoko.androidmvvm.page_sample2

import rx.Observable
import rx.schedulers.Schedulers
import tech.takenoko.androidmvvm.Const.baseUrl
import tech.takenoko.androidmvvm.common.BaseRepository
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_ApiProtocol
import tech.takenoko.androidmvvm.page_sample2.entity.Sample2_Entity
import tech.takenoko.androidmvvm.utility.ApiBuilder
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class Sample2_Repository @Inject constructor() : BaseRepository<String, Sample2_Entity>() {

    fun getSampleText(): Observable<Sample2_Entity.Response>? {
         return ApiBuilder
                .build(baseUrl)
                .create(Sample2_ApiProtocol::class.java)
                .getLatest("USD", "JPY")
                .subscribeOn(Schedulers.newThread())
    }
}