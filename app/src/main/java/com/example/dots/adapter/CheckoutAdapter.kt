package com.example.dots.adapter

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

class CheckoutProductAdapter(
    private var productList: List<CheckoutItem>
) : RecyclerView.Adapter<CheckoutProductAdapter.CheckoutViewHolder>() {


    inner class CheckoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.product_image)
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val quantity: TextView = itemView.findViewById(R.id.product_quantity)
        val size: TextView = itemView.findViewById(R.id.product_quantity) // Ganti jika ukuran ada ID tersendiri
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkout_view, parent, false)

        Log.d("Checkout", "data: jkasjdaaaaaaaakkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
        return CheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        val product = productList[position]

        Log.d("Checkout", "data: $product")

        holder.name.text = product.nama_produk
        holder.price.text = "Rp ${product.harga_satuan}"
        holder.quantity.text = product.jumlah.toString()
        holder.size.text = (product.jumlah ?: "") as CharSequence?

//        Glide.with(holder.itemView.context)
//            .load(product.gambar ?: createShimmerDrawable())
//            .into(holder.imgProduct)
    }

    override fun getItemCount(): Int = productList.size

    fun updateList(newList: List<CheckoutItem>) {
        productList = newList
        notifyDataSetChanged()
    }
}