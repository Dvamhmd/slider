package com.example.dots.repository

import com.example.dots.network.ApiService

class KeranjangRepository(private val apiService: ApiService) {
    suspend fun getKeranjang() = apiService.getKeranjang()
    suspend fun tambahKeranjang(idProduk: String, idToko: String, jumlah: Int = 1) =
        apiService.tambahKeranjang(idProduk, idToko, jumlah)
}
