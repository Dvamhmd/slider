package com.example.dots.network

import com.example.dots.models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // 🔐 Auth
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<ApiResponse<Any?>>

    // 👤 User
    @GET("user")
    suspend fun getUser(): Response<User>

    @POST("update-user")
    suspend fun updateUser(@Body request: User): Response<User>

    @POST("update-password")
    suspend fun updatePassword(@Body request: UpdatePasswordRequest): Response<ApiResponse<Any?>>

    // 📍 Alamat
    @POST("tambah-alamat")
    suspend fun tambahAlamat(@Body alamat: Alamat): Response<Alamat>

    @GET("get-alamat")
    suspend fun getAlamat(): Response<List<Alamat>>

    @POST("edit-alamat")
    suspend fun editAlamat(@Body alamat: Alamat): Response<Alamat>

    @POST("delete-alamat")
    suspend fun deleteAlamat(@Body idAlamat: Map<String, String>): Response<ApiResponse<Any?>>

    // ❤️ Favorit
    @POST("tambah-favorit")
    suspend fun tambahFavorit(@Body favorit: Favorit): Response<ApiResponse<Any?>>

    @GET("get-favorit")
    suspend fun getFavorit(): Response<List<Favorit>>

    @POST("delete-favorit")
    suspend fun deleteFavorit(@Body favorit: Favorit): Response<ApiResponse<Any?>>

    // 🛒 Keranjang
    @POST("tambah-keranjang")
    suspend fun tambahKeranjang(@Body keranjang: Keranjang): Response<ApiResponse<Any?>>

    @GET("get-keranjang")
    suspend fun getKeranjang(): Response<List<Keranjang>>

    @POST("edit-keranjang")
    suspend fun editKeranjang(@Body keranjang: Keranjang): Response<ApiResponse<Any?>>

    @POST("delete-keranjang")
    suspend fun deleteKeranjang(@Body keranjang: Map<String, String>): Response<ApiResponse<Any?>>

    // 🛍️ Checkout & Order
    @GET("checkout")
    suspend fun prepareCheckout(): Response<CheckoutSummary>

    @GET("orders")
    suspend fun getOrders(): Response<List<Transaksi>>

    @POST("orders/checkout")
    suspend fun checkoutOrder(@Body transaksi: Transaksi): Response<ApiResponse<Any?>>

    @POST("orders")
    suspend fun placeOrder(@Body transaksi: Transaksi): Response<ApiResponse<Any?>>

    // 🔖 Promo
    @GET("promo")
    suspend fun getPromo(): Response<List<Promo>>

    @POST("promo/rekomendasi")
    suspend fun getPromoRekomendasi(): Response<List<Promo>>

    @POST("cart/apply-promo")
    suspend fun applyPromoToCart(@Body promoRequest: PromoRequest): Response<ApiResponse<Any?>>

    // 🏬 Toko
    @GET("toko")
    suspend fun getToko(): Response<List<Toko>>

    @GET("toko/nearest")
    suspend fun getTokoTerdekat(): Response<Toko?>

    // 📦 Produk & Kategori
    @GET("produk")
    suspend fun getProduk(): Response<List<Produk>>

    @GET("kategori")
    suspend fun getKategori(): Response<BaseResponse<List<Kategori>>>

    // 📸 Upload Foto
    @Multipart
    @POST("upload-foto")
    suspend fun uploadFoto(@Part file: MultipartBody.Part): Response<ApiResponse<Any?>>

    // 💳 Payment
    @POST("payment/create-token/{transaksi}")
    suspend fun createSnapToken(@Path("transaksi") idTransaksi: String): Response<SnapTokenResponse>

    @POST("payment/update-status/{id_transaksi}")
    suspend fun updatePaymentStatus(@Path("id_transaksi") id: String): Response<ApiResponse<Any?>>
}
