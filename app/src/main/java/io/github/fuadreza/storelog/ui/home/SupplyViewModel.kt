package io.github.fuadreza.storelog.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.fuadreza.storelog.localdb.LocalDatabase
import io.github.fuadreza.storelog.model.Supply
import io.github.fuadreza.storelog.repo.SupplyRepo

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */


class SupplyViewModel constructor(application: Application) : AndroidViewModel(application){

    private val _supplyStete = MutableLiveData<SupplyState>()

    private var repository: SupplyRepo

    var allSupplies: LiveData<List<Supply>> = MutableLiveData()

    val supplyState: LiveData<SupplyState> get() = _supplyStete

    init {
        val supplyDao = LocalDatabase.getDatabase(application, viewModelScope).supplyDao()
        repository = SupplyRepo(supplyDao)
        allSupplies = repository.allSupplies
    }

    suspend fun addSupply(supply: Supply){
        repository.insert(supply)
    }

}