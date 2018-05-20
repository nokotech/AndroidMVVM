package tech.takenoko.androidmvvm.common

import android.app.Activity
import android.content.Intent
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/12.
 */
class CommonNavigator<T : Activity> @Inject constructor(var activity: T) {

    inline fun <reified X> next() {
        val intent = Intent(activity, X::class.java)
        activity?.startActivity(intent)
    }

    fun kill() {
        activity.finish()
    }
}