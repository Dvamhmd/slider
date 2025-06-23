package com.example.dots.network

import android.content.Context
import com.example.dots.network.Network
import retrofit2.Retrofit

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getApiService(context: Context): ApiService {
        if (retrofit == null) {
            retrofit = Network.getRetrofit(context)
        }
        return retrofit!!.create(ApiService::class.java)
    }

}
