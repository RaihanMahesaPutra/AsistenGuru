package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.*
import com.example.asistenguru.adapter.CategoryAdapter
import com.example.asistenguru.adapter.WebFavoritAdapter
import com.example.asistenguru.model.Category
import com.example.asistenguru.model.CategoryItem
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.OnCategoryClickListener {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var promptCategoryAdapter: CategoryAdapter
    private lateinit var webAiCategoryAdapter: CategoryAdapter
    private lateinit var favoritAdapter: WebFavoritAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Setup Toolbar agar bisa pakai menu logout
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        // ✅ RecyclerView & View Binding
        val rvPromptCategories: RecyclerView = view.findViewById(R.id.rvPromptCategories)
        val rvWebAiCategories: RecyclerView = view.findViewById(R.id.rvWebAiCategories)
        val tvSeeAllPrompts: TextView = view.findViewById(R.id.tvSeeAllPrompts)
        val tvSeeAllWebs: TextView = view.findViewById(R.id.tvSeeAllWebs)
        val progressOverlay: View = view.findViewById(R.id.progressOverlay)
        val contentLayout: View = view.findViewById(R.id.contentLayout)

        setupAdapters()
        rvPromptCategories.adapter = promptCategoryAdapter
        rvWebAiCategories.adapter = webAiCategoryAdapter

        setupNavigation(tvSeeAllPrompts, tvSeeAllWebs)

        observeViewModel(view, progressOverlay, contentLayout)
    }

    // ✅ Observasi ViewModel
    private fun observeViewModel(view: View, progressOverlay: View, contentLayout: View) {
        homeViewModel.promptCategories.observe(viewLifecycleOwner) { categories ->
            val categoryItems = categories.map { category ->
                CategoryItem(
                    category.id,
                    category.iconEmoji,
                    category.name,
                    category.itemCount.toInt(),
                    category.type
                )
            }
            promptCategoryAdapter.updateData(categoryItems)
            updatePromptStatistics(view, categories)
        }

        homeViewModel.webAiCategories.observe(viewLifecycleOwner) { categories ->
            val categoryItems = categories.map { category ->
                CategoryItem(
                    category.id,
                    category.iconEmoji,
                    category.name,
                    category.itemCount.toInt(),
                    category.type
                )
            }
            webAiCategoryAdapter.updateData(categoryItems)
            updateWebAiStatistics(view, categories)
        }

        homeViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            favoritAdapter = WebFavoritAdapter(favorites)
            view.findViewById<RecyclerView>(R.id.rvWebFavorit).adapter = favoritAdapter
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressOverlay.visibility = View.VISIBLE
                contentLayout.visibility = View.GONE
            } else {
                progressOverlay.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            }
        }
    }

    // ✅ Setup Adapter
    private fun setupAdapters() {
        promptCategoryAdapter = CategoryAdapter(mutableListOf(), this)
        webAiCategoryAdapter = CategoryAdapter(mutableListOf(), this)
        favoritAdapter = WebFavoritAdapter(emptyList())
    }

    // ✅ Statistik Prompt
    private fun updatePromptStatistics(view: View, promptCategories: List<Category>) {
        val tvStatPromptCategories: TextView = view.findViewById(R.id.tvStatPromptCategories)
        val tvStatTotalPrompts: TextView = view.findViewById(R.id.tvStatTotalPrompts)
        val totalPrompts = promptCategories.sumOf { it.itemCount }

        tvStatPromptCategories.text = "Kategori Prompt: ${promptCategories.size} kategori"
        tvStatTotalPrompts.text = "Total Prompt AI: $totalPrompts prompt"
    }

    // ✅ Statistik Web AI
    private fun updateWebAiStatistics(view: View, webCategories: List<Category>) {
        val tvStatWebCategories: TextView = view.findViewById(R.id.tvStatWebCategories)
        val tvStatTotalWebs: TextView = view.findViewById(R.id.tvStatTotalWebs)
        val totalWebs = webCategories.sumOf { it.itemCount }

        tvStatWebCategories.text = "Kategori Web AI: ${webCategories.size} kategori"
        tvStatTotalWebs.text = "Total Web AI: $totalWebs web"
    }

    // ✅ Klik kategori -> buka DetailCategoryActivity
    override fun onCategoryClick(category: CategoryItem) {
        val intent = Intent(requireContext(), DetailCategoryActivity::class.java).apply {
            putExtra("CATEGORY_ID", category.id)
            putExtra("CATEGORY_TITLE", category.title)
        }
        startActivity(intent)
    }

    // ✅ Navigasi Lihat Semua
    private fun setupNavigation(seeAllPrompts: TextView, seeAllWebs: TextView) {
        seeAllPrompts.setOnClickListener {
            (activity as? MainActivity)?.switchToTab(R.id.promptAiFragment)
        }

        seeAllWebs.setOnClickListener {
            (activity as? MainActivity)?.switchToTab(R.id.webAiFragment)
        }
    }

    // ✅ Menu Toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
