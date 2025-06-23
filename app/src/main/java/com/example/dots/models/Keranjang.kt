package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Keranjang(
    @SerializedName("id_keranjang") val idKeranjang: String?,
    @SerializedName("id_user") val idUser: String?,
    @SerializedName("id_toko") val idToko: String?,
    @SerializedName("id_produk") val idProduk: String?,
    @SerializedName("jumlah") val jumlah: Int?,
    @SerializedName("harga_total") val hargaTotal: Double?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
