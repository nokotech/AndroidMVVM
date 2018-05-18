package tech.takenoko.androidmvvm.data_layer.cache

import android.graphics.Bitmap
import android.util.LruCache
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/03/01.
 */
@Singleton
class BitmapCache {

    private var memoryCache: LruCache<String, Bitmap>

    init {
        val cacheSize: Long = Runtime.getRuntime().maxMemory() / 8  // 最大メモリの1/8
        // int cacheSize = 5 * 1024 * 1024;                         // 5MB

        memoryCache = object : LruCache<String, Bitmap>(cacheSize.toInt()) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value?.byteCount ?: 0
            }
        }
    }

    @Synchronized
    fun getBitmap(url: String): Bitmap {
        return memoryCache.get(url)
    }

    @Synchronized
    fun putBitmap(url: String, bitmap: Bitmap) {
        val old: Bitmap? = memoryCache.put(url, bitmap)
        old?.recycle()
    }
}