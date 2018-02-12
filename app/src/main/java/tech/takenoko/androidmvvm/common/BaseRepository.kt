package tech.takenoko.androidmvvm.common

import tech.takenoko.androidmvvm.utility.ULog
import java.util.*
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
abstract class BaseRepository<K, V> {

    abstract val log: String

    // repository propaty cache.
    private var cache: MutableMap<K, V> = HashMap()

    protected fun getCache(): MutableMap<K, V> {
        ULog.debug(log, "getCache called. cache = " + cache.toString())
        return this.cache
    }

    protected fun clearCache() {
        ULog.debug(log, "clearCache called.")
        cache.clear()
    }
}

