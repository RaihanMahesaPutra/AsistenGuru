package com.example.asistenguru

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.adapter.ItemDetailAdapter
import com.example.asistenguru.data.DataSource // Impor DataSource
import com.example.asistenguru.model.DetailItem

class DetailCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val recyclerView: RecyclerView = findViewById(R.id.rvItems)

        val categoryTitle = intent.getStringExtra("CATEGORY_TITLE") ?: "Detail"
        val categoryType = intent.getStringExtra("CATEGORY_TYPE") ?: "prompt"
        toolbar.title = categoryTitle

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Mengambil data dari DataSource
        val items = getItemsForCategory(categoryTitle, categoryType)
        recyclerView.adapter = ItemDetailAdapter(items)
    }

    private fun getItemsForCategory(category: String, type: String): List<DetailItem> {
        if (type == "web") {
            return DataSource.getWebAiItems(category)
        }
        return DataSource.getPromptItems(category)
    }
}