package com.example.dots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dots.R
import com.example.dots.models.Kategori
import com.example.dots.utilities.createShimmerDrawable

class KategoriAdapter(
    private val listKategori: List<Kategori>,
    private val onItemClick: (Kategori) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    inner class KategoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSeries: ImageView = itemView.findViewById(R.id.image_series)
        val nameSeries: TextView = itemView.findViewById(R.id.name_series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return KategoriViewHolder(view)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = listKategori[position]
        holder.nameSeries.text = kategori.namaKategori


        Glide.with(holder.itemView.context)
            .load(kategori.gambar)
            .placeholder(createShimmerDrawable()) // fallback
            .into(holder.imageSeries)

        holder.itemView.setOnClickListener {
            onItemClick(kategori)
        }
    }

    override fun getItemCount(): Int = listKategori.size
}
