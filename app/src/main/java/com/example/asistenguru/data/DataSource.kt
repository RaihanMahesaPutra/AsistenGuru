package com.example.asistenguru.data

import com.example.asistenguru.R
import com.example.asistenguru.model.CategoryItem
import com.example.asistenguru.model.DetailItem

object DataSource {

    // Sumber data untuk Kategori Prompt
    fun getPromptCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi Guru", getPromptItems("Administrasi Guru").size, "prompt"),
            CategoryItem("📚", "Bahan Pembelajaran", getPromptItems("Bahan Pembelajaran").size, "prompt"),
            CategoryItem("🧠", "Manajemen & Refleksi", getPromptItems("Manajemen & Refleksi").size, "prompt"),
            CategoryItem("📋", "Evaluasi", getPromptItems("Evaluasi").size, "prompt")
        )
    }

    // Sumber data untuk Kategori Web AI
    fun getWebCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi", getWebAiItems("Administrasi").size, "web"),
            CategoryItem("✨", "Media Interaktif", getWebAiItems("Media Interaktif").size, "web"),
            CategoryItem("📈", "Evaluasi Pembelajaran", getWebAiItems("Evaluasi Pembelajaran").size, "web"),
            CategoryItem("🎬", "Media Pembelajaran", getWebAiItems("Media Pembelajaran").size, "web")
        )
    }

    // Sumber data DETAIL untuk Prompt
    fun getPromptItems(category: String): List<DetailItem.Prompt> {
        return when (category) {
            "Administrasi Guru" -> listOf(
                DetailItem.Prompt("Buatkan RPP untuk mata pelajaran Matematika kelas 4."),
                DetailItem.Prompt("Tuliskan contoh surat izin tidak masuk untuk siswa."),
                DetailItem.Prompt("Buat daftar piket kelas untuk satu minggu.")
            )
            "Bahan Pembelajaran" -> listOf(
                DetailItem.Prompt("Jelaskan proses fotosintesis secara sederhana."),
                DetailItem.Prompt("Buat ringkasan tentang Kerajaan Majapahit."),
                DetailItem.Prompt("Buat 5 soal esai tentang rotasi bumi.")
            )
            "Manajemen & Refleksi" -> listOf(
                DetailItem.Prompt("Berikan contoh refleksi diri setelah mengajar."),
                DetailItem.Prompt("Buat checklist manajemen kelas harian.")
            )
            "Evaluasi" -> listOf(
                DetailItem.Prompt("Buat rubrik penilaian untuk presentasi siswa."),
                DetailItem.Prompt("Tuliskan 10 soal benar-salah tentang pahlawan nasional.")
            )
            else -> emptyList()
        }
    }

    // Sumber data DETAIL untuk Web AI
    fun getWebAiItems(category: String): List<DetailItem.WebAi> {
        return when (category) {
            "Media Interaktif" -> listOf(
                DetailItem.WebAi(R.drawable.logo_canva, "Canva", "Platform desain grafis.", "https://www.canva.com"),
                DetailItem.WebAi(R.drawable.logo_mentimenter, "Mentimeter", "Alat untuk presentasi interaktif.", "https://www.mentimeter.com")
            )
            "Evaluasi Pembelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_quizizz, "Quizizz", "Platform untuk kuis gamifikasi.", "https://quizizz.com"),
                DetailItem.WebAi(R.drawable.logo_googleforms, "Google Forms", "Alat untuk survei dan kuis.", "https://docs.google.com/forms/")
            )
            else -> emptyList()
        }
    }
}