package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Alamat(
    @SerializedName("id_alamat") val idAlamat: String? = null,
    @SerializedName("id_user") val idUser: String? = null,
    @SerializedName("label_alamat") val labelAlamat: String? = null,
    @SerializedName("nama_penerima") val namaPenerima: String? = null,
    @SerializedName("no_hp_penerima") val noHpPenerima: String? = null,
    @SerializedName("alamat") val alamat: String? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)
