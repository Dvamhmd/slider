package com.example.dots.models

data class RegisterRequest(
    val nama: String,
    val email: String,
    val username: String,
    val password: String,
    val no_hp: String?
)
