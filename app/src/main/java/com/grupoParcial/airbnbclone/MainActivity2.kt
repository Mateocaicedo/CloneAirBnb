package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.grupoParcial.airbnbclone.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bottonNavigationView =  binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

        val NavController = navHostFragment.navController
        bottonNavigationView.setupWithNavController(NavController)

    }
}