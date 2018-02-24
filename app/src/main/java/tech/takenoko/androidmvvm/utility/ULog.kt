package tech.takenoko.androidmvvm.utility

import android.util.Log

/**
 * Created by takenoko on 2018/02/11.
 *
 * Custom Logger.
 */
object ULog {

    fun Boolean.toInt() = if (this) 1 else 0

    private fun isCurrent(): String {
        return ""//[${Thread.currentThread().equals(getMainLooper().getThread()).toInt()}]"
    }

    fun debug(tag: String?, msg: String) {
        Log.d(String.format("--- [ %-24s ]%s", tag, isCurrent()), msg)
    }

    fun info(tag: String?, msg: String) {
        Log.i(String.format("--- [ %-24s ]%s", tag, isCurrent()), msg)
    }

    fun error(tag: String?, msg: String) {
        Log.e(String.format("--- [ %-24s ]%s", tag, isCurrent()), msg)
    }

    fun warn(tag: String?, t: Throwable) {
        Log.w(String.format("--- [ %-24s ]%s", tag, isCurrent()), t)
    }
}