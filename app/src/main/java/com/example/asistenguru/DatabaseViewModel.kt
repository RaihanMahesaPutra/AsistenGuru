package com.example.asistenguru

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asistenguru.model.GlossaryItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _glossaryItems = MutableLiveData<List<GlossaryItem>>()
    val glossaryItems: LiveData<List<GlossaryItem>> = _glossaryItems

    init {
        fetchGlossaryItems()
    }

    private fun fetchGlossaryItems() {
        viewModelScope.launch {
            db.collection("glossary")
                .get()
                .addOnSuccessListener { result ->
                    _glossaryItems.value = result.toObjects(GlossaryItem::class.java)
                }
                .addOnFailureListener {
                    // Handle error
                }
        }
    }
}