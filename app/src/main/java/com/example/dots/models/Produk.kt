package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Produk(
    @SerializedName("id_produk") val idProduk: String?,
    @SerializedName("id_kategori") val idKategori: String?,
    @SerializedName("gambar_produk") val gambarProduk: String?,
    @SerializedName("nama_produk") val namaProduk: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("harga") val harga: Double?,
    @SerializedName("jumlah_terjual") val jumlahTerjual: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
