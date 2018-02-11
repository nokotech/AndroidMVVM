package tech.takenoko.androidmvvm.utility

import android.support.annotation.Nullable
import android.util.Log

/**
 * Created by takenoko on 2018/02/11.
 *
 * Custom Logger.
 */
object CLog {

    fun debug(tag : String?, msg : String) {
        Log.d("---------- " + tag, msg)
    }

    fun info(tag : String?, msg : String) {
        Log.i("---------- " + tag, msg)
    }
}