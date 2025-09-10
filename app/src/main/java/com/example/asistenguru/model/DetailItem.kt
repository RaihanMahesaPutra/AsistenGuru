package com.example.asistenguru.model

sealed class DetailItem {
    data class Prompt(
        val content: String
    ) : DetailItem()

    data class WebAi(
        val logoResId: Int, // DIUBAH: Menggunakan ID sumber daya drawable
        val name: String,
        val description: String,
        val url: String
    ) : DetailItem()
}