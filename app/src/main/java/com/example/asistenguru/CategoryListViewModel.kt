package com.example.asistenguru

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asistenguru.model.Category
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    private val db = Firebase.firestore

    // LiveData untuk menyimpan daftar kategori yang diambil
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    // LiveData untuk status loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Fungsi untuk memulai pengambilan data berdasarkan tipe
    fun fetchCategoriesByType(categoryType: String) {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("categories")
                .whereEqualTo("type", categoryType) // Filter berdasarkan tipe yang diberikan
                .get()
                .addOnSuccessListener { result ->
                    _categories.value = result.toObjects(Category::class.java)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                    // Handle error di sini jika perlu
                }
        }
    }
}