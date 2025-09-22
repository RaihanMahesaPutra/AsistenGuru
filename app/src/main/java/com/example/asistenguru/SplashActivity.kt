package com.example.asistenguru

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur tampilan activity menggunakan file layout activity_splash.xml
        setContentView(R.layout.activity_splash)

        // Mencari setiap view dari layout berdasarkan ID-nya secara manual
        val ivLogo: ImageView = findViewById(R.id.iv_logo)
        val tvAppName: TextView = findViewById(R.id.tv_app_name)
        val tvAppSubtitle: TextView = findViewById(R.id.tv_app_subtitle)
        val btnEnter: MaterialButton = findViewById(R.id.btn_enter)

        // Sembunyikan semua view terlebih dahulu agar bisa dianimasikan saat muncul
        ivLogo.visibility = View.INVISIBLE
        tvAppName.visibility = View.INVISIBLE
        tvAppSubtitle.visibility = View.INVISIBLE
        btnEnter.visibility = View.INVISIBLE

        // Memuat file animasi dari folder res/anim
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        // Mengatur durasi dan waktu tunda untuk animasi
        fadeIn.duration = 1000 // Animasi fade-in berjalan selama 1 detik
        slideUp.startOffset = 500 // Animasi slide-up dimulai setelah 0.5 detik
        slideUp.duration = 800 // Animasi slide-up berjalan selama 0.8 detik

        // Menjalankan animasi setelah layout selesai ditampilkan
        ivLogo.post {
            // Tampilkan kembali semua view
            ivLogo.visibility = View.VISIBLE
            tvAppName.visibility = View.VISIBLE
            tvAppSubtitle.visibility = View.VISIBLE
            btnEnter.visibility = View.VISIBLE

            // Mulai animasi untuk setiap view
            ivLogo.startAnimation(fadeIn)
            tvAppName.startAnimation(fadeIn)
            tvAppSubtitle.startAnimation(fadeIn)
            btnEnter.startAnimation(slideUp)
        }

        // Menambahkan aksi klik pada tombol "Masuk"
        btnEnter.setOnClickListener {
            // Pindah ke MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            // Tutup SplashActivity agar pengguna tidak bisa kembali ke halaman ini
            finish()
        }
    }
}

