package io.github.fuadreza.storelog.viewmodel.state

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.database.entity.Supply

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

sealed class SupplyState {
    object OnSuccess : SupplyState()

    data class OnSuccessLoad(val supply: LiveData<List<Supply>>) : SupplyState()

    data class OnGetSupply(val supply: LiveData<Supply>) : SupplyState()
}