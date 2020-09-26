package io.github.fuadreza.storelog.localdb.supply

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.fuadreza.storelog.model.Supply
import io.github.fuadreza.storelog.utils.MyConstants

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@Dao
interface SupplyDao  {

    @Query("SELECT * FROM ${MyConstants.DB.SUPPLY}")
    fun getSupply() : LiveData<List<Supply>>

    @Query("SELECT * FROM ${MyConstants.DB.SUPPLY} WHERE id = :id")
    fun getSupplyById(id: Int): LiveData<Supply>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(supply: Supply)

    @Update
    suspend fun update(supply: Supply)

    @Query("DELETE FROM ${MyConstants.DB.SUPPLY} WHERE id = :id")
    suspend fun delete(id: Int)

}