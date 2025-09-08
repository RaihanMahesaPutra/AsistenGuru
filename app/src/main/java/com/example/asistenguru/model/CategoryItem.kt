package com.example.asistenguru.model

// Menggunakan emoji (String) bukan ID drawable (Int)
data class CategoryItem(
    val iconEmoji: String,
    val title: String,
    val itemCount: Int,
    val type: String
)