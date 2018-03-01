package tech.takenoko.androidmvvm

/**
 * Created by takenoko on 2018/02/12.
 */
object Const {

    /** date */
    val DATE_FORMAT_DEFAULT = "yyyy/MM/dd HH:mm:ss"

    /** Timeout */
    val CACHE_TIMEOUT: Long = 10000

    /** API domain name. */
    object BaseUrl {
        val SAMPLE_API: String = "http://api.fixer.io"
    }

    /** Priority of reading values. */
    enum class ReadType(val v: Int) {
        PROPERTY            (0b00000001),
        SHARED_PREFERENCES  (0b00000010),
        LOCAL_DB            (0b00000100),
        API                 (0b00001000)
    }


    /** SharedPreferences key. */
    val SHARED_PREF_USER_INFO = "USER_INFO"

}