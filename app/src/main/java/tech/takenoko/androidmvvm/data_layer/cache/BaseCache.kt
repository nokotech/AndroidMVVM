package tech.takenoko.androidmvvm.data_layer.cache

import tech.takenoko.androidmvvm.utility.ULog
import java.util.*

/**
 * Created by takenoko on 2018/03/01.
 */
abstract class BaseCache {

    /**
     * @param date
     * @return Difference from current time
     */
    fun checkTimeout(date: Date?, sessionTime: Long): Boolean {
        if(date == null) return false
        val subtime = Date().time - date.time
        ULog.debug("BaseRepository.checkTimeout", "subtime = $subtime")
        return subtime <= sessionTime
    }
}