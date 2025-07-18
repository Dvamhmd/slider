package com.example.dots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.TokenManager
import com.example.dots.models.Alamat

class AlamatAdapter(
      private var alamatList: List<Alamat>,
      private val onEditClick: (Alamat) -> Unit,
      private val onDeleteClick: (Alamat) -> Unit,
      private val onSetUtamaClick: (Alamat) -> Unit
) : RecyclerView.Adapter<AlamatAdapter.AlamatViewHolder>() {


    inner class AlamatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.address_label)
        val namePhone: TextView = itemView.findViewById(R.id.address_name_phone)
        val detail: TextView = itemView.findViewById(R.id.address_detail)
        val fullAlamat: TextView = itemView.findViewById(R.id.address)
        val editBtn: ImageView = itemView.findViewById(R.id.edit_button)
        val deleteBtn: ImageView = itemView.findViewById(R.id.delete_button)
        val checkBox: ImageView = itemView.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlamatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_address, parent, false)
        return AlamatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlamatViewHolder, position: Int) {
          val alamat = alamatList[position]
          holder.label.text = alamat.labelAlamat ?: "-"
          holder.namePhone.text = "${alamat.namaPenerima} - ${alamat.noHpPenerima}"
          holder.detail.text = alamat.detailAlamat ?: "-"
          holder.fullAlamat.text = alamat.alamat ?: "-"

          // Checklist
        val newImage = if (alamat.status == "utama") R.drawable.checked else R.drawable.uncheck
        if (alamat.status == "utama") {
            TokenManager.saveAddressDetail(alamat.detailAlamat.toString())
        }


        holder.checkBox.animate()
            .scaleX(0.7f)
            .scaleY(0.7f)
            .alpha(0.5f)
            .setDuration(100)
            .withEndAction {
                holder.checkBox.setImageResource(newImage)
                holder.checkBox.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(100)
                    .start()
            }
            .start()



        holder.checkBox.setOnClickListener {
                if (alamat.status != "utama") {
                  onSetUtamaClick(alamat)
                }
              }

          holder.editBtn.setOnClickListener { onEditClick(alamat) }
          holder.deleteBtn.setOnClickListener { onDeleteClick(alamat) }
    }



    override fun getItemCount(): Int = alamatList.size

    // ✅ Tambahkan fungsi ini agar bisa update list-nya
    fun updateData(newList: List<Alamat>) {
        alamatList = newList
        notifyDataSetChanged()
    }
}
