package io.github.fuadreza.storelog.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.fuadreza.storelog.utils.MyConstants
import java.io.Serializable
import java.util.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */
@Entity(
    tableName = MyConstants.DB.SUPPLY
)

data class Supply (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val supplyId: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = ITEMS)
    val items: Int,

    @ColumnInfo(name = SUPPLIER)
    val supplier: String,

    @ColumnInfo(name = DATE)
    val date: String

): Serializable {
    companion object {
        const val ID = "id"
        const val NAME = "supply_name"
        const val ITEMS = "supply_item"
        const val SUPPLIER = "supplier"
        const val DATE = "date"
    }
}