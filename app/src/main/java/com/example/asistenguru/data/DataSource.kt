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
        // itemCount dihitung OTOMATIS dari jumlah item detail di bawah
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
        // Di sinilah Anda mengisi daftar website untuk setiap kategori
        return when (category) {
            "Administrasi" -> listOf(
                DetailItem.WebAi(R.drawable.logo_googlesheets, "Google Sheets", "Aplikasi spreadsheet online untuk rekap nilai.", "https://docs.google.com/spreadsheets/")
            )
            "Media Interaktif" -> listOf(
                DetailItem.WebAi(R.drawable.logo_canva, "Canva", "Platform desain grafis untuk membuat presentasi.", "https://www.canva.com"),
                DetailItem.WebAi(R.drawable.logo_mentimenter, "Mentimeter", "Alat untuk presentasi interaktif dengan polling.", "https://www.mentimeter.com")
            )
            "Evaluasi Pembelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_quizizz, "Quizizz", "Platform untuk membuat kuis gamifikasi.", "https://quizizz.com"),
                DetailItem.WebAi(R.drawable.logo_googleforms, "Google Forms", "Alat serbaguna untuk survei dan kuis.", "https://docs.google.com/forms/")
            )
            "Media Pembelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_youtube, "YouTube", "Platform berbagi video untuk materi belajar.", "https://youtube.com")
            )
            else -> emptyList()
        }
    }
}