package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.DetailCategoryActivity
import com.example.asistenguru.R
import com.example.asistenguru.adapter.CategoryDetailAdapter
import com.example.asistenguru.data.DataSource
import com.example.asistenguru.model.CategoryItem
import com.google.android.material.textfield.TextInputEditText

class CategoryListFragment : Fragment(R.layout.fragment_category_list), CategoryDetailAdapter.OnCategoryClickListener {

    private lateinit var adapter: CategoryDetailAdapter
    private val originalList = mutableListOf<CategoryItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Views dari layout yang benar
        val pageTitleView: TextView = view.findViewById(R.id.tvPageTitle)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvCategories)
        val searchIcon: ImageView = view.findViewById(R.id.ivSearch)

        // Ambil judul dari argumen navigasi
        val pageTitle = arguments?.getString("pageTitle") ?: "Kategori"
        pageTitleView.text = pageTitle

        // Ambil data yang sesuai
        val initialItems = when(pageTitle) {
            "Prompt AI" -> DataSource.getPromptCategories()
            "Web AI" -> DataSource.getWebCategories()
            "Database" -> {
                // TODO: Ganti dengan data database jika sudah ada
                emptyList()
            }
            else -> emptyList()
        }

        originalList.clear()
        originalList.addAll(initialItems)

        // Setup Adapter
        adapter = CategoryDetailAdapter(ArrayList(originalList), this)
        recyclerView.adapter = adapter

        // Fungsikan ikon pencarian
        searchIcon.setOnClickListener {
            showSearchDialog()
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
            putExtra("CATEGORY_TITLE", category.title)
            putExtra("CATEGORY_TYPE", category.type)
        }
        startActivity(intent)
    }
}