package tech.takenoko.androidmvvm.utility

import android.util.Log

/**
 * Created by takenoko on 2018/02/11.
 *
 * Custom Logger.
 */
object ULog {

    fun debug(tag: String?, msg: String) {
        Log.d(String.format("--- [ %-24s ]", tag), msg)
    }

    fun info(tag: String?, msg: String) {
        Log.i(String.format("--- [ %-24s ]", tag), msg)
    }

    fun error(tag: String?, msg: String) {
        Log.e(String.format("--- [ %-24s ]", tag), msg)
    }

    fun warn(tag: String?, t: Throwable) {
        Log.w(String.format("--- [ %-24s ]", tag), t)
    }
}