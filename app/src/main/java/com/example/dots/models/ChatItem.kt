package com.example.dots.models

data class ChatItem(
    val name: String,
    val message: String,
    val time: String,
    val imageResId: Int,
    val isRead: Boolean
)
