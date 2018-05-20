package tech.takenoko.androidmvvm.domain_layer.translater

import tech.takenoko.androidmvvm.data_layer.cache.Sample_Cache
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample2_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_CustomAdapter
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenaka on 2018/05/18.
 */
@Singleton
class Sample2_Translater @Inject constructor() {

    /**
     *
     */
    fun translateDataList(res: Sample2_Usecase.GetSampleTextResponse): List<Sample2_CustomAdapter.SampleList> {

        // return data
        val dataList: MutableList<Sample2_CustomAdapter.SampleList> = mutableListOf()

        // calc data
        val latest: List<Sample_Cache.Entity>? = res.latest
        val past:   List<Sample_Cache.Entity>? = res.past

        //
        latest?.forEach { l ->
            val text1 = "${l.target} / ${l.base}"
            val text2 = "${l.rate}"
            // ULog.debug("latest.rates", "${text1}, ${text2}")
            // calc
            if(l.rate.isNullOrBlank()) return@forEach
            val sub = BigDecimal(l.rate) - BigDecimal(past?.find{ p -> l.target == p.target }?.rate?:"0")
            val rate = sub.divide(BigDecimal(l.rate),2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal(100))
            // insert
            val index: Int = dataList.indexOfFirst { it.text1 == text1 }
            when {
                (index > -1) -> {
                    val row = dataList[index]
                    row.text1 = text1
                    row.text2 = text2
                    row.text3 = rate.toInt()
                    dataList[index] = row
                } else -> {
                dataList.add(Sample2_CustomAdapter.SampleList(text1, text2, rate.toInt()))
            }
            }
        }
        return dataList
    }
}