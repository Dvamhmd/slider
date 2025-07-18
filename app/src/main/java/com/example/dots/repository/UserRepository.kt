package com.example.dots.repository

import android.content.Context
import android.net.Uri
import com.example.dots.TokenManager
import com.example.dots.models.LoginRequest
import com.example.dots.models.LoginResponse
import com.example.dots.models.RegisterRequest
import com.example.dots.models.RegisterResponse
import com.example.dots.models.User
import com.example.dots.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class UserRepository(private val context: Context) {

    private val api = ApiClient.getApiService(context)

    suspend fun login(login: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.login(LoginRequest(login, password))
                if (response.isSuccessful) {
                    response.body()?.data?.token?.let { TokenManager.saveToken(it) }
                    Result.success(response.body()!!)
                } else {
                    Result.failure(HttpException(response))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun register(data: RegisterRequest): Result<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.register(data)
                if (response.isSuccessful) {
                    response.body()?.token?.let { TokenManager.saveToken(it) }
                    Result.success(response.body()!!)
                } else {
//                    // Ambil error response JSON
//                    val errorJson = response.errorBody()?.string()
                    throw HttpException(response.apply {
                        errorBody()?.close()
                    }) // tetap lempar untuk ViewModel handle
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    suspend fun getUser(): Result<User> {
          return withContext(Dispatchers.IO) {
                try {
                  val response = api.getUser()
                  if (response.isSuccessful) {
                    Result.success(response.body()?.data!!)
                  } else {
                    Result.failure(HttpException(response))
                  }
                } catch (e: Exception) {
                  Result.failure(e)
                }
              }
    }

    suspend fun updateProfile(name: String, username: String, email: String, phone: String, photoData: ByteArray?): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                val nameBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
                val usernameBody = username.toRequestBody("text/plain".toMediaTypeOrNull())
                val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
                val phoneBody = phone.toRequestBody("text/plain".toMediaTypeOrNull())

                val imagePart = photoData?.let {
                    val requestFile = it.toRequestBody("image/jpeg".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("photo", "profile.jpg", requestFile)
                }

                val response = api.updateProfile(nameBody, usernameBody, emailBody, phoneBody, imagePart)

                if (response.isSuccessful) {
                    Result.success(response.body()!!.data!!)
                } else {
                    Result.failure(HttpException(response))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }





    fun logout() {
        TokenManager.clearToken()
    }
}
