package com.example.dots.repository

import com.example.dots.models.BaseResponse
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.network.ApiService
import retrofit2.Response


class CheckoutRepository(private val api: ApiService) {

    suspend fun prepareCheckout(
        tokoId: String,
        items: List<RequestCheckoutItem>,
        promoId: String?
    ): BaseResponse<CheckoutData> {
        val body = mapOf(
            "selected_toko_id" to tokoId,
            "items" to items,
            "selected_promo_id" to promoId
        )
        return api.prepareCheckout(body)
    }
}
