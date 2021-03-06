package tech.takenoko.androidmvvm.utility

import tech.takenoko.androidmvvm.Const
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by takenoko on 2018/02/14.
 */
object Util {

    var recordingTime: MutableMap<String, Date> = HashMap()

    fun dateToString(date: Date, format: String = Const.DATE_FORMAT_DEFAULT) : String {
        return SimpleDateFormat(format).format(date)
    }

    fun stringToDate(dateStr: String, format: String = Const.DATE_FORMAT_DEFAULT) : Date? {
        val sdFormat = try { SimpleDateFormat(format) } catch (e: Exception) { null }
        return sdFormat?.parse(dateStr)
    }

    fun startTime(key: String) {
        val date = Date()
        // ULog.debug("Util.startTime", "${key} ${date}")
        recordingTime[key] = date
    }

    fun endTime(key: String) {
        val date = Date()
        val sub = date.time - (recordingTime[key]?.time?: 0)
        // ULog.debug("Util.endTime", "$key $date")
        ULog.info("Util.endTime", "$key recordingTime = $sub")
    }
}