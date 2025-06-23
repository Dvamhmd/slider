package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Kategori(
    @SerializedName("id_kategori") val idKategori: String?,
    @SerializedName("nama_kategori") val namaKategori: String?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
