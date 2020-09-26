package io.github.fuadreza.storelog.repository

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.database.dao.SupplyDao
import io.github.fuadreza.storelog.database.entity.Supply
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class SupplyRepository @Inject constructor(private val supplyDao: SupplyDao) {

    suspend fun fetchSupply() = supplyDao.fetch()

    suspend fun insert(supply: Supply){
        supplyDao.insert(supply)
    }

    suspend fun delete(id: Int){
        supplyDao.delete(id)
    }

    suspend fun getSupplyById(id: Int): LiveData<Supply>{
        return supplyDao.getSupplyById(id)
    }

    suspend fun updateSupply(supply: Supply) {
        supplyDao.update(supply)
    }

}