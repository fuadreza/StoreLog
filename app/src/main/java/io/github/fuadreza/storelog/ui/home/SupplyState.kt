package io.github.fuadreza.storelog.ui.home

import androidx.lifecycle.LiveData
import io.github.fuadreza.storelog.model.Supply

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

sealed class SupplyState  {
    object OnSuccess: SupplyState()

    data class OnSuccessLoad(val supply: LiveData<List<Supply>>): SupplyState()

    data class OnGetSupply(val supply: LiveData<Supply>): SupplyState()
}