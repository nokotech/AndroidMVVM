package tech.takenoko.androidmvvm

import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by takenoko on 2018/02/09.
 */
class App : DaggerApplication() {

    var mTracker: Tracker? = null

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    @Synchronized
    fun getDefaultTracker(): Tracker {
        if (mTracker == null) {
            mTracker = GoogleAnalytics.getInstance(this).newTracker(R.xml.global_tracker)
        }
        return mTracker!!
    }

}
