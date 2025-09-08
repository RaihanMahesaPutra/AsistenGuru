package com.example.asistenguru.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // ImageView sudah tidak dipakai
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.model.CategoryItem

class CategoryDetailAdapter(private val items: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // DIUBAH: 'icon' sekarang adalah TextView
        val icon: TextView = view.findViewById(R.id.tvCategoryIcon)
        val title: TextView = view.findViewById(R.id.tvCategoryTitle)
        val count: TextView = view.findViewById(R.id.tvItemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        // DIUBAH: Menggunakan .text dan properti iconEmoji
        holder.icon.text = item.iconEmoji
        holder.title.text = item.title
        holder.count.text = "${item.itemCount} ${item.type}"
    }

    override fun getItemCount() = items.size
}