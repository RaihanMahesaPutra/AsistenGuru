package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.CategoryListViewModel
import com.example.asistenguru.DetailCategoryActivity
import com.example.asistenguru.R
import com.example.asistenguru.adapter.CategoryDetailAdapter
import com.example.asistenguru.model.CategoryItem
import com.google.android.material.textfield.TextInputEditText

class CategoryListFragment : Fragment(R.layout.fragment_category_list), CategoryDetailAdapter.OnCategoryClickListener {

    // Gunakan ViewModel yang baru
    private val viewModel: CategoryListViewModel by viewModels()
    private lateinit var adapter: CategoryDetailAdapter
    private val originalList = mutableListOf<CategoryItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pageTitleView: TextView = view.findViewById(R.id.tvPageTitle)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvCategories)
        val searchIcon: ImageView = view.findViewById(R.id.ivSearch)
        val progressOverlay: View = view.findViewById(R.id.progressOverlay)
        val contentLayout: View = view.findViewById(R.id.contentLayout)

        // Ambil judul dari argumen navigasi
        val pageTitle = arguments?.getString("pageTitle") ?: "Kategori"
        pageTitleView.text = pageTitle

        // Setup Adapter dengan list kosong
        adapter = CategoryDetailAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter

        // Tentukan tipe kategori berdasarkan judul halaman
        val categoryType = when(pageTitle) {
            "Prompt AI" -> "prompt"
            "Web AI" -> "web"
            else -> ""
        }

        // Mulai ambil data dari ViewModel jika tipe kategori valid
        if (categoryType.isNotEmpty()) {
            viewModel.fetchCategoriesByType(categoryType)
        }

        // Amati perubahan dari ViewModel
        observeViewModel(progressOverlay, contentLayout)

        searchIcon.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun observeViewModel(progressOverlay: View, contentLayout: View) {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            // PERBAIKI DI SINI: Tambahkan "it.id" sebagai parameter pertama
            val categoryItems = categories.map {
                CategoryItem(it.id, it.iconEmoji, it.name, it.itemCount.toInt(), it.type)
            }
            originalList.clear()
            originalList.addAll(categoryItems)
            adapter.filterList(categoryItems)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressOverlay.visibility = View.VISIBLE
                contentLayout.visibility = View.GONE
            } else {
                progressOverlay.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun showSearchDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.App_AlertDialogTheme)
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_search, null)
        val input = dialogView.findViewById<TextInputEditText>(R.id.etSearchQuery)

        builder.setTitle("Cari Kategori")
        builder.setView(dialogView)

        builder.setPositiveButton("Cari") { dialog, _ ->
            filter(input.text.toString())
            dialog.dismiss()
        }
        builder.setNegativeButton("Batal") { dialog, _ -> dialog.cancel() }
        builder.setNeutralButton("Reset") { dialog, _ ->
            filter("")
            dialog.dismiss()
        }
        builder.show()
    }

    private fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.title.contains(query, ignoreCase = true) }
        }
        adapter.filterList(filteredList)
    }

    override fun onCategoryClick(category: CategoryItem) {
        val intent = Intent(requireContext(), DetailCategoryActivity::class.java).apply {
            // DIUBAH: Kirim ID, bukan title
            putExtra("CATEGORY_ID", category.id)
            putExtra("CATEGORY_TITLE", category.title)
        }
        startActivity(intent)
    }
}