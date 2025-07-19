package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status") val success: Boolean?,
    @SerializedName("error_type") val errorType: String?,
    @SerializedName("item_bermasalah") val itemBermasalah: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?
)
