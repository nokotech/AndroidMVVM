package tech.takenoko.androidmvvm.utility

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/12.
 */
@Singleton
class CacheManager(context: Context) {

    // repository propaty cache.
    private var sharedPreference: SharedPreferences

    init {
        sharedPreference = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
}