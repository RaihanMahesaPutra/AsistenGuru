package com.example.asistenguru.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.model.GlossaryItem

class GlossaryAdapter(private val items: List<GlossaryItem>) :
    RecyclerView.Adapter<GlossaryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val term: TextView = view.findViewById(R.id.tvTerm)
        val definition: TextView = view.findViewById(R.id.tvDefinition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_glossary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.term.text = item.term
        holder.definition.text = item.definition
    }

    override fun getItemCount() = items.size
}