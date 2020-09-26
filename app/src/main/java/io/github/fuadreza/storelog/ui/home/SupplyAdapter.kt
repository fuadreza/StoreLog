package io.github.fuadreza.storelog.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.fuadreza.storelog.R
import io.github.fuadreza.storelog.model.Supply

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 26/09/2020.
 *
 */

class SupplyAdapter constructor(context: Context): RecyclerView.Adapter<SupplyAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var supplies = emptyList<Supply>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplyAdapter.ViewHolder {
        val itemView = inflater.inflate(R.layout.item_supply, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = supplies.size

    override fun onBindViewHolder(holder: SupplyAdapter.ViewHolder, position: Int) {
        val current = supplies[position]
        holder.name.text = current.name
        holder.count.text = current.items.toString()
        holder.supplier.text = current.supplier
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.supply_name)
        val count: TextView = itemView.findViewById(R.id.suppy_count)
        val supplier: TextView = itemView.findViewById(R.id.suppy_suplier)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    internal fun setSupply(supplies: List<Supply>) {
        this.supplies = supplies
        notifyDataSetChanged()
    }
}