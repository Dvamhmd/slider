package com.example.dots.network

import android.content.Context
import com.example.dots.TokenManager
import com.example.dots.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    fun getRetrofit(context: Context): Retrofit {
        // Pastikan TokenManager sudah diinisialisasi
        TokenManager.initialize(context)

        // Logging interceptor
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Interceptor untuk menambahkan Authorization header jika perlu
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept", "application/json")

            val token = TokenManager.getToken()
            val requestUrl = originalRequest.url.toString()

            if (!requestUrl.contains("/auth/login") &&
                !requestUrl.contains("/auth/register") &&
                token != null
            ) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }

            chain.proceed(requestBuilder.build())
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(authInterceptor) // ← hanya satu interceptor
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
