package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Kategori(
    @SerializedName("id_kategori") val idKategori: String?,
    @SerializedName("nama_kategori") val namaKategori: String?,
    @SerializedName("gambar_kategori")
    private val gambarRaw: String?,  // Simpan raw value dari API
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
) {
    val gambar: String?
        get() = if (gambarRaw?.contains("127.0.0.1") == true)
            gambarRaw.replace("127.0.0.1", "sharp-sheep-gladly.ngrok-free.app")
        else
            gambarRaw
}

