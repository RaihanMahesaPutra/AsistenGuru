package com.example.asistenguru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val splashTimeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = auth.currentUser

            if (currentUser != null) {
                // PENGGUNA SUDAH LOGIN, PERIKSA PERANNYA
                if (currentUser.email == "adminajakonline@asistenguru.com") { // Ganti dengan email admin Anda
                    // Jika user adalah admin, arahkan ke AdminActivity
                    startActivity(Intent(this, AdminActivity::class.java))
                } else {
                    // Jika user adalah customer biasa, arahkan ke MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                }
            } else {
                // PENGGUNA BELUM LOGIN
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()

        }, splashTimeOut)
    }
}