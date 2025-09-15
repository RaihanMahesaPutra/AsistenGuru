package com.example.asistenguru

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.ItemDetailAdapter
import com.example.asistenguru.data.DataSource
import com.example.asistenguru.model.DetailItem
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

class DetailCategoryActivity : AppCompatActivity() {

    private lateinit var adapter: ItemDetailAdapter
    private val originalList = mutableListOf<DetailItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        // Inisialisasi Views
        val searchBar: SearchBar = findViewById(R.id.search_bar_detail)
        val searchView: SearchView = findViewById(R.id.search_view_detail)
        val recyclerView: RecyclerView = findViewById(R.id.rvItems)
        // DIUBAH: Ambil referensi ke RecyclerView hasil pencarian
        val searchRecyclerView: RecyclerView = findViewById(R.id.rvSearchResults)

        // Ambil data awal
        val categoryTitle = intent.getStringExtra("CATEGORY_TITLE") ?: "Detail"
        val categoryType = intent.getStringExtra("CATEGORY_TYPE") ?: "prompt"
        searchBar.setText(categoryTitle)

        val initialItems = getItemsForCategory(categoryTitle, categoryType)
        originalList.addAll(initialItems)

        // Setup Adapter
        adapter = ItemDetailAdapter(ArrayList(originalList))
        // DIUBAH: Pasang SATU ADAPTER yang sama ke DUA RecyclerView
        recyclerView.adapter = adapter
        searchRecyclerView.adapter = adapter

        // Setup Fungsionalitas Pencarian
        searchView.setupWithSearchBar(searchBar)
        searchView
            .editText
            .addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filter(s.toString())
                }
                override fun afterTextChanged(s: Editable?) {}
            })

        searchBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun filter(query: String) {
        val filteredList = mutableListOf<DetailItem>()
        if (query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            for (item in originalList) {
                val match = when (item) {
                    is DetailItem.Prompt -> item.content.contains(query, ignoreCase = true)
                    is DetailItem.WebAi -> item.name.contains(query, ignoreCase = true) || item.description.contains(query, ignoreCase = true)
                }
                if (match) {
                    filteredList.add(item)
                }
            }
        }
        adapter.filterList(filteredList)
    }

    private fun getItemsForCategory(category: String, type: String): List<DetailItem> {
        if (type == "web") {
            return DataSource.getWebAiItems(category)
        }
        return DataSource.getPromptItems(category)
    }
}