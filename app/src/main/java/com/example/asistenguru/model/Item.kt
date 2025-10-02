package com.example.asistenguru.model

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId
    val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val url: String = "",
    val imageUrl: String = ""
)