package com.example.asistenguru.model

// model/CategoryItem.kt
data class CategoryItem(
    val id: String, // TAMBAHKAN INI
    val iconEmoji: String,
    val title: String,
    val itemCount: Int,
    val type: String
)