package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val activador = registerForActivityResult(StartActivityForResult()){
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debugtodasvistas)

        val PagInicio = findViewById<Button>(R.id.PagInicio)
        val PagInicioSesion = findViewById<Button>(R.id.PagInicioSesion)
        val PagCrearCuenta = findViewById<Button>(R.id.PagCrearCuenta)


        PagInicio.setOnClickListener {
            val intent = Intent(this, ActivityInicio::class.java)
            activador.launch(intent)
        }

        PagInicioSesion.setOnClickListener {
            val intent = Intent(this, ActivityInicioSesion::class.java)
            activador.launch(intent)
        }

        PagCrearCuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            activador.launch(intent)
        }
    }
}