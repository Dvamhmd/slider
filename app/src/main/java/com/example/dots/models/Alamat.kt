package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Alamat(
    @SerializedName("id_alamat") val idAlamat: String?,
    @SerializedName("id_user") val idUser: String?,
    @SerializedName("label_alamat") val labelAlamat: String?,
    @SerializedName("nama_penerima") val namaPenerima: String?,
    @SerializedName("no_hp_penerima") val noHpPenerima: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("status") val status: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)




