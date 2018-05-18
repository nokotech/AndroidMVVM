package tech.takenoko.androidmvvm.data_layer.database

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table
import tech.takenoko.androidmvvm.application.AppException
import java.io.Serializable
import java.util.*

/**
 * Created by takenoko on 2018/02/24.
 */
@Table
class Sample_Table: Serializable {

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column(indexed = true)
    var date: String? = null

    @Column(indexed = true)
    var base: String? = null

    @Column(indexed = true)
    var target: String? = null

    @Column(indexed = true)
    var rate: String? = null

    @Column
    var insertTime: Date? = null

    @Column
    var updateTime: Date? = null

    fun creat(_date: String?, _base: String?, _target: String?, _rate: String?): Sample_Table {
        if(_date.isNullOrBlank() || _base.isNullOrBlank() || _target.isNullOrBlank() || _rate.isNullOrBlank()) {
            throw AppException("Not Pram.", Throwable())
        }
        this.date       = _date
        this.base       = _base
        this.target     = _target
        this.rate       = _rate
        this.insertTime = Date()
        this.updateTime = Date()
        return this
    }
}