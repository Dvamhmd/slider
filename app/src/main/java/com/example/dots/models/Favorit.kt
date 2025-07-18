package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Favorit(
    @SerializedName("id_user") val idUser: String?,
    @SerializedName("id_produk") val idProduk: String?,
    @SerializedName("nama_produk") val namaProduk: String?,
    @SerializedName("harga") val harga: Int?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
