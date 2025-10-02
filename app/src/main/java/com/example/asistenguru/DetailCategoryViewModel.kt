package com.example.asistenguru

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asistenguru.model.Item // Pastikan model Item Anda sudah ada
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class DetailCategoryViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchItemsForCategory(categoryId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("items")
                .whereEqualTo("categoryId", categoryId) // Cari item berdasarkan ID kategori
                .get()
                .addOnSuccessListener { result ->
                    _items.value = result.toObjects(Item::class.java)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
        }
    }
}