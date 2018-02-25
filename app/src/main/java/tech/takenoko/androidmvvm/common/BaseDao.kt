package tech.takenoko.androidmvvm.common

import com.github.gfx.android.orma.Relation
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/25.
 */
@Singleton
abstract class BaseDao<T, U : Relation<T, *>?> {

    abstract val log: String

    protected abstract fun relation(): Relation<T, U>
    val isEmpty: Boolean get() = relation().isEmpty

    /** INSERT */
    fun insertAll(list: List<T>): Unit = relation().inserter().executeAll(list)

    /** SELECT */
    fun findAll(): List<T> = relation().selector().toList()

    /** DELETE */
    fun deleteAll(): Int = relation().deleter().execute()
}