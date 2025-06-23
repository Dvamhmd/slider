package com.example.dots.models

data class CheckoutSummary(
    val total: Double?,
    val subtotal: Double?,
    val diskon: Double?,
    val promo: Promo?
)
