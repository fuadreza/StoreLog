package io.github.fuadreza.storelog.ui.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.model.Supply
import io.github.fuadreza.storelog.ui.home.SupplyState
import io.github.fuadreza.storelog.ui.home.SupplyViewModel
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class EditActivity : AppCompatActivity() {
    private lateinit var supplyViewModel: SupplyViewModel

    var name: String = ""
    var count: Int = 0
    var supplierName: String = ""

    private val EXTRA_SUPPLY = "EXTRA_SUPPLY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val supply = intent.getSerializableExtra(EXTRA_SUPPLY) as Supply

        setupViews(supply)

        supplyViewModel = ViewModelProvider(this).get(SupplyViewModel::class.java).apply {
            /*CoroutineScope(Dispatchers.IO).launch {
                getSupplyById(id)
            }*/
        }

        supplyViewModel.supplyState.observe(this, Observer<SupplyState> { state ->
            handleUI(state)
        })

        btn_add.setOnClickListener {
            val updatedSupply = Supply(
                supply.supplyId,
                sup_name.text.toString(),
                sup_count.text.toString().toInt(),
                supplier.text.toString(),
                "2020"
            )
            CoroutineScope(Dispatchers.IO).launch {
                supplyViewModel.updateSupply(updatedSupply)
            }
        }

        btn_delete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                supplyViewModel.deleteById(supply.supplyId)
            }
        }

    }

    private fun setupViews(supply: Supply) {
        sup_name.setText(supply.name)
        sup_count.setText(supply.items.toString())
        supplier.setText(supply.supplier)
    }

    private fun dataState(supply: Supply?) {

    }

    private fun handleUI(state: SupplyState?) {
        when(state) {
            is SupplyState.OnSuccess -> finish()
        }
    }
}