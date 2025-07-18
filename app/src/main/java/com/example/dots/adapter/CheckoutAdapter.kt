package com.example.dots.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dots.R
import com.example.dots.models.CheckoutItem
import com.example.dots.utilities.createShimmerDrawable
import com.example.dots.utilities.formatRupiah
import com.example.dots.utilities.toRupiah

class CheckoutAdapter(
    private var productList: List<CheckoutItem>
) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {


    inner class CheckoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgProduct: ImageView = itemView.findViewById(R.id.product_image)
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val quantity: TextView = itemView.findViewById(R.id.product_quantity)
        val subtotal: TextView = itemView.findViewById(R.id.product_subtotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkout_view, parent, false)
        return CheckoutViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        try {
            val product = productList[position]

            Log.i("CheckoutAdapter line 43", "data: $product")

            holder.name.text = product.nama_produk
            holder.price.text = product.harga_satuan.toString().toRupiah()
            holder.quantity.text = product.jumlah.toString()
            holder.subtotal.text = product.subtotal.toString().toRupiah()
        } catch (e: Exception) {
            Log.e("AdapterError", "Error at position $position: ${e.message}")
        }
    }

    override fun getItemCount(): Int = productList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CheckoutItem>) {
        productList = newList
        Log.i("CheckoutAdapter line 57", "data: $productList")
        notifyDataSetChanged()
    }
}