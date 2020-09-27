package io.github.fuadreza.storelog.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.github.fuadreza.storelog.database.entity.Supply
import io.github.fuadreza.storelog.repository.SupplyRepository
import io.github.fuadreza.storelog.viewmodel.state.SupplyState
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */


class SupplyViewModel @ViewModelInject constructor(private val supplyRepository: SupplyRepository) :
    ViewModel(), LifecycleObserver {

    private val _supplyStete = MutableLiveData<SupplyState>()
    private var _supply: LiveData<Supply> = MutableLiveData()

    var allSupplies: LiveData<List<Supply>> = MutableLiveData()

    val supply: LiveData<Supply> get() = _supply

    val supplyState: LiveData<SupplyState> get() = _supplyStete

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchSupplyData() {
        viewModelScope.launch {
            allSupplies = supplyRepository.fetchSupply()
        }
    }

    suspend fun addSupply(supply: Supply) {
        supplyRepository.insert(supply)
    }

    suspend fun getSupplyById(id: Int) {
        _supply = supplyRepository.getSupplyById(id)
    }

    suspend fun deleteById(id: Int) {
        supplyRepository.delete(id)
        _supplyStete.postValue(SupplyState.OnSuccess)
    }

    suspend fun updateSupply(supply: Supply) {
        supplyRepository.updateSupply(supply)
        _supplyStete.postValue(SupplyState.OnSuccess)
    }
}