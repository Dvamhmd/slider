package com.example.dots.network

import com.example.dots.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    suspend fun getUser(): Response<BaseResponse<User>>


    @Multipart
    @POST("update-user")
    suspend fun updateProfile(
        @Part("nama") nama: RequestBody,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("no_hp") noHp: RequestBody,
        @Part photo: MultipartBody.Part?
    ): Response<BaseResponse<User>>



    @POST("update-password")
    suspend fun updatePassword(@Body request: UpdatePasswordRequest): Response<ApiResponse<Any?>>

    // 📍 Alamat
    @POST("tambah-alamat")
    suspend fun tambahAlamat(@Body alamat: Alamat): Response<BaseResponse<List<Alamat>>>

    @GET("get-alamat")
    suspend fun getAlamat(): Response<BaseResponse<List<Alamat>>>

    @POST("edit-alamat")
    suspend fun editAlamat(@Body alamat: Alamat): Response<BaseResponse<Alamat>>

    @POST("delete-alamat")
    suspend fun deleteAlamat(@Body id: Map<String, String>): Response<BaseResponse<List<Alamat>>>

    // ❤️ Favorit
    @FormUrlEncoded
    @POST("tambah-favorit")
    suspend fun tambahFavorit(
        @Field("id_produk") idProduk: String,
        @Field("id_toko") idToko: String
    ): BaseResponse<Unit>

    @GET("get-favorit")
    suspend fun getFavorit(): Response<BaseResponse<List<Favorit>>>

    @FormUrlEncoded
    @POST("delete-favorit")
    suspend fun hapusFavorit(
        @Field("id_produk") idProduk: String
    ): Response<ApiResponse<Any?>>

    // 🛒 Keranjang
    @GET("get-keranjang")
    suspend fun getKeranjang(): BaseResponse<Map<String, Any>>

    @FormUrlEncoded
    @POST("tambah-keranjang")
    suspend fun tambahKeranjang(
        @Field("id_produk") idProduk: String,
        @Field("id_toko") idToko: String,
        @Field("jumlah") jumlah: Int = 1
    ): BaseResponse<Unit>

    @POST("edit-keranjang")
    suspend fun editKeranjang(@Body keranjang: Keranjang): Response<ApiResponse<Any?>>

    @POST("delete-keranjang")
    suspend fun hapusKeranjang(@Body keranjang: Keranjang): Response<ApiResponse<Any?>>

    // 🛍️ Checkout & Order
    @POST("checkout")
    suspend fun prepareCheckout(
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): Response<BaseResponse<CheckoutData>>


    @GET("orders")
    suspend fun getOrders(): Response<BaseResponse<List<Transaksi>>>

    @POST("orders/checkout")
    suspend fun checkoutOrder(@Body transaksi: Transaksi): Response<ApiResponse<Any?>>

    @POST("orders")
    suspend fun placeOrder(@Body request: Map<String, @JvmSuppressWildcards Any?>): BaseResponse<PlaceOrderResponse>


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
