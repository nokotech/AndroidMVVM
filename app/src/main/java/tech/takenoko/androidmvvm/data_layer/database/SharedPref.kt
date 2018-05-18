package tech.takenoko.androidmvvm.data_layer.database

import android.content.Context
import com.google.gson.Gson
import tech.takenoko.androidmvvm.Const.SHARED_PREF_USER_INFO
import javax.inject.Inject
import javax.inject.Singleton




/**
 * Created by takenoko on 2018/02/12.
 */
@Singleton
class SharedPref @Inject constructor(context: Context) {

    /** repository SharedPreferences cache. */
    private var sharedPreferences = context.getSharedPreferences("appPreferences", Context.MODE_PRIVATE)

    /**
     * get String.
     * @param key
     * @return value
     */
    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    /**
     * put String.
     * @param key
     * @param value
     * @return is success.
     */
    fun putString(key: String, value: String?): Boolean {
        sharedPreferences.edit().putString(key, value).apply()
        return (getString(key) == value)
    }

    /**
     * get user info.
     * @return
     */
    fun getUser(): SharedPrefDataset.User? {
        val str = sharedPreferences.getString(SHARED_PREF_USER_INFO, "")
        return Gson().fromJson<SharedPrefDataset.User>(str, SharedPrefDataset.User::class.java)
    }

    /**
     * put user info.
     * @param user
     * @return is success.
     */
    fun setUser(user: SharedPrefDataset.User): Boolean {
        val str = Gson().toJson(user)
        sharedPreferences.edit().putString(SHARED_PREF_USER_INFO, str).apply()
        return (getUser() == user)
    }

    /**
     * all clear.
     * @return is success.
     */
    fun allClear(): Boolean {
        val keys = sharedPreferences.getAll()
        if (keys.isNotEmpty()) {
            keys.forEach { (key) ->  sharedPreferences.edit().remove(key).apply() }
        }
        return sharedPreferences.getAll().isEmpty()
    }
}