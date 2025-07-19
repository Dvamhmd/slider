package com.example.dots

import android.app.Application
import com.example.dots.BuildConfig.API_BASE_URL
import com.midtrans.sdk.uikit.external.UiKitApi

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val MIDTRANS_CLIENT_KEY = "Mid-client-tXp5Z_Hvpks9OgKs"

        // Inisialisasi Midtrans SDK
        UiKitApi.Builder()
            .withContext(this.applicationContext)
            .withMerchantUrl(API_BASE_URL)
            .withMerchantClientKey(MIDTRANS_CLIENT_KEY)
            .build()
    }
}
