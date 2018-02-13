package tech.takenoko.androidmvvm.common

import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.utility.ULog
import java.util.*
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
abstract class BaseRepository<K, V> {

    abstract val log: String

    /** repository propaty cache. */
    private var cache: MutableMap<K, V> = HashMap()

    /**
     *  check read type.
     *  @param type
     */
    protected fun Const.ReadType.contain(type: Const.ReadType): Boolean {
        return type.v >= this.v
    }

    /**
     * strict check read type.
     * @param types
     */
    protected fun Const.ReadType.check(types: List<Const.ReadType>): Boolean {
        return types.contains(this)
    }

    /**
     * get propaty cache.
     */
    protected fun getCache(): MutableMap<K, V> {
        ULog.debug(log, "getCache called. cache = " + cache.toString())
        return this.cache
    }

    /**
     * clear propaty cache.
     */
    protected fun clearCache() {
        ULog.debug(log, "clearCache called.")
        cache.clear()
    }
}

