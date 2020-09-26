package io.github.fuadreza.storelog.localdb.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.fuadreza.storelog.model.User
import io.github.fuadreza.storelog.utils.MyConstants

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM ${MyConstants.DB.USER} WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

}