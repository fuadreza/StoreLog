package io.github.fuadreza.storelog.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.ui.add.AddSuplyActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class SupplyActivity : AppCompatActivity() {

    private lateinit var supplyViewModel: SupplyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supplyViewModel = ViewModelProvider(this).get(SupplyViewModel::class.java).apply {

        }

        val adapter = SupplyAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        observe(adapter)

        btn_add_new.setOnClickListener {
            startActivity(Intent(this, AddSuplyActivity::class.java))
        }
    }

    private fun observe(adapter: SupplyAdapter) {
        supplyViewModel.allSupplies.observe(this, Observer {supplies ->
            supplies.let { adapter.setSupply(it) }
        })
    }
}