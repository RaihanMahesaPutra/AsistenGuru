package com.example.asistenguru

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.CategoryDetailAdapter
import com.example.asistenguru.model.CategoryItem

class CategoryListActivity : AppCompatActivity(), CategoryDetailAdapter.OnCategoryClickListener {
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

        recyclerView.adapter = CategoryDetailAdapter(categoryList, this)
    }

    override fun onCategoryClick(category: CategoryItem) {
        val intent = Intent(this, DetailCategoryActivity::class.java).apply {
            putExtra("CATEGORY_TITLE", category.title)
            putExtra("CATEGORY_TYPE", category.type) // KIRIM TIPE KATEGORI
        }
        startActivity(intent)
    }

    private fun getPromptCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi Guru", 17, "prompt"),
            CategoryItem("📚", "Bahan Pembelajaran", 6, "prompt"),
            CategoryItem("🧠", "Manajemen & Refleksi", 8, "prompt"),
            CategoryItem("📋", "Evaluasi", 11, "prompt")
        )
    }

    private fun getWebCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi", 5, "web"),
            CategoryItem("✨", "Media Interaktif", 5, "web"),
            CategoryItem("📈", "Evaluasi Pembelajaran", 7, "web"),
            CategoryItem("🎬", "Media Pembelajaran", 7, "web")
        )
    }
}