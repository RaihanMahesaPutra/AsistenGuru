// Buat file baru bernama Category.kt di dalam folder 'model'
package com.example.asistenguru.model

import com.google.firebase.firestore.DocumentId

data class Category(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val itemCount: Long = 0, // Firestore biasanya membaca angka sebagai Long
    val iconEmoji: String = ""
)