package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class ProdukToko(
    @SerializedName("id_toko") val idToko: String?,
    @SerializedName("id_produk") val idProduk: String?,
    @SerializedName("stok") val stok: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
