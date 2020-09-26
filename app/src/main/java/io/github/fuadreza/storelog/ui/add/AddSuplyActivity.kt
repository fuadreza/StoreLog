package io.github.fuadreza.storelog.ui.add

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.model.Supply
import io.github.fuadreza.storelog.ui.home.SupplyViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class AddSuplyActivity : AppCompatActivity() {

    lateinit var supplyName: TextView
    lateinit var supplyCount: TextView
    lateinit var supplySupplier: TextView

    private lateinit var supplyViewModel: SupplyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supplyViewModel = ViewModelProvider(this).get(SupplyViewModel::class.java).apply {

        }
        setupViews()

        btn_add.setOnClickListener {
            addNewSupply()
        }

    }

    private fun addNewSupply() {
        var name = supplyName.text.toString()
        var count: Int = supplyCount.text.toString().toInt()
        var supplier = supplySupplier.text.toString()

        var supply = Supply(0, name = name, items = count, supplier = supplier, date = "2020")

        CoroutineScope(IO).launch {
            supplyViewModel.addSupply(supply)
            finish()
        }

    }

    private fun setupViews() {
        supplyName = sup_name
        supplyCount = sup_count
        supplySupplier = supplier
    }
}