package com.example.asistenguru

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.CategoryDetailAdapter // DIUBAH: Impor adapter yang baru
import com.example.asistenguru.model.CategoryItem

class CategoryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        val pageTitleView: TextView = findViewById(R.id.tvPageTitle)
        val recyclerView: RecyclerView = findViewById(R.id.rvCategories)

        val pageTitle = intent.getStringExtra("PAGE_TITLE") ?: "Kategori"
        pageTitleView.text = pageTitle

        val categoryList = if (pageTitle.contains("Prompt")) {
            getPromptCategories()
        } else {
            getWebCategories()
        }

        // PERBEDAAN UTAMA: Menggunakan CategoryDetailAdapter
        recyclerView.adapter = CategoryDetailAdapter(categoryList)
    }

    // Data contoh untuk Kategori Prompt AI dengan EMOJI
    private fun getPromptCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi Guru", 17, "prompt"),
            CategoryItem("📚", "Bahan Pembelajaran", 6, "prompt"),
            CategoryItem("🧠", "Manajemen & Refleksi", 8, "prompt"),
            CategoryItem("📋", "Evaluasi", 11, "prompt")
        )
    }

    // Data contoh untuk Kategori Web AI dengan EMOJI
    private fun getWebCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi", 5, "web"),
            CategoryItem("✨", "Media Interaktif", 5, "web"),
            CategoryItem("📈", "Evaluasi Pembelajaran", 7, "web"),
            CategoryItem("🎬", "Media Pembelajaran", 7, "web")
        )
    }
}