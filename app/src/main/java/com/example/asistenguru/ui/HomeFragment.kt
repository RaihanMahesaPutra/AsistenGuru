package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        // Inisialisasi semua View yang ada di layout
        val rvWebFavorit: RecyclerView = view.findViewById(R.id.rvWebFavorit)
        val rvPromptCategories: RecyclerView = view.findViewById(R.id.rvPromptCategories)
        val rvWebAiCategories: RecyclerView = view.findViewById(R.id.rvWebAiCategories)
        val tvSeeAllPrompts: TextView = view.findViewById(R.id.tvSeeAllPrompts)
        val tvSeeAllWebs: TextView = view.findViewById(R.id.tvSeeAllWebs)

        // Setup semua RecyclerView
        setupWebFavorit(rvWebFavorit)
        setupPromptCategories(rvPromptCategories)
        setupWebAiCategories(rvWebAiCategories)

        // Setup Navigasi untuk tombol "Lihat Semua"
        setupNavigation(tvSeeAllPrompts, tvSeeAllWebs)

        // Update Statistik
        updateStatistics(view)
    }

    private fun setupWebFavorit(recyclerView: RecyclerView) {
        recyclerView.adapter = WebFavoritAdapter(DataSource.getWebFavoritData())
    }

    private fun getWebFavoritData(): List<WebFavorit> {
        // Data ini dipindah ke DataSource untuk kerapian
        return DataSource.getWebFavoritData()
    }

    private fun updateStatistics(view: View) {
        val tvStatPromptCategories: TextView = view.findViewById(R.id.tvStatPromptCategories)
        val tvStatTotalPrompts: TextView = view.findViewById(R.id.tvStatTotalPrompts)
        val tvStatWebCategories: TextView = view.findViewById(R.id.tvStatWebCategories)
        val tvStatTotalWebs: TextView = view.findViewById(R.id.tvStatTotalWebs)

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

    // Fungsi setupNavigation sekarang hanya untuk tombol "Lihat Semua"
    private fun setupNavigation(seeAllPrompts: TextView, seeAllWebs: TextView) {
        seeAllPrompts.setOnClickListener { navigateToCategoryList("Prompt AI") }
        seeAllWebs.setOnClickListener { navigateToCategoryList("Web AI") }
    }

    private fun navigateToCategoryList(pageTitle: String) {
        // Cara manual tanpa Safe Args
        val bundle = Bundle().apply {
            putString("pageTitle", pageTitle)
        }
        findNavController().navigate(R.id.action_homeFragment_to_categoryListFragment, bundle)
    }
}
