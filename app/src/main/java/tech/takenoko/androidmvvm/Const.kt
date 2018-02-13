package tech.takenoko.androidmvvm

/**
 * Created by takenoko on 2018/02/12.
 */
object Const {

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

}