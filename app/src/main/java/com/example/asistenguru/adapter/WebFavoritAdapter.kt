package com.example.asistenguru.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.model.WebFavorit

class WebFavoritAdapter(private val items: List<WebFavorit>) :
    RecyclerView.Adapter<WebFavoritAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: TextView = view.findViewById(R.id.tvFavoritIcon)
        val name: TextView = view.findViewById(R.id.tvFavoritName)
        val description: TextView = view.findViewById(R.id.tvFavoritDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_web_favorit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.icon.text = item.logoEmoji
        holder.name.text = item.name
        holder.description.text = item.description

        // Menambahkan click listener untuk membuka link
        holder.itemView.setOnClickListener {
            openUrl(it.context, item.url)
        }
    }

    override fun getItemCount() = items.size

    // Fungsi helper untuk membuka URL di browser
    private fun openUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Tangani jika tidak ada browser atau URL tidak valid
            Toast.makeText(context, "Tidak bisa membuka link", Toast.LENGTH_SHORT).show()
        }
    }
}