package com.grupoParcial.airbnbclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class ActivityInicioSesion : AppCompatActivity() {
    private val activador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciarsesion)

        val BottonInicio = findViewById<Button>(R.id.iniciarHome)
        val crearCuenta = findViewById<TextView>(R.id.crearCuenta)

        BottonInicio.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            activador.launch(intent)
        }

        crearCuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            activador.launch(intent)
        }


    }
}