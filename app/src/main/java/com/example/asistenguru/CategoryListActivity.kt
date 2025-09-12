package com.example.asistenguru

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.CategoryDetailAdapter
import com.example.asistenguru.data.DataSource
import com.example.asistenguru.model.CategoryItem
import com.google.android.material.textfield.TextInputEditText

class CategoryListActivity : AppCompatActivity(), CategoryDetailAdapter.OnCategoryClickListener {

    private lateinit var adapter: CategoryDetailAdapter
    private val originalList = mutableListOf<CategoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        val pageTitleView: TextView = findViewById(R.id.tvPageTitle)
        val recyclerView: RecyclerView = findViewById(R.id.rvCategories)
        val searchIcon: ImageView = findViewById(R.id.ivSearch)

        val pageTitle = intent.getStringExtra("PAGE_TITLE") ?: "Kategori"
        pageTitleView.text = pageTitle

        val initialItems = if (pageTitle.contains("Prompt")) {
            DataSource.getPromptCategories()
        } else {
            DataSource.getWebCategories()
        }
        originalList.addAll(initialItems)

        adapter = CategoryDetailAdapter(ArrayList(originalList), this)
        recyclerView.adapter = adapter

        searchIcon.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun showSearchDialog() {
        // DIUBAH: Gunakan tema dialog yang baru dibuat
        val builder = AlertDialog.Builder(this, R.style.App_AlertDialogTheme)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_search, null)
        val input = dialogView.findViewById<TextInputEditText>(R.id.etSearchQuery)

        // DIUBAH: Gunakan setTitle bawaan
        builder.setTitle("Cari Kategori")
        builder.setView(dialogView)

        // DIUBAH: Gunakan tombol bawaan yang sekarang sudah terlihat
        builder.setPositiveButton("Cari") { dialog, _ ->
            val query = input.text.toString()
            filter(query)
            dialog.dismiss()
        }

        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.cancel()
        }

        builder.setNeutralButton("Reset") { dialog, _ ->
            filter("")
            dialog.dismiss()
        }

        builder.show()
    }

    private fun filter(query: String) {
        val filteredList = mutableListOf<CategoryItem>()
        if (query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            for (item in originalList) {
                if (item.title.contains(query, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
        }
        adapter.filterList(filteredList)
    }

    override fun onCategoryClick(category: CategoryItem) {
        // Aksi klik di halaman ini juga akan membuka halaman detail
        val intent = Intent(this, DetailCategoryActivity::class.java).apply {
            putExtra("CATEGORY_TITLE", category.title)
            putExtra("CATEGORY_TYPE", category.type)
        }
        startActivity(intent)
    }
}