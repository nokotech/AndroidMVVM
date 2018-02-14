package tech.takenoko.androidmvvm.utility

import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.Const.DATE_FORMAT_DEFAULT
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by takenaka on 2018/02/14.
 */
object Util {

    fun dateToString(date: Date, format: String = Const.DATE_FORMAT_DEFAULT) : String {
        return SimpleDateFormat(format).format(date)
    }

    fun stringToDate(dateStr: String, format: String = Const.DATE_FORMAT_DEFAULT) : Date? {
        val sdFormat = try { SimpleDateFormat(format) } catch (e: Exception) { null }
        return sdFormat?.parse(dateStr)
    }

}