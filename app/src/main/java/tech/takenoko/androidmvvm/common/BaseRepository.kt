package tech.takenoko.androidmvvm.common

import java.util.*
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
abstract class BaseRepository<K, V> {

    // API
    abstract class RemoteDataStore

    // storage
    abstract class LocalDataStore

    // repository propaty cache.
    private var cache: MutableMap<K, V> = HashMap()

    protected fun getCache(): MutableMap<K, V> = this.cache

    protected fun clearCache() {
        cache.clear()
    }

}

