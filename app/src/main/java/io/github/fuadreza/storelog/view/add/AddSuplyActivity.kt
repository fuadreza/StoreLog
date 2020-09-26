package io.github.fuadreza.storelog.view.add

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.database.entity.Supply
import io.github.fuadreza.storelog.viewmodel.SupplyViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */
@AndroidEntryPoint
class AddSuplyActivity : AppCompatActivity() {

    lateinit var supplyName: TextView
    lateinit var supplyCount: TextView
    lateinit var supplySupplier: TextView

    private val supplyViewModel: SupplyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        lifecycle.addObserver(supplyViewModel)

        /*supplyViewModel = ViewModelProvider(this).get(SupplyViewModel::class.java).apply {

        }*/
        setupViews()

        btn_add.setOnClickListener {
            addNewSupply()
        }

    }

    private fun addNewSupply() {
        val name = supplyName.text.toString()
        val count = supplyCount.text.toString()
        val supplier = supplySupplier.text.toString()

        if (validate(name, count, supplier)) {
            val supply = Supply(
                0,
                name = name,
                items = count.toInt(),
                supplier = supplier,
                date = "2020"
            )
            CoroutineScope(IO).launch {
                supplyViewModel.addSupply(supply)
                finish()
            }
        } else {
            Toast.makeText(this, "Please fill all the information", Toast.LENGTH_LONG).show()
        }

    }

    private fun validate(name: String?, count: String?, supplier: String?): Boolean {
        return !(name!!.isEmpty() || count!!.isEmpty()|| supplier!!.isEmpty())
    }

    private fun setupViews() {
        supplyName = sup_name
        supplyCount = sup_count
        supplySupplier = supplier
    }
}