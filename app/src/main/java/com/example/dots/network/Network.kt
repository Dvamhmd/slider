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
        // Logging interceptor (untuk debug request/response)
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Interceptor untuk menambahkan header Authorization Bearer
        val authInterceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                .addHeader("Accept", "application/json")

            // Ambil token dari TokenManager
            val token = TokenManager.getToken()
            val requestUrl = chain.request().url.toString()

            // Hanya sisipkan Authorization kalau bukan endpoint login/register
            if (!requestUrl.contains("/auth/login") && !requestUrl.contains("/auth/register") && token != null) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }

            chain.proceed(requestBuilder.build())
        }

        // OkHttpClient
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) // waktu koneksi maksimal
            .readTimeout(10, TimeUnit.SECONDS)    // waktu tunggu respons
            .writeTimeout(10, TimeUnit.SECONDS)   // waktu kirim data
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                val token = TokenManager.getToken()
                val url = chain.request().url.toString()

                if (!url.contains("/auth/login") && !url.contains("/auth/register") && token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }

                chain.proceed(requestBuilder.build())
            }
            .build()


        // Retrofit
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
