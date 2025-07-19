package com.example.dots.models

import com.google.gson.annotations.SerializedName

data class SnapTokenResponse(
    @SerializedName("snap_token") val token: String?
)
