package tech.takenoko.androidmvvm.utility

import android.content.Context

/**
 * Created by takenoko on 2018/02/11.
 */
class AppExceptionHandler(val context: Context, val targrtName: String) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread?, p1: Throwable?) {
        ULog.error(targrtName, "called uncaughtException.")
    }

}