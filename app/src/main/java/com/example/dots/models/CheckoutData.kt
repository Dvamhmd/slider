package com.example.dots.models

data class CheckoutData(
    val items: List<CheckoutItem>,
    val alamat_pengguna: Alamat,
    val toko_terpilih: TokoTerpilih,
    val ringkasan_harga: RingkasanHarga,
    val promo_terpilih: Promo?,
    val rekomendasi_promo: List<Promo>
)

data class CheckoutItem(
    val id_produk: String,
    val nama_produk: String,
    val jumlah: Int,
    val harga_satuan: Int,
    val subtotal: Int
)

data class TokoTerpilih(val id_toko: String)
data class RingkasanHarga(val subtotal: Int, val diskon: Int, val total_akhir: Int)
