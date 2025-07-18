package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id_user") val idUser: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("no_hp") val noHp: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("foto_uri") val foto: String?,
    @SerializedName("remember_token") val rememberToken: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
