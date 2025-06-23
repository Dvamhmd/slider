package com.example.dots.models

data class UpdatePasswordRequest(
    val current_password: String,
    val new_password: String,
    val confirm_password: String
)
