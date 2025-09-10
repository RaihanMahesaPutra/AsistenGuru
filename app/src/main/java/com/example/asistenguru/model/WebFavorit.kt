package com.example.asistenguru.model

data class WebFavorit(
    val logoResId: Int, // DIUBAH: Menggunakan ID sumber daya drawable
    val name: String,
    val description: String,
    val url: String
)