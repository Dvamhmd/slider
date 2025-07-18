package com.example.dots.models

data class PlaceOrderResponse(
    val id_transaksi: String,
    val harga_akhir: Double,
    val status: String,
    val pembayaran: PembayaranInfo
)

data class PembayaranInfo(
    val id_pembayaran: String,
    val status: String,
    val jumlah_dibayar: Double,
    val bukti_bayar: String?
)
