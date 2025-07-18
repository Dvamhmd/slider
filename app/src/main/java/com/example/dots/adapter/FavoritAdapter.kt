package com.example.dots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dots.R
import com.example.dots.models.Favorit
import com.example.dots.utilities.createShimmerDrawable
import com.example.dots.utilities.toRupiah

class FavoritAdapter(
      private val list: List<Favorit>,
      private val onItemClick: (Favorit) -> Unit,
      private val onDeleteClick: (Favorit) -> Unit
) : RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {


    inner class FavoritViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.nama_produk)
        val harga: TextView = itemView.findViewById(R.id.harga_produk)
        val gambar: ImageView = itemView.findViewById(R.id.gambar_produk)
        val deleteButton: View = itemView.findViewById(R.id.delete_cart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorit, parent, false)
        return FavoritViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val item = list[position]
        holder.nama.text = item.namaProduk
        holder.harga.text = item.harga.toString().toRupiah()

        Glide.with(holder.itemView.context)
            .load(item.gambar)
            .placeholder(createShimmerDrawable())
            .into(holder.gambar)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.deleteButton.setOnClickListener {
              onDeleteClick(item)
        }

    }

    override fun getItemCount(): Int = list.size
}
