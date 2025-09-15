package com.example.asistenguru.data

import com.example.asistenguru.R
import com.example.asistenguru.model.CategoryItem
import com.example.asistenguru.model.DetailItem
import com.example.asistenguru.model.GlossaryItem
import com.example.asistenguru.model.WebFavorit

object DataSource {

    // Sumber data untuk Kategori Prompt
    fun getPromptCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi Guru", getPromptItems("Administrasi Guru").size, "prompt"),
            CategoryItem("📚", "Bahan Pembelajaran", getPromptItems("Bahan Pembelajaran").size, "prompt"),
            CategoryItem("🧠", "Manajemen & Refleksi", getPromptItems("Manajemen & Refleksi").size, "prompt"),
            CategoryItem("📋", "Evaluasi", getPromptItems("Evaluasi").size, "prompt"),
            // KATEGORI PROMPT BARU
            CategoryItem("💡", "Pengembangan", getPromptItems("Pengembangan Profesional").size, "prompt"),
            CategoryItem("💬", "Komunikasi Orang Tua", getPromptItems("Komunikasi Orang Tua").size, "prompt")
        )
    }

    fun getWebCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem("📄", "Administrasi", getWebAiItems("Administrasi").size, "web"),
            CategoryItem("✨", "Media Interaktif", getWebAiItems("Media Interaktif").size, "web"),
            CategoryItem("📈", "Evaluasi Pembelajaran", getWebAiItems("Evaluasi Pembelajaran").size, "web"),
            CategoryItem("🎬", "Media Pembelajaran", getWebAiItems("Media Pembelajaran").size, "web"),
            // KATEGORI WEB BARU
            CategoryItem("⚙️", "Generator Soal", getWebAiItems("Generator Soal").size, "web"),
            CategoryItem("🗓️", "Perencana Pelajaran", getWebAiItems("Perencana Pelajaran").size, "web")
        )
    }

    // --- DETAIL ITEM PROMPT ---
    fun getPromptItems(category: String): List<DetailItem.Prompt> {
        return when (category) {
            "Administrasi Guru" -> listOf(
                DetailItem.Prompt("Buatkan draf notulen untuk rapat dewan guru mingguan."),
                DetailItem.Prompt("Tuliskan checklist observasi kelas untuk supervisi akademik."),
                DetailItem.Prompt("Buat program kerja ekstrakurikuler Pramuka untuk satu semester."),
                DetailItem.Prompt("Draf email kepada kepala sekolah untuk pengajuan sarana laboratorium IPA."),
                DetailItem.Prompt("Buatkan format laporan bulanan wali kelas yang mencakup absensi dan perkembangan siswa."),
                DetailItem.Prompt("Tuliskan poin-poin penting untuk penyusunan tata tertib perpustakaan."),
                DetailItem.Prompt("Buat jadwal piket guru selama periode ujian sekolah."),
                DetailItem.Prompt("Draf surat undangan resmi untuk acara pentas seni sekolah."),
                DetailItem.Prompt("Rancang agenda untuk rapat awal tahun ajaran baru bersama guru-guru."),
                DetailItem.Prompt("Buatkan template sertifikat penghargaan untuk siswa berprestasi.")
            )
            "Bahan Pembelajaran" -> listOf(
                DetailItem.Prompt("Jelaskan konsep rantai makanan dengan analogi sederhana untuk kelas 4 SD."),
                DetailItem.Prompt("Buat ringkasan 5 paragraf tentang Perang Dunia II untuk siswa SMA."),
                DetailItem.Prompt("Tuliskan 10 kosakata baru tentang cuaca dalam Bahasa Inggris beserta contoh kalimatnya."),
                DetailItem.Prompt("Buat naskah drama singkat tentang proklamasi kemerdekaan Indonesia."),
                DetailItem.Prompt("Rancang sebuah studi kasus tentang dampak pencemaran lingkungan di sungai."),
                DetailItem.Prompt("Jelaskan perbedaan antara sel hewan dan sel tumbuhan dalam format tabel."),
                DetailItem.Prompt("Buat soal cerita matematika tentang untung dan rugi untuk siswa SMP."),
                DetailItem.Prompt("Tuliskan penjelasan tentang 3 jenis gaya (gesek, gravitasi, magnet) untuk anak-anak."),
                DetailItem.Prompt("Buat peta konsep tentang sistem tata surya."),
                DetailItem.Prompt("Draf materi presentasi tentang pentingnya daur ulang sampah.")
            )
            "Manajemen & Refleksi" -> listOf(
                DetailItem.Prompt("Berikan 5 strategi untuk menangani siswa yang suka mengobrol di kelas."),
                DetailItem.Prompt("Tuliskan contoh kalimat pembuka pelajaran yang menarik dan membangkitkan semangat."),
                DetailItem.Prompt("Buat checklist refleksi harian untuk saya sebagai guru: apa yang berhasil, apa yang perlu diperbaiki."),
                DetailItem.Prompt("Bagaimana cara memberikan umpan balik yang konstruktif tanpa menyakiti perasaan siswa?"),
                DetailItem.Prompt("Rancang sistem penghargaan (reward system) sederhana untuk meningkatkan partisipasi siswa."),
                DetailItem.Prompt("Berikan ide ice-breaking selama 5 menit untuk memulai pelajaran Sejarah."),
                DetailItem.Prompt("Bagaimana cara mengatur ulang tata letak bangku kelas untuk memaksimalkan kerja kelompok?"),
                DetailItem.Prompt("Tuliskan poin-poin refleksi akhir semester mengenai metode mengajar saya."),
                DetailItem.Prompt("Berikan tips untuk membangun hubungan yang positif dengan siswa yang pendiam."),
                DetailItem.Prompt("Buat draf aturan kelas yang singkat, jelas, dan positif.")
            )
            "Evaluasi" -> listOf(
                DetailItem.Prompt("Buat 10 soal pilihan ganda tentang sistem pernapasan manusia."),
                DetailItem.Prompt("Rancang soal esai yang menguji kemampuan analisis siswa tentang novel 'Laskar Pelangi'."),
                DetailItem.Prompt("Buatkan rubrik penilaian untuk proyek poster tentang pahlawan nasional."),
                DetailItem.Prompt("Berikan 5 pertanyaan terbuka untuk memulai diskusi kelas tentang globalisasi."),
                DetailItem.Prompt("Buat kuis singkat 'Benar atau Salah' tentang ibukota negara-negara di Asia Tenggara."),
                DetailItem.Prompt("Rancang tugas portofolio untuk mata pelajaran Seni Budaya selama satu semester."),
                DetailItem.Prompt("Bagaimana cara menilai partisipasi siswa dalam kerja kelompok secara adil?"),
                DetailItem.Prompt("Buat format lembar penilaian diri (self-assessment) untuk siswa setelah presentasi."),
                DetailItem.Prompt("Tuliskan 3 soal studi kasus untuk menguji pemahaman etika bisnis."),
                DetailItem.Prompt("Buat kunci jawaban dan pedoman penskoran untuk 5 soal uraian Fisika.")
            )
            // PROMPT BARU
            "Pengembangan" -> listOf(
                DetailItem.Prompt("Ringkas artikel penelitian terbaru tentang penggunaan AI dalam pendidikan."),
                DetailItem.Prompt("Buat rencana pengembangan diri saya untuk meningkatkan keterampilan mengajar digital."),
                DetailItem.Prompt("Berikan daftar 5 kursus online gratis untuk guru mengenai 'differentiated instruction'."),
                DetailItem.Prompt("Tuliskan draf proposal untuk mengikuti seminar pendidikan tingkat nasional."),
                DetailItem.Prompt("Bagaimana cara memulai Penelitian Tindakan Kelas (PTK) di kelas saya?"),
                DetailItem.Prompt("Draf poin-poin untuk presentasi saya di komunitas belajar guru."),
                DetailItem.Prompt("Berikan ide untuk berkolaborasi dengan guru mata pelajaran lain dalam sebuah proyek."),
                DetailItem.Prompt("Buat daftar 10 buku yang direkomendasikan untuk pengembangan profesional guru."),
                DetailItem.Prompt("Bagaimana cara mendapatkan sertifikasi guru dari Google?"),
                DetailItem.Prompt("Tuliskan refleksi tentang tantangan dan peluang menjadi guru di era digital.")
            )
            "Komunikasi Orang Tua" -> listOf(
                DetailItem.Prompt("Tuliskan draf email untuk memberitahu orang tua tentang jadwal study tour."),
                DetailItem.Prompt("Buat template laporan perkembangan siswa yang akan dikirim ke orang tua setiap bulan."),
                DetailItem.Prompt("Berikan contoh cara menyampaikan kabar kurang baik tentang nilai siswa kepada orang tua dengan cara yang positif."),
                DetailItem.Prompt("Draf agenda untuk pertemuan wali murid di awal semester."),
                DetailItem.Prompt("Tuliskan pesan WhatsApp singkat untuk mengingatkan orang tua tentang tugas proyek anak mereka."),
                DetailItem.Prompt("Bagaimana cara merespons email dari orang tua yang mengeluhkan metode mengajar saya?"),
                DetailItem.Prompt("Buat survei singkat untuk orang tua mengenai kepuasan mereka terhadap komunikasi sekolah."),
                DetailItem.Prompt("Draf surat pemberitahuan tentang adanya libur sekolah mendadak."),
                DetailItem.Prompt("Berikan ide untuk kegiatan yang melibatkan orang tua di sekolah."),
                DetailItem.Prompt("Tuliskan catatan terima kasih kepada orang tua yang telah berpartisipasi dalam acara sekolah.")
            )
            else -> emptyList()
        }
    }

    // --- DETAIL ITEM WEB AI ---
    fun getWebAiItems(category: String): List<DetailItem.WebAi> {
        return when (category) {
            "Administrasi" -> listOf(
                DetailItem.WebAi(R.drawable.logo_googlesheets, "Google Sheets", "Aplikasi spreadsheet online untuk rekap nilai.", "https://docs.google.com/spreadsheets/"),
                DetailItem.WebAi(R.drawable.logo_notion, "Notion", "Workspace all-in-one untuk catatan, manajemen tugas, dan database.", "https://www.notion.so"),
                DetailItem.WebAi(R.drawable.logo_trello, "Trello", "Alat manajemen proyek visual berbasis papan kanban.", "https://trello.com")
            )
            "Media Interaktif" -> listOf(
                DetailItem.WebAi(R.drawable.logo_canva, "Canva", "Platform desain grafis untuk membuat presentasi.", "https://www.canva.com"),
                DetailItem.WebAi(R.drawable.logo_mentimenter, "Mentimeter", "Alat untuk presentasi interaktif dengan polling.", "https://www.mentimeter.com"),
                DetailItem.WebAi(R.drawable.logo_genially, "Genially", "Membuat konten interaktif seperti gambar, presentasi, dan infografis.", "https://genial.ly")
            )
            "Evaluasi Pembelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_quizizz, "Quizizz", "Platform untuk membuat kuis gamifikasi.", "https://quizizz.com"),
                DetailItem.WebAi(R.drawable.logo_googleforms, "Google Forms", "Alat serbaguna untuk survei dan kuis.", "https://docs.google.com/forms/"),
                DetailItem.WebAi(R.drawable.logo_kahoot, "Kahoot!", "Platform pembelajaran berbasis game yang populer.", "https://kahoot.com")
            )
            "Media Pembelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_youtube, "YouTube", "Platform berbagi video untuk materi belajar.", "https://youtube.com"),
                DetailItem.WebAi(R.drawable.logo_prezi, "Prezi", "Alat presentasi non-linear yang dinamis.", "https://prezi.com")
            )
            // WEB BARU
            "Generator Soal" -> listOf(
                DetailItem.WebAi(R.drawable.logo_questgen, "Questgen", "Generator soal otomatis dari teks apapun.", "https://questgen.ai"),
                DetailItem.WebAi(R.drawable.logo_twee, "Twee", "Alat bantu guru bahasa Inggris, termasuk membuat soal.", "https://twee.com")
            )
            "Perencana Pelajaran" -> listOf(
                DetailItem.WebAi(R.drawable.logo_curipod, "Curipod", "Membuat pelajaran interaktif dengan bantuan AI.", "https://curipod.com"),
                DetailItem.WebAi(R.drawable.logo_education_copilot, "Education Copilot", "Generator RPP dan materi ajar berbasis AI.", "https://educationcopilot.com"),
                DetailItem.WebAi(R.drawable.logo_magic_school_ai, "Magic School AI", "Kumpulan alat AI untuk membantu segala tugas guru.", "https://www.magicschool.ai")
            )
            else -> emptyList()
        }
    }

    // --- ISI DATABASE ---
    fun getGlossaryItems(): List<GlossaryItem> {
        return listOf(
            GlossaryItem("Adaptive Learning", "Metode pendidikan yang menggunakan algoritma AI untuk menyesuaikan materi pembelajaran secara real-time sesuai dengan tingkat pemahaman setiap siswa."),
            GlossaryItem("Gamification", "Penerapan elemen-elemen game (seperti poin, lencana, papan peringkat) dalam konteks non-game untuk meningkatkan motivasi dan keterlibatan."),
            GlossaryItem("Personalized Learning Path", "Jalur belajar yang dirancang khusus untuk setiap individu berdasarkan tujuan, kecepatan belajar, dan minat mereka."),
            GlossaryItem("LLM (Large Language Model)", "Model AI yang dilatih pada data teks dalam jumlah besar untuk memahami dan menghasilkan bahasa manusia. Contohnya adalah GPT-4."),
            GlossaryItem("Prompt Engineering", "Seni dan ilmu merancang input (prompt) yang efektif untuk mendapatkan output yang diinginkan dari model AI generatif."),
            GlossaryItem("Differentiated Instruction", "Pendekatan pengajaran yang menyesuaikan kurikulum, metode, dan penilaian untuk memenuhi kebutuhan belajar siswa yang beragam."),
            GlossaryItem("Blended Learning", "Model pembelajaran yang mengkombinasikan metode pengajaran tatap muka tradisional dengan media pembelajaran online."),
            GlossaryItem("Flipped Classroom", "Model di mana siswa mempelajari materi baru di rumah (biasanya melalui video online) dan mengerjakan 'pekerjaan rumah' (latihan atau diskusi) di kelas.")
        )
    }

    fun getWebFavoritData(): List<WebFavorit> {
        return listOf(
            WebFavorit(R.drawable.logo_canva, "Canva", "Desain Grafis", "https://www.canva.com"),
            WebFavorit(R.drawable.logo_chatgpt, "ChatGPT", "Asisten AI", "https://chat.openai.com"),
            WebFavorit(R.drawable.logo_quizizz, "Quizizz", "Kuis Interaktif", "https://quizizz.com"),
            WebFavorit(R.drawable.logo_youtube, "YouTube", "Video Pembelajaran", "https://youtube.com"),
            WebFavorit(R.drawable.logo_googleforms, "Google Forms", "Survei & Kuis", "https://docs.google.com/forms/")
        )
    }
}