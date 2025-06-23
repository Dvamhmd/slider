package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Transaksi(
    @SerializedName("id_transaksi") val idTransaksi: String?,
    @SerializedName("id_user") val idUser: String?,
    @SerializedName("id_toko") val idToko: String?,
    @SerializedName("metode_pengiriman") val metodePengiriman: String?,
    @SerializedName("catatan_pembeli") val catatanPembeli: String?,
    @SerializedName("id_alamat") val idAlamat: String?,
    @SerializedName("total_harga") val totalHarga: Double?,
    @SerializedName("id_promo_terpakai") val idPromoTerpakai: String?,
    @SerializedName("diskon") val diskon: Double?,
    @SerializedName("harga_akhir") val hargaAkhir: Double?,
    @SerializedName("status") val status: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
