package com.example.dots.repository

import com.example.dots.models.BaseResponse
import com.example.dots.models.CheckoutData
import com.example.dots.models.PlaceOrderResponse
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.models.SnapTokenResponse
import com.example.dots.models.Transaksi
import com.example.dots.network.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
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

        return try {
            val response = api.prepareCheckout(body) // pakai Response<...>
            if (response.isSuccessful) {
                response.body()!!
            } else {
                val errorBody = response.errorBody()?.string()
                val gson = Gson()
                gson.fromJson(errorBody, BaseResponse::class.java) as BaseResponse<CheckoutData>
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val gson = Gson()
            gson.fromJson(errorBody, BaseResponse::class.java) as BaseResponse<CheckoutData>
        }
    }

    suspend fun placeOrder(
        selectedTokoId: String,
        items: List<RequestCheckoutItem>,
        metodePengiriman: String,
        promoId: String? = null,
        idAlamat: String? = null,
        catatan: String? = null
    ): BaseResponse<PlaceOrderResponse> {
        val body = mutableMapOf<String, Any?>(
            "selected_toko_id" to selectedTokoId,
            "items" to items,
            "metode_pengiriman" to metodePengiriman,
            "selected_promo_id" to promoId,
            "id_alamat" to idAlamat,
            "catatan_pembeli" to catatan
        )
        return api.placeOrder(body)
    }

    suspend fun getOrderHistory(): Response<BaseResponse<List<Transaksi>>> {
        return api.getOrders()
    }

    suspend fun getSnapToken(transaksiId: String): Response<SnapTokenResponse> {
        return api.createSnapToken(transaksiId)
    }


}
