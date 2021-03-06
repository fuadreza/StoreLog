package io.github.fuadreza.storelog.view.edit

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.database.entity.Supply
import io.github.fuadreza.storelog.viewmodel.SupplyViewModel
import io.github.fuadreza.storelog.viewmodel.state.SupplyState
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */
@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    private val supplyViewModel: SupplyViewModel by viewModels()

    var name: String = ""
    var count: Int = 0
    var supplierName: String = ""

    lateinit var supplyName: TextView
    lateinit var supplyCount: TextView
    lateinit var supplySupplier: TextView

    private val EXTRA_SUPPLY = "EXTRA_SUPPLY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val supply = intent.getSerializableExtra(EXTRA_SUPPLY) as Supply

        setupViews(supply)

        lifecycle.addObserver(supplyViewModel)

        clickListener(supply)

    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun clickListener(supply: Supply) {
        btn_add.setOnClickListener {
            saveData(supply.supplyId)
        }

        btn_delete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                supplyViewModel.deleteById(supply.supplyId)
            }
        }
    }

    private fun observe() {
        supplyViewModel.supplyState.observe(this, Observer<SupplyState> { state ->
            handleUI(state)
        })
    }


    private fun saveData(id: Int) {

        val name = supplyName.text.toString()
        val count = supplyCount.text.toString()
        val supplier = supplySupplier.text.toString()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyy hh:mm:ss", Locale.getDefault())
        val currentDate = simpleDateFormat.format(Date())

        if (validate(name, count, supplier)) {
            val updatedSupply =
                Supply(
                    id,
                    name,
                    count.toInt(),
                    supplier,
                    currentDate
                )

            CoroutineScope(Dispatchers.IO).launch {
                supplyViewModel.updateSupply(updatedSupply)
            }
        } else {
            Toast.makeText(this, "There is still empty imformation", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViews(supply: Supply) {
        supplyName = sup_name
        supplyCount = sup_count
        supplySupplier = supplier

        supplyName.text = supply.name
        supplyCount.text = supply.items.toString()
        supplySupplier.text = supply.supplier
    }

    private fun validate(name: String?, count: String?, supplier: String?): Boolean {
        return !(name!!.isEmpty() || count!!.isEmpty() || supplier!!.isEmpty())
    }

    private fun handleUI(state: SupplyState?) {
        when (state) {
            is SupplyState.OnSuccess -> finish()
        }
    }
}