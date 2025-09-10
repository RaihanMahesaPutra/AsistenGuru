package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.CategoryListActivity
import com.example.asistenguru.DetailCategoryActivity
import com.example.asistenguru.R
import com.example.asistenguru.adapter.CategoryAdapter
import com.example.asistenguru.adapter.WebFavoritAdapter
import com.example.asistenguru.data.DataSource
import com.example.asistenguru.model.CategoryItem
import com.example.asistenguru.model.WebFavorit

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.OnCategoryClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi semua View
        val rvPromptCategories: RecyclerView = view.findViewById(R.id.rvPromptCategories)
        val rvWebAiCategories: RecyclerView = view.findViewById(R.id.rvWebAiCategories)
        val tvSeeAllPrompts: TextView = view.findViewById(R.id.tvSeeAllPrompts)
        val rvWebFavorit: RecyclerView = view.findViewById(R.id.rvWebFavorit)
        val tvSeeAllWebs: TextView = view.findViewById(R.id.tvSeeAllWebs)
        val btnPromptAi: CardView = view.findViewById(R.id.btnPromptAi)
        val btnWebAi: CardView = view.findViewById(R.id.btnWebAi)

        setupWebFavorit(rvWebFavorit)
        setupPromptCategories(view.findViewById(R.id.rvPromptCategories))
        setupWebAiCategories(view.findViewById(R.id.rvWebAiCategories))
        setupNavigation(view)
        updateStatistics(view)
    }

    // --- FUNGSI BARU UNTUK WEB FAVORIT ---
    private fun setupWebFavorit(recyclerView: RecyclerView) {
        val favoritList = getWebFavoritData()
        recyclerView.adapter = WebFavoritAdapter(favoritList)
    }

    // --- FUNGSI BARU UNTUK DATA WEB FAVORIT ---
    private fun getWebFavoritData(): List<WebFavorit> {
        return listOf(
            WebFavorit("🎨", "Canva", "Situs Web", "https://www.canva.com"),
            WebFavorit("🤖", "ChatGPT", "Situs Web", "https://chat.openai.com"),
            WebFavorit("📈", "Quizizz", "Situs Web", "https://quizizz.com"),
            WebFavorit("🎬", "YouTube", "Situs Web", "https://youtube.com"),
            WebFavorit("📝", "Google Forms", "Situs Web", "https://docs.google.com/forms/")
        )
    }

    /**
     * Fungsi baru untuk mengambil data, menghitung, dan menampilkannya
     * di kartu statistik secara otomatis.
     */
    private fun updateStatistics(view: View) {
        val tvStatPromptCategories: TextView = view.findViewById(R.id.tvStatPromptCategories)
        val tvStatTotalPrompts: TextView = view.findViewById(R.id.tvStatTotalPrompts)
        val tvStatWebCategories: TextView = view.findViewById(R.id.tvStatWebCategories)
        val tvStatTotalWebs: TextView = view.findViewById(R.id.tvStatTotalWebs)

        // Mengambil data dari DataSource
        val promptCategories = DataSource.getPromptCategories()
        val totalPrompts = promptCategories.sumOf { it.itemCount }

        val webCategories = DataSource.getWebCategories()
        val totalWebs = webCategories.sumOf { it.itemCount }

        tvStatPromptCategories.text = "Kategori Prompt: ${promptCategories.size} kategori"
        tvStatTotalPrompts.text = "Total Prompt AI: $totalPrompts prompt"
        tvStatWebCategories.text = "Kategori Web AI: ${webCategories.size} kategori"
        tvStatTotalWebs.text = "Total Web AI: $totalWebs web"
    }

    private fun setupPromptCategories(recyclerView: RecyclerView) {
        recyclerView.adapter = CategoryAdapter(DataSource.getPromptCategories(), this)
    }

    private fun setupWebAiCategories(recyclerView: RecyclerView) {
        recyclerView.adapter = CategoryAdapter(DataSource.getWebCategories(), this)
    }

    override fun onCategoryClick(category: CategoryItem) {
        val intent = Intent(requireContext(), DetailCategoryActivity::class.java).apply {
            putExtra("CATEGORY_TITLE", category.title)
            putExtra("CATEGORY_TYPE", category.type)
        }
        startActivity(intent)
    }

    private fun setupNavigation(view: View) {
        val tvSeeAllPrompts: TextView = view.findViewById(R.id.tvSeeAllPrompts)
        val tvSeeAllWebs: TextView = view.findViewById(R.id.tvSeeAllWebs)
        val promptButton: CardView = view.findViewById(R.id.btnPromptAi)
        val webButton: CardView = view.findViewById(R.id.btnWebAi)

        tvSeeAllPrompts.setOnClickListener { navigateToCategoryList("Prompt AI") }
        promptButton.setOnClickListener { navigateToCategoryList("Prompt AI") }
        tvSeeAllWebs.setOnClickListener { navigateToCategoryList("Web AI") }
        webButton.setOnClickListener { navigateToCategoryList("Web AI") }
    }

    private fun navigateToCategoryList(pageTitle: String) {
        val intent = Intent(requireContext(), CategoryListActivity::class.java).apply {
            putExtra("PAGE_TITLE", pageTitle)
        }
        startActivity(intent)
    }
}