package com.example.dots.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.models.Transaksi
import com.example.dots.utilities.toRupiah

class OrderHistoryAdapter(
    private var orders: List<Transaksi>,
    private val onItemClick: (Transaksi) -> Unit
) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvOrderId = itemView.findViewById<TextView>(R.id.order_id)
        private val tvOrderStatus = itemView.findViewById<TextView>(R.id.order_status)
        private val tvOrderCost = itemView.findViewById<TextView>(R.id.order_cost)
        private val tvOrderItemQty = itemView.findViewById<TextView>(R.id.order_item_qty)

        fun bind(item: Transaksi) {
            Log.d("ORDER_ADAPTER line 26", "Bind order: ${item.idTransaksi}")
            tvOrderId.text = item.idTransaksi ?: "-"
            tvOrderStatus.text = item.status ?: "-"
            tvOrderCost.text = item.hargaAkhir?.toString()?.toRupiah() ?: "Rp0"
            tvOrderItemQty.text = item.jumlah.toString()

            itemView.setOnClickListener {
                onItemClick(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_status_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
          Log.d("ADAPTER_COUNT", "Jumlah item di adapter line 46: ${orders.size}")
          return orders.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          Log.d("order ADAPTER_BIND line 48", "onBindViewHolder dipanggil untuk posisi $position")
          holder.bind(orders[position])
    }

    fun updateOrders(newOrders: List<Transaksi>) {
            this.orders = newOrders
            notifyDataSetChanged()
          }

}
