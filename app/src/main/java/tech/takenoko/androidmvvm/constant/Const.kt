package tech.takenoko.androidmvvm.constant

import tech.takenoko.androidmvvm.BuildConfig
import tech.takenoko.androidmvvm.constant.env.DebugConst
import tech.takenoko.androidmvvm.constant.env.ReleaseConst
import tech.takenoko.androidmvvm.constant.env.StagingConst

/**
 * Created by takenoko on 2018/02/12.
 */

object Const {

    /** */
    val env: EnvConst = when (BuildConfig.BUILD_TYPE) {
        "staging" -> StagingConst()
        "release" -> ReleaseConst()
        else -> DebugConst()
    }

    /** Priority of reading values. */
    enum class ReadType(val v: Int) {
        PROPERTY            (0b00000001),
        SHARED_PREFERENCES  (0b00000010),
        LOCAL_DB            (0b00000100),
        API                 (0b00001000)
    }

    /** date */
    val DATE_FORMAT_DEFAULT = "yyyy/MM/dd HH:mm:ss"

    /** Timeout */
    val CACHE_TIMEOUT: Long = 100000

    /** SharedPreferences key. */
    val SHARED_PREF_USER_INFO = "USER_INFO"
}
