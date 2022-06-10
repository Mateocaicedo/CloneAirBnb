package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.grupoParcial.airbnbclone.databinding.ActivityMain2Binding

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bottonNavigationView =  binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

        val NavController = navHostFragment.navController
        bottonNavigationView.setupWithNavController(NavController)

        val email = intent.getStringExtra("email")
        val provider = intent.getStringExtra("provider")
        setupHome(email ?:"", provider ?: "")
        val prefs = getSharedPreferences(getString(R.string.prefs_file_name), MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setupHome(email: String, provider: String) {
        title = "Home"
    }
}