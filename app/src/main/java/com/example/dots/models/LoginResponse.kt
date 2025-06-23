package com.example.dots.models

data class LoginResponse(
    val status: String?,
    val message: String?,
    val data: LoginData?
)

data class LoginData(
    val user: User?,
    val token: String?
)
