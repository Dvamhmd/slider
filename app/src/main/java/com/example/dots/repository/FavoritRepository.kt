package com.example.dots.repository

import android.content.Context
import com.example.dots.network.ApiClient
import com.example.dots.models.BaseResponse
import com.example.dots.models.Favorit
import retrofit2.HttpException

class FavoritRepository(private val context: Context) {
    val apiService = ApiClient.getApiService(context)

    suspend fun getFavorit(): Result<BaseResponse<List<Favorit>>> {
        return try {
            val response = apiService.getFavorit()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Data kosong"))
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

