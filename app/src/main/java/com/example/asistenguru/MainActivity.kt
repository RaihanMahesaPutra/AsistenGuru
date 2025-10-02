package com.example.asistenguru

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // DIUBAH: Jadikan properti kelas agar bisa diakses
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Inisialisasi properti kelas
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    // BARU: Fungsi publik untuk mengganti tab yang dipilih
    fun switchToTab(destinationId: Int) {
        bottomNavigationView.selectedItemId = destinationId
    }
}