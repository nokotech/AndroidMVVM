package tech.takenoko.androidmvvm.utility

import android.app.Application
import com.google.android.gms.analytics.HitBuilders
import tech.takenoko.androidmvvm.App
import tech.takenoko.androidmvvm.page_sample1.Sample1_Repository.log

/**
 * Created by takenoko on 2018/02/24.
 */
object GALog {

    fun sendScreen(application: Application, screenName: String) {
        ULog.info("GALog", "sendScreen called. ${screenName}")
        val app = application as App
        app.getDefaultTracker().setScreenName(log)
        app.getDefaultTracker().send(HitBuilders.ScreenViewBuilder().build())
    }

    fun sendEvent(application: Application, category: String, action: String) {
        ULog.info("GALog", "sendEvent called. ${category}, ${action}")
        val app = application as App
        app.getDefaultTracker().send(HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .build())
    }

}