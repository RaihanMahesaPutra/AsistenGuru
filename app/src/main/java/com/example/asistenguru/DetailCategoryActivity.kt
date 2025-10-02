package com.example.asistenguru

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.ItemDetailAdapter
import com.example.asistenguru.model.DetailItem
import com.google.android.material.search.SearchBar

class DetailCategoryActivity : AppCompatActivity() {

    private val viewModel: DetailCategoryViewModel by viewModels()
    private lateinit var adapter: ItemDetailAdapter
    private val originalList = mutableListOf<DetailItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        // Inisialisasi Views
        val searchBar: SearchBar = findViewById(R.id.search_bar_detail)
        val recyclerView: RecyclerView = findViewById(R.id.rvItems)
        val progressOverlay: View = findViewById(R.id.progressOverlay)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val contentLayout: View = findViewById(R.id.contentLayout)

        // Ambil data dari Intent
        val categoryId = intent.getStringExtra("CATEGORY_ID")
        val categoryTitle = intent.getStringExtra("CATEGORY_TITLE") ?: "Detail"
        searchBar.setText(categoryTitle)

        // Setup Adapter dengan list kosong
        adapter = ItemDetailAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Mulai ambil data dari ViewModel jika ID tidak null
        if (categoryId != null) {
            viewModel.fetchItemsForCategory(categoryId)
        }

        // Amati perubahan dari ViewModel
        observeViewModel(progressOverlay, contentLayout, recyclerView)

        searchBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun observeViewModel(progressOverlay: View, contentLayout: View, recyclerView: RecyclerView) {
        viewModel.items.observe(this) { items ->
            val detailItems = items.map { item ->
                if (item.url.isNotEmpty()) {
                    DetailItem.WebAi(0, item.name, item.description, item.url, item.imageUrl)
                } else {
                    DetailItem.Prompt(item.name)
                }
            }
            originalList.clear()
            originalList.addAll(detailItems)
            adapter.filterList(detailItems)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressOverlay.visibility = View.VISIBLE
                contentLayout.visibility = View.GONE
            } else {
                progressOverlay.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            }
        }
    }
}
