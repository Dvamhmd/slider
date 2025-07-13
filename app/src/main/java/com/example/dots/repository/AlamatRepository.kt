// AlamatRepository.kt
package com.example.dots.repository

import android.content.Context
import com.example.dots.models.Alamat
import com.example.dots.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Result

class AlamatRepository(context: Context) {

    val apiService = ApiClient.getApiService(context)

    suspend fun tambahAlamat(alamat: Alamat): Result<Alamat> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.tambahAlamat(alamat)
                if (response.isSuccessful && response.body()?.data != null) {
                    val listAlamat = response.body()!!.data!!
                    Result.success(listAlamat.last()) // Ambil alamat terakhir yang baru ditambahkan
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal menyimpan alamat"))
                }

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    suspend fun getAlamat(): Result<List<Alamat>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAlamat()
                if (response.isSuccessful && response.body()?.data != null) {
                    Result.success(response.body()!!.data!!)
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal mengambil alamat"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun editAlamat(alamat: Alamat): Result<Alamat> {
          return try {
                val response = apiService.editAlamat(alamat)
                if (response.isSuccessful && response.body() != null) {
                  Result.success(response.body()!!.data!!)
                } else {
                  Result.failure(Exception(response.message()))
                }
              } catch (e: Exception) {
                Result.failure(e)
              }
    }

    suspend fun deleteAlamat(id: String): Result<Unit> {
          return try {
                val response = apiService.deleteAlamat(mapOf("id_alamat" to id))
                if (response.isSuccessful) {
                  Result.success(Unit)
                } else {
                  Result.failure(Exception(response.message()))
                }
              } catch (e: Exception) {
                Result.failure(e)
              }
    }


}
