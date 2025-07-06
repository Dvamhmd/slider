package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?
)
