package tech.takenoko.androidmvvm.presentation_layer.presenter

import rx.Single
import tech.takenoko.androidmvvm.Const
import tech.takenoko.androidmvvm.SingleSubscriberBlock
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

    /**
     * Single creater.
     */
    protected fun <T> rxSingle(f: SingleSubscriberBlock<T>): Single<T> {
        return Single.create { sub -> f(sub) }
    }

    /**
     *  check read type.
     *  @param type
     */
    fun Const.ReadType.contain(type: Const.ReadType): Boolean {
        return type.v >= this.v
    }

    /**
     * strict check read type.
     * @param types
     */
    fun Const.ReadType.check(types: List<Const.ReadType>): Boolean {
        return types.contains(this)
    }

}

