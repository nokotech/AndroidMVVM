package tech.takenoko.androidmvvm.database

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/24.
 */
@Singleton
class Sample_Dao @Inject constructor(private val orma: OrmaDatabase) {

    /** Instance */
    private fun relation(): Sample_Table_Relation = orma.relationOfSample_Table()
    val isEmpty: Boolean get() = relation().isEmpty

    /** SELECT */
    fun findAll(): List<Sample_Table> = relation().selector().toList()
    fun findById(id: Long): Sample_Table? = relation().idEq(id).selector().valueOrNull()

    /** INSERT */
    fun insert(sample: Sample_Table): Long = relation().inserter().execute(sample).also { sample.id = it }
    fun insert(samples: List<Sample_Table>): Unit = relation().inserter().executeAll(samples)

    /** UPDATE */
    fun update(sample: Sample_Table): Int = relation().idEq(sample.id).updater().apply { base(sample.base); date(sample.date) }.execute()

    /** DELETE */
    fun delete(sample: Sample_Table): Int = relation().idEq(sample.id).deleter().execute()
    fun deleteAll(): Int = relation().deleter().execute()
}
