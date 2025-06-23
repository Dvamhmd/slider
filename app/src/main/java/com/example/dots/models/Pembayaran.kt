package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Pembayaran(
    @SerializedName("id_pembayaran") val idPembayaran: String?,
    @SerializedName("id_transaksi") val idTransaksi: String?,
    @SerializedName("metode_pembayaran") val metodePembayaran: String?,
    @SerializedName("bukti_bayar") val buktiBayar: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
