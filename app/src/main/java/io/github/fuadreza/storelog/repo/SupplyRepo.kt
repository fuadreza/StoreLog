package io.github.fuadreza.storelog.repo

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.localdb.supply.SupplyDao
import io.github.fuadreza.storelog.model.Supply

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class SupplyRepo(private val supplyDao: SupplyDao) {

    val allSupplies: LiveData<List<Supply>> = supplyDao.getSupply()

    suspend fun insert(supply: Supply){
        supplyDao.insert(supply)
    }

    suspend fun delete(id: Int){
        supplyDao.delete(id)
    }

    suspend fun getSupplyById(id: Int): LiveData<Supply>{
        return supplyDao.getSupplyById(id)
    }

}