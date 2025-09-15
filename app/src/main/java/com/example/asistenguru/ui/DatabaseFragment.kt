package com.example.asistenguru.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.adapter.GlossaryAdapter
import com.example.asistenguru.data.DataSource

class DatabaseFragment : Fragment(R.layout.fragment_database) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvGlossary)
        recyclerView.adapter = GlossaryAdapter(DataSource.getGlossaryItems())
    }
}