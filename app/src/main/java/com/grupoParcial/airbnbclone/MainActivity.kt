package com.grupoParcial.airbnbclone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val activador = registerForActivityResult(StartActivityForResult()){
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val IniciarSesion = findViewById<Button>(R.id.iniciarsesion)

        val crearCuenta = findViewById<TextView>(R.id.crearCuenta)


        IniciarSesion.setOnClickListener {
            val intent = Intent(this, ActivityInicioSesion::class.java)
            activador.launch(intent)
        }

        crearCuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            activador.launch(intent)
        }


        ///val PagInicio = findViewById<Button>(R.id.PagInicio)
        ///val PagInicioSesion = findViewById<Button>(R.id.PagInicioSesion)
        ///val PagCrearCuenta = findViewById<Button>(R.id.PagCrearCuenta)
        ///val random = findViewById<Button>(R.id.button17)

        ///random.setOnClickListener {
        ///    val intent = Intent(this, MainActivity2::class.java)
        ///    activador.launch(intent)
        ///}
        ///PagInicio.setOnClickListener {
        ///    val intent = Intent(this, ActivityInicio::class.java)
        ///    activador.launch(intent)
        ///}
        ///PagInicioSesion.setOnClickListener {
        ///    val intent = Intent(this, ActivityInicioSesion::class.java)
        ///    activador.launch(intent)
        ///}
        ///PagCrearCuenta.setOnClickListener {
        ///    val intent = Intent(this, ActivityCrearCuenta::class.java)
        ///    activador.launch(intent)
        ///}



    }


}