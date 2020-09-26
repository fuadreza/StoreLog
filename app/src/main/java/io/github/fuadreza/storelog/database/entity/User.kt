package io.github.fuadreza.storelog.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.fuadreza.storelog.utils.MyConstants
import java.io.Serializable

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */
@Entity(
    tableName = MyConstants.DB.USER,
    indices = [Index(value = [User.USERNAME], unique = true)]
)

data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val userId: Int,

    @ColumnInfo(name = USERNAME)
    val userName : String? = "",

    @ColumnInfo(name = PASSWORD)
    val passWord: String? = ""

) : Serializable {
    companion object {
        const val ID = "id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }
}