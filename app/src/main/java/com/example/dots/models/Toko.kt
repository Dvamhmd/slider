package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Toko(
    @SerializedName("id_toko") val idToko: String?,
    @SerializedName("nama_toko") val namaToko: String?,
    @SerializedName("alamat_toko") val alamatToko: String?,
    @SerializedName("no_hp_toko") val noHpToko: String?,
    @SerializedName("email_toko") val emailToko: String?,
    @SerializedName("foto_toko") val fotoToko: String?,
    @SerializedName("status_toko") val statusToko: String?,
    @SerializedName("username_admin") val usernameAdmin: String?,
    @SerializedName("password_admin") val passwordAdmin: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
