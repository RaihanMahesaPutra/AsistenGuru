package com.example.asistenguru

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asistenguru.model.Category
import com.example.asistenguru.model.WebFavorit
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val db = Firebase.firestore

    // LiveData untuk kategori prompt
    private val _promptCategories = MutableLiveData<List<Category>>()
    val promptCategories: LiveData<List<Category>> = _promptCategories
    private val _webAiCategories = MutableLiveData<List<Category>>()
    val webAiCategories: LiveData<List<Category>> = _webAiCategories
    private val _favorites = MutableLiveData<List<WebFavorit>>()
    val favorites: LiveData<List<WebFavorit>> = _favorites

    // LiveData untuk status loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchPromptCategories()
        fetchWebAiCategories()
        fetchFavorites()
    }

    private fun fetchPromptCategories() {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("categories")
                .whereEqualTo("type", "prompt")
                .get()
                .addOnSuccessListener { result ->
                    _promptCategories.value = result.toObjects(Category::class.java)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
        }
    }

    // BARU: Fungsi untuk mengambil data kategori Web AI
    private fun fetchWebAiCategories() {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("categories")
                .whereEqualTo("type", "web") // Filter hanya untuk tipe "web"
                .get()
                .addOnSuccessListener { result ->
                    _webAiCategories.value = result.toObjects(Category::class.java)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
        }
    }

    private fun fetchFavorites() {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("favorites")
                .get()
                .addOnSuccessListener { result ->
                    _favorites.value = result.toObjects(WebFavorit::class.java)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
        }
    }
}