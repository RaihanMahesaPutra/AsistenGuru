package com.example.asistenguru.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView // Impor ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.model.WebFavorit

class WebFavoritAdapter(private val items: List<WebFavorit>) :
    RecyclerView.Adapter<WebFavoritAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ImageView = view.findViewById(R.id.ivFavoritLogo) // DIUBAH: Referensi ke ImageView
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
        holder.logo.setImageResource(item.logoResId) // DIUBAH: Mengatur gambar dari resource ID
        holder.name.text = item.name
        holder.description.text = item.description

        holder.itemView.setOnClickListener {
            openUrl(it.context, item.url)
        }
    }

    override fun getItemCount() = items.size

    private fun openUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Tidak bisa membuka link", Toast.LENGTH_SHORT).show()
        }
    }
}