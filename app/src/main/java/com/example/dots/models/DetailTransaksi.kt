package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class DetailTransaksi(
    @SerializedName("id_transaksi") val idTransaksi: String?,
    @SerializedName("id_produk") val idProduk: String?,
    @SerializedName("jumlah") val jumlah: Int?,
    @SerializedName("harga_total") val hargaTotal: Double?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
