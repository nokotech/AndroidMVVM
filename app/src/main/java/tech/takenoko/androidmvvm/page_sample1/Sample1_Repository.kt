package tech.takenoko.androidmvvm.page_sample1

import tech.takenoko.androidmvvm.common.BaseRepository

/**
 * Created by takenoko on 2018/02/11.
 */
object Sample1_Repository : BaseRepository<String, String>() {

    fun getSanpleText1(): String {
        val sanpleTextCount = ((getCache().get("sanpleText")?.toLongOrNull() ?: 0)  + 1L)
        getCache().put("sanpleText", sanpleTextCount.toString())
        return "SanpleText" + sanpleTextCount
    }

    fun getSanpleText2(): String {
        val sanpleTextCount = ((getCache().get("sanpleText")?.toLongOrNull() ?: 0)  - 1L)
        getCache().put("sanpleText", sanpleTextCount.toString())
        return "SanpleText" + sanpleTextCount
    }
}