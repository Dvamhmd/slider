package com.example.dots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.models.Keranjang
import com.example.dots.R
import com.example.dots.utilities.toRupiah

class KeranjangAdapter(
    private val list: List<Keranjang>,
    private val onDeleteClick: (Keranjang) -> Unit
) : RecyclerView.Adapter<KeranjangAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.product_image)
        val name = itemView.findViewById<TextView>(R.id.product_name)
        val price = itemView.findViewById<TextView>(R.id.product_price)
        val quantity = itemView.findViewById<TextView>(R.id.product_quantity)
        val deleteButton = itemView.findViewById<CardView>(R.id.delete_cart)

        fun bind(item: Keranjang) {
            // Tampilkan data
            name.text = item.namaProduk
            price.text = item.harga.toString().toRupiah()
            quantity.text = "${item.jumlah}"

            // TODO: Load image pakai Glide/Picasso jika kamu punya URL-nya
            // Glide.with(itemView.context).load(item.gambar).into(image)

            deleteButton.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.keranjang_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Keranjang>) {
        (list as MutableList).clear()
        (list as MutableList).addAll(newList)
        notifyDataSetChanged()
    }

}
