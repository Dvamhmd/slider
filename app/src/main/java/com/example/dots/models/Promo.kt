package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class Promo(
    @SerializedName("id_promo") val idPromo: String?,
    @SerializedName("nama_promo") val namaPromo: String?,
    @SerializedName("kode_promo") val kodePromo: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("jenis") val jenis: String?,
    @SerializedName("nilai_diskon") val nilaiDiskon: Double?,
    @SerializedName("minimal_pembelian") val minimalPembelian: Double?,
    @SerializedName("maksimal_diskon") val maksimalDiskon: Double?,
    @SerializedName("tanggal_mulai") val tanggalMulai: String?,
    @SerializedName("tanggal_berakhir") val tanggalBerakhir: String?,
    @SerializedName("jumlah_penggunaan") val jumlahPenggunaan: Int?,
    @SerializedName("kuota_promo") val kuotaPromo: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("syarat_ketentuan") val syaratKetentuan: String?,
    @SerializedName("target_semua_user") val targetSemuaUser: Boolean?,
    @SerializedName("target_user_baru") val targetUserBaru: Boolean?,
    @SerializedName("target_semua_produk") val targetSemuaProduk: Boolean?,
    @SerializedName("id_kategori_target") val idKategoriTarget: String?,
    @SerializedName("id_produk_target") val idProdukTarget: String?,
    @SerializedName("deskripsi_singkat") val deskripsiSingkat: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
