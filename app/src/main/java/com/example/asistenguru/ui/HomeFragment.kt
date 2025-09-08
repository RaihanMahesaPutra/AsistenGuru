package com.example.asistenguru.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.CategoryListActivity
import com.example.asistenguru.R
import com.example.asistenguru.adapter.CategoryAdapter
import com.example.asistenguru.model.CategoryItem

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi semua View yang diperlukan dari layout
        val rvPromptCategories: RecyclerView = view.findViewById(R.id.rvPromptCategories)
        val rvWebAiCategories: RecyclerView = view.findViewById(R.id.rvWebAiCategories)
        val tvSeeAllPrompts: TextView = view.findViewById(R.id.tvSeeAllPrompts)
        val tvSeeAllWebs: TextView = view.findViewById(R.id.tvSeeAllWebs)
        val btnPromptAi: CardView = view.findViewById(R.id.btnPromptAi)
        val btnWebAi: CardView = view.findViewById(R.id.btnWebAi)

        // 1. Mengisi RecyclerView untuk Kategori Prompt AI
        setupPromptCategories(rvPromptCategories)

        // 2. Mengisi RecyclerView untuk Kategori Web AI
        setupWebAiCategories(rvWebAiCategories)

        // 3. (Opsional) Mengisi RecyclerView untuk Web Favorit
        // setupWebFavorit(view.findViewById(R.id.rvWebFavorit))

        // 4. Mengatur aksi klik untuk navigasi
        setupNavigation(tvSeeAllPrompts, tvSeeAllWebs, btnPromptAi, btnWebAi)
    }

    private fun setupPromptCategories(recyclerView: RecyclerView) {
        val promptCategoryList = getPromptCategories() // Ambil data
        recyclerView.adapter = CategoryAdapter(promptCategoryList) // Set adapter
    }

    private fun setupWebAiCategories(recyclerView: RecyclerView) {
        val webCategoryList = getWebCategories() // Ambil data
        recyclerView.adapter = CategoryAdapter(webCategoryList) // Set adapter
    }

    private fun setupNavigation(
        seeAllPrompts: TextView,
        seeAllWebs: TextView,
        promptButton: CardView,
        webButton: CardView
    ) {
        // Aksi klik untuk "Lihat Semua Prompt"
        seeAllPrompts.setOnClickListener {
            navigateToCategoryList("Prompt AI")
        }

        // Aksi klik untuk tombol utama Prompt AI
        promptButton.setOnClickListener {
            navigateToCategoryList("Prompt AI")
        }

        // Aksi klik untuk "Lihat Semua Web"
        seeAllWebs.setOnClickListener {
            navigateToCategoryList("Web AI")
        }

        // Aksi klik untuk tombol utama Web AI
        webButton.setOnClickListener {
            navigateToCategoryList("Web AI")
        }
    }

    // Fungsi bantuan untuk navigasi agar tidak ada duplikasi kode
    private fun navigateToCategoryList(pageTitle: String) {
        val intent = Intent(requireContext(), CategoryListActivity::class.java).apply {
            putExtra("PAGE_TITLE", pageTitle)
        }
        startActivity(intent)
    }

    // Data contoh untuk Kategori Prompt AI
    // Data contoh untuk Kategori Prompt AI dengan EMOJI
    private fun getPromptCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi Guru", 17, "prompt"),
            CategoryItem("📚", "Bahan Pembelajaran", 6, "prompt"),
            CategoryItem("🧠", "Manajemen & Refleksi", 8, "prompt"),
            CategoryItem("📋", "Evaluasi", 11, "prompt")
        )
    }

    // Data contoh untuk Kategori Web AI dengan EMOJI
    private fun getWebCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi", 5, "web"),
            CategoryItem("✨", "Media Interaktif", 5, "web"),
            CategoryItem("📈", "Evaluasi Pembelajaran", 7, "web"),
            CategoryItem("🎬", "Media Pembelajaran", 7, "web")
        )
    }
}