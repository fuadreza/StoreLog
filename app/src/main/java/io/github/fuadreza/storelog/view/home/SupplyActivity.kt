package io.github.fuadreza.storelog.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.view.add.AddSuplyActivity
import io.github.fuadreza.storelog.viewmodel.SupplyViewModel
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

@AndroidEntryPoint
class SupplyActivity : AppCompatActivity(), LifecycleOwner {

    private val supplyViewModel: SupplyViewModel by viewModels()

    private lateinit var supplyAdapter: SupplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lifecycle.addObserver(supplyViewModel)

        initAdapter()

        clickListener()

    }

    override fun onResume() {
        super.onResume()
        observe(supplyAdapter)
    }

    private fun initAdapter() {
        supplyAdapter = SupplyAdapter(this)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@SupplyActivity, RecyclerView.VERTICAL, false)
            adapter = supplyAdapter
        }
    }

    private fun clickListener() {
        btn_add_new.setOnClickListener {
            startActivity(Intent(this, AddSuplyActivity::class.java))
        }
    }

    private fun observe(adapter: SupplyAdapter) {
        supplyViewModel.allSupplies.observe(this, Observer { supplies ->
            supplies.let { adapter.setSupply(it) }
        })
    }
}