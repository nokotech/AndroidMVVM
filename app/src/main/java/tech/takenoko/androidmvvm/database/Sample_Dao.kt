package tech.takenoko.androidmvvm.database

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/24.
 */
@Singleton
class Sample_Dao @Inject constructor(private val orma: OrmaDatabase): BaseDao<Sample_Table, Sample_Table_Relation>(orma) {

    override val log: String = "Sample_Dao"

    /** Instance */
    override fun relation(): Sample_Table_Relation = orma.relationOfSample_Table()

    /** SELECT */
    fun findById(id: Long): Sample_Table? = relation().idEq(id).selector().valueOrNull()

    /** INSERT */
    fun insert(sample: Sample_Table): Long = relation().inserter().execute(sample).also { sample.id = it }

    /** UPDATE */
    fun update(sample: Sample_Table): Int = relation().idEq(sample.id).updater().apply { base(sample.base); date(sample.date) }.execute()

    /** DELETE */
    fun delete(sample: Sample_Table): Int = relation().idEq(sample.id).deleter().execute()

}

