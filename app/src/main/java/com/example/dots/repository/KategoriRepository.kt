package com.example.dots.repository

import android.content.Context
import com.example.dots.models.BaseResponse
import com.example.dots.models.Kategori
import com.example.dots.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KategoriRepository(context: Context) {
    private val apiService = ApiClient.getApiService(context)

    suspend fun getKategori(): Result<BaseResponse<List<Kategori>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getKategori()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Gagal mengambil kategori"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}
