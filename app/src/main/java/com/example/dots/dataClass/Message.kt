package com.example.dots.dataClass

data class Message(
    val id: Int = 0,
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val timestamp: String = ""
)
