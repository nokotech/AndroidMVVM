package tech.takenoko.androidmvvm.common

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/12.
 */
@Singleton
class PreferenceManager<T : Context> @Inject constructor(var activity: T) {

    // repository propaty cache.
    private var sharedPreference: SharedPreferences

    init {
        sharedPreference = activity.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
}