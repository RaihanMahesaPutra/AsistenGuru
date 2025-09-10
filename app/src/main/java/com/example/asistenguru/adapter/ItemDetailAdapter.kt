package com.example.asistenguru.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView // Import ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asistenguru.R
import com.example.asistenguru.model.DetailItem

class ItemDetailAdapter(private val items: List<DetailItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_PROMPT = 1
        private const val TYPE_WEB_AI = 2
    }

    class PromptViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.tvPromptContent)
    }

    class WebAiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ImageView = view.findViewById(R.id.ivWebLogo) // DIUBAH: Referensi ke ImageView
        val name: TextView = view.findViewById(R.id.tvWebName)
        val description: TextView = view.findViewById(R.id.tvWebDescription)
        val visitButton: Button = view.findViewById(R.id.btnVisit)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DetailItem.Prompt -> TYPE_PROMPT
            is DetailItem.WebAi -> TYPE_WEB_AI
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PROMPT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_prompt_detail, parent, false)
                PromptViewHolder(view)
            }
            TYPE_WEB_AI -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_web_ai_detail, parent, false)
                WebAiViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is DetailItem.Prompt -> {
                (holder as PromptViewHolder).content.text = item.content
            }
            is DetailItem.WebAi -> {
                val webAiHolder = holder as WebAiViewHolder
                webAiHolder.logo.setImageResource(item.logoResId) // DIUBAH: Mengatur gambar dari resource ID
                webAiHolder.name.text = item.name
                webAiHolder.description.text = item.description
                webAiHolder.visitButton.setOnClickListener {
                    openUrl(it.context, item.url)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    private fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    }
}