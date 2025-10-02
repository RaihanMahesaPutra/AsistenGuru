package com.example.asistenguru.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.DatabaseViewModel
import com.example.asistenguru.R
import com.example.asistenguru.adapter.GlossaryAdapter

class DatabaseFragment : Fragment(R.layout.fragment_database) {

    private val viewModel: DatabaseViewModel by viewModels()
    private lateinit var glossaryAdapter: GlossaryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvGlossary)

        // Setup adapter dengan list kosong
        glossaryAdapter = GlossaryAdapter(emptyList())
        recyclerView.adapter = glossaryAdapter

        // Amati data dari ViewModel
        viewModel.glossaryItems.observe(viewLifecycleOwner) { items ->
            // Update adapter dengan data baru dari Firestore
            glossaryAdapter = GlossaryAdapter(items)
            recyclerView.adapter = glossaryAdapter
        }
    }
}