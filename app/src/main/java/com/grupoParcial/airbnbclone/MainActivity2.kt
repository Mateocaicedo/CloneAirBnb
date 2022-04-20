package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.grupoParcial.airbnbclone.databinding.ActivityMain2Binding
import com.grupoParcial.airbnbclone.model.Alojamientos
class MainActivity2 : AppCompatActivity() {

    private var alojamientos:List<Alojamientos> =listOf(
        Alojamientos("Rincon de georgio",
            "Pura marica que viene aqui",
            "Sal si puedes",
            150,
            "https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"), Alojamientos("Rincon de georgio",
            "Pura marica que viene aqui",
            "Sal si puedes",
            150,
            "https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"), Alojamientos("Rincon de georgio",
            "Pura marica que viene aqui",
            "Sal si puedes",
            150,
            "https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"), Alojamientos("Rincon de georgio",
            "Pura marica que viene aqui",
            "Sal si puedes",
            150,
            "https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg")
    )

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